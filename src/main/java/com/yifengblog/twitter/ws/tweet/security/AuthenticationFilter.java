package com.yifengblog.twitter.ws.tweet.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yifengblog.twitter.ws.SpringApplicationContext;
import com.yifengblog.twitter.ws.tweet.service.UserService;
import com.yifengblog.twitter.ws.tweet.shared.dto.UserDTO;
import com.yifengblog.twitter.ws.user.ui.request.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    
    private final AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
    
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) throws AuthenticationException{
        
        try {
            UserLoginRequestModel loginReq = new ObjectMapper()
                    .readValue(request.getInputStream(), UserLoginRequestModel.class);
            
            // the third parameter is java.util.Collection<? extends GrantedAuthority> authorities;
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginReq.getEmail(),
                            loginReq.getPassword(),
                            new ArrayList<>())
            );
        
        } catch (JsonParseException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (JsonMappingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

//    Fields of User.class    
//    private String password;
//    private final String username;
//    private final Set<GrantedAuthority> authorities;
//    private final boolean accountNonExpired;
//    private final boolean accountNonLocked;
//    private final boolean credentialsNonExpired;
//    private final boolean enabled;
    
    
//    auth is produced in the function above
    public void successfulAuthentication(HttpServletRequest req, 
            HttpServletResponse response,
            FilterChain chain,
            Authentication auth) throws IOException, ServletException {
        
        String userName = ((User)auth.getPrincipal()).getUsername();
        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                .compact();
        
        UserService userService = SpringApplicationContext.getBean("userServiceImpl", UserService.class);
        UserDTO userDTO = userService.getUser(userName);
        
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        response.addHeader("UserId", userDTO.getUserId());
        
        // if authentication succeeded. Not further passing down along the chain
    }
}