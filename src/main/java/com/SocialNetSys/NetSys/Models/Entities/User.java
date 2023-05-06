package com.SocialNetSys.NetSys.Models.Entities;

import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Objects.User_Model;
import lombok.Data;

import java.util.LinkedList;
import java.util.UUID;
@Data
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LinkedList<Biography_Model> biography;
    private String avatar;
    private LinkedList<User_Model> followers;
    private LinkedList<User_Model> following;

    public User(String name, String email, String password) {
        this.setId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.biography = new LinkedList<Biography_Model>();
        };

    public void setBiography(Biography_Model bio) {
        this.biography.add(bio);
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }

    public void setFollower(User_Model newUser) {

        if(followers == null) {
            this.followers = new LinkedList<User_Model>();
        }

        this.followers.add(newUser);
    }

    public void setFollowing(User_Model newUser) {

        if(this.following == null) {
            this.following = new LinkedList<User_Model>();
        }
        this.following.add(newUser);
    }
}
