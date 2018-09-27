package com.yifengblog.twitter.ws.tweet.io.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="password_reset_token")
public class PasswordResetTokenEntity implements Serializable {
    private static final long serialVersionUID = 5504700281187561277L;
    
    @GeneratedValue
    @Id
    private long id;
    
    private String token;
    
    @OneToOne()
    @JoinColumn(name="users_id") // foreign key
    private UserEntity userDetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserEntity getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserEntity userDetails) {
        this.userDetails = userDetails;
    }
    
}
