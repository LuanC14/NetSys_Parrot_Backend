package com.SocialNetSys.NetSys.Models.Entities;

import com.SocialNetSys.NetSys.Models.Objects_Model.Biography_Model;
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
}
