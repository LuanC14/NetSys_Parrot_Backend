package com.SocialNetSys.NetSys.Models.Objects;

import lombok.Data;

import java.util.UUID;
@Data
public class User_Model {
    public String name;
    public UUID id;

    public User_Model(String name, UUID id) {
        this.name = name;
        this.id = id;
    }
}
