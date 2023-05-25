package com.SocialNetSys.NetSys.Models.Responses;

import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Objects.User_Model;
import lombok.Data;

import java.util.LinkedList;
import java.util.UUID;
@Data
public class FindUserResponse {
    public UUID id;
    public String name;
    public String email;
    public String username;
    public String avatarUri;
    public LinkedList<User_Model> followers;
    public LinkedList<User_Model> following;

    public LinkedList<Biography_Model> biography;


    public FindUserResponse (
            UUID id, String name, String email, String username,
            LinkedList<User_Model> followers, LinkedList<User_Model> following, LinkedList<Biography_Model> biography, String avatarUri ) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.avatarUri = avatarUri;
        this.followers = followers;
        this.following = following;
        this.biography = biography;
    }
}
