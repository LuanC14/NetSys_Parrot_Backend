package com.SocialNetSys.NetSys.Models.Objects;

import lombok.Data;

import java.util.UUID;
@Data
public class User_Model {
    public String name;
    public String username;
    public UUID id;

    public User_Model(String name, UUID id, String username) {
        this.name = name;
        this.id = id;
        this.username = username;
    }
}
