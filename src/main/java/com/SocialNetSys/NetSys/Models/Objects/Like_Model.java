package com.SocialNetSys.NetSys.Models.Objects;

import lombok.Data;

import java.util.UUID;
@Data
public class Like_Model {
    public String name;
    public UUID user_id;
    public UUID post_id;

    public Like_Model(String name, UUID user_id, UUID post_id) {
        this.name = name;
        this.user_id = user_id;
        this.post_id = post_id;
    }
}
