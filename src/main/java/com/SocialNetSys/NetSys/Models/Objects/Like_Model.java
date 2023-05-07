package com.SocialNetSys.NetSys.Models.Objects;

import lombok.Data;

import java.util.UUID;
@Data
public class Like_Model {
    public String name;
    public UUID userId;
    public UUID postId;

    public Like_Model(String name, UUID userId, UUID postId) {
        this.name = name;
        this.userId = userId;
        this.postId = postId;
    }
}
