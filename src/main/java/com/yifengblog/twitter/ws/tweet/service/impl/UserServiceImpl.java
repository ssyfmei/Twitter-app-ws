package com.yifengblog.twitter.ws.tweet.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yifengblog.twitter.ws.tweet.io.entity.PasswordResetTokenEntity;
import com.yifengblog.twitter.ws.tweet.io.entity.TweetEntity;
import com.yifengblog.twitter.ws.tweet.io.entity.UserEntity;
import com.yifengblog.twitter.ws.tweet.io.repository.PasswordResetTokenRepository;
import com.yifengblog.twitter.ws.tweet.io.repository.TweetRepository;
import com.yifengblog.twitter.ws.tweet.io.repository.UserRepository;
import com.yifengblog.twitter.ws.tweet.service.UserService;
import com.yifengblog.twitter.ws.tweet.shared.AmazonSES;
import com.yifengblog.twitter.ws.tweet.shared.Utils;
import com.yifengblog.twitter.ws.tweet.shared.dto.TweetDTO;
import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;

@Service
public class UserServiceImpl implements UserService {
	private int userIdLen = 40;
	
	@Autowired
	Utils utils;	
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	UserRepository userRepository;
	@Autowired
	TweetRepository tweetRepository;
	@Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Override
	public UserDTO getUserByEmail(String email) {
		UserDTO returnValue = new UserDTO();
	    UserEntity userEntity = userRepository.findByEmail(email);
	    BeanUtils.copyProperties(userEntity, returnValue);
	    return returnValue;
//	    UserEntity userEntity = userRepository.findByEmail(email);
//        return modelMapper.map(userEntity, UserDTO.class);
	}
	
	@Override
	public UserDTO createUser(UserDTO userDto) {	    
//	    if(userRepository.findByEmail(userDto.getEmail()) != null)
//	        throw new UserServiceException(ErrorMessageEnum.RECORD_ALREADY_EXISTS.name());
	    
		UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
		
		String publicUserId = utils.generateUserId(userIdLen);
		userEntity.setUserId(publicUserId);
		userEntity.setEmailVerificationStatus(false);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		
		userEntity.setEmailVerificationToken(Utils.generateEmailVerificationToken(publicUserId));
		
		UserEntity createdEntity = userRepository.save(userEntity);
		UserDTO	   createdDto    = modelMapper.map(createdEntity, UserDTO.class);
		
		new AmazonSES().verifyEmail(createdDto);
		
		return createdDto;
	}
	
	@Override
	public List<TweetDTO> getTweets(String userId) {
		List<TweetDTO> returnValue = new ArrayList<>();
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		if(userEntity == null) return returnValue;
		
		Iterable<TweetEntity> tweets = tweetRepository.findAllByUserDetails(userEntity);
		
		for(TweetEntity tweet: tweets) {
			returnValue.add(modelMapper.map(tweet, TweetDTO.class));
		}
		return returnValue;
	}

	@Override
	public UserDTO updateUser(String userId, UserDTO userDto) {	
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		userEntity.setScreenName(userDto.getScreenName());
		UserEntity updatedEntity = userRepository.save(userEntity);
		return modelMapper.map(updatedEntity, UserDTO.class);
	}

	@Override
	public void deleteUser(String userId) {
		UserEntity userEntity = userRepository.findByUserId(userId);
		userRepository.delete(userEntity);
	}

	@Override
	public UserDTO getUser(String userId) {
//	    UserDTO returnValue = new UserDTO();
//	    UserEntity userEntity = userRepository.findByUserId(userId);
//	    BeanUtils.copyProperties(userEntity, returnValue);
//	    return returnValue;
	    UserEntity userEntity = userRepository.findByUserId(userId);
        return modelMapper.map(userEntity, UserDTO.class);
	}
	
	@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        UserEntity userEntity = userRepository.findByEmail(email);
        if(userEntity == null) throw new UsernameNotFoundException(email);
        
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), 
                userEntity.getEmailVerificationStatus(),
                true, true, true, new ArrayList<>());
        //return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
    public boolean verifyEmailToken(String token) {
        boolean returnValue = false;
        
        UserEntity userEntity = userRepository.findUserByEmailVerificationToken(token);
        
        if(userEntity != null) {
            boolean hasTokenExpired = Utils.hasTokenExpired(token);
            if(!hasTokenExpired) {
                userEntity.setEmailVerificationToken(null);
                userEntity.setEmailVerificationStatus(Boolean.TRUE);
                userRepository.save(userEntity);
                returnValue = true;
            } 
        }
        return returnValue;
    }
    
    @Override
    public boolean requestPasswordReset(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        
        String token = Utils.generatePasswordResetToken(userEntity.getUserId());
        
        PasswordResetTokenEntity passwordResetTokenEntity = new PasswordResetTokenEntity();
        passwordResetTokenEntity.setToken(token);
        passwordResetTokenEntity.setUserDetails(userEntity);
        passwordResetTokenRepository.save(passwordResetTokenEntity);
        
        String screenName = userEntity.getScreenName();
        boolean returnValue = new AmazonSES().
                sendPasswordResetRequest(screenName, email, token);
        
        return returnValue;
    }

    @Override
    public boolean resetPassword(String token, String password) {
        boolean returnValue = true;
        
        if( Utils.hasTokenExpired(token)) {
            return returnValue;
        }
        PasswordResetTokenEntity passwordResetTokenEntity = passwordResetTokenRepository.findByToken(token);
        if(passwordResetTokenEntity == null) {
            return returnValue;
        }
        
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        UserEntity userEntity = passwordResetTokenEntity.getUserDetails();
        userEntity.setEncryptedPassword(encodedPassword);
        UserEntity savedUserEntity = userRepository.save(userEntity);
        
        if(savedUserEntity != null && savedUserEntity.getEncryptedPassword().equalsIgnoreCase(encodedPassword)) {
            returnValue = true;
        }
        
        passwordResetTokenRepository.delete(passwordResetTokenEntity);
        
        return returnValue;
    }
}
