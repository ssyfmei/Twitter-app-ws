package com.yifengblog.twitter.ws.tweet.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.yifengblog.twitter.ws.tweet.exceptions.UserServiceException;
import com.yifengblog.twitter.ws.tweet.io.entity.UserEntity;
import com.yifengblog.twitter.ws.tweet.io.repository.UserRepository;
import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;



class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;
    
    @Mock
    UserRepository userRepository;
    
    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetUserByEmail() {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setEncryptedPassword("fdfagadgadaf");
        userEntity.setUserId("dfadf");
        userEntity.setScreenName("Yifeng");
        
        when(userRepository.findByEmail(anyString())).thenReturn(userEntity);
        UserDTO userDto = userService.getUserByEmail("fdaf");
        
        assertNotNull(userDto);
        assertEquals("Yifeng", userDto.getScreenName());
    }
    @Test
    final void testGetUser_UsernameNotFoundException()
    {
        when(userRepository.findByEmail(anyString())).thenReturn(null);
        
        assertThrows(UserServiceException.class, ()->{
            userService.getUserByEmail("fdfa");
        });
    }
}
