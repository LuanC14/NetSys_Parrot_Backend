package com.SocialNetSys.NetSys.Models.Entities;

import com.SocialNetSys.NetSys.Models.Objects_Model.Biography;
import lombok.Data;

import java.util.LinkedList;
import java.util.UUID;
@Data
public class User {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private LinkedList<Biography> biography;

    public User(String name, String email, String password) {
        this.setId();
        this.name = name;
        this.email = email;
        this.password = password;
        this.biography = new LinkedList<Biography>();
        };

    public void setBiography(Biography bio) {
        this.biography.add(bio);
    }

    public void setId() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return this.id;
    }
}
