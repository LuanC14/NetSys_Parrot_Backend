package com.SocialNetSys.NetSys.Models.Responses;

import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Objects.User_Model;

import java.util.LinkedList;
import java.util.UUID;

public class FindUserResponse {
    public UUID id;
    public String name;
    public String email;
    public String username;
    public LinkedList<User_Model> followers;
    public LinkedList<User_Model> following;

    public LinkedList<Biography_Model> biography;


    public FindUserResponse (
            UUID id, String name, String email, String username,
            LinkedList<User_Model> followers, LinkedList<User_Model> following, LinkedList<Biography_Model> biography ) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.username = username;
        this.followers = followers;
        this.following = following;
        this.biography = biography;
    }
}
