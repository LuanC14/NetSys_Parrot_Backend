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
    public String username;
    private String email;
    private String password;
    private LinkedList<Biography_Model> biography;
    private String avatar;
    public LinkedList<User_Model> followers;
    public LinkedList<User_Model> following;

    public User(String name, String email, String username, String password) {
        this.setId();
        this.name = name;
        this.email = email;
        this.username = username;
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

    public void startFollow(User_Model followedUser) {

        if(this.following == null) {
            this.following = new LinkedList<User_Model>();
        }

        this.following.add(followedUser);
    }

    public void receiveFollow(User_Model newFollower) {

        if(followers == null) {
            this.followers = new LinkedList<User_Model>();
        }

        this.followers.add(newFollower);
    }

    public boolean isFollowing(UUID userId) {
        if (following != null) {
            for (User_Model user : following) {
                if (user.id.equals(userId)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void stopFollow(UUID followedUserId) {

    following.removeIf(user -> user.getId().equals(followedUserId));

    }

    public void lostFollow(UUID idFollowLost) {

        followers.removeIf(user -> user.getId().equals(idFollowLost));


    }
}
