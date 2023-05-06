package com.SocialNetSys.NetSys.Models.Objects;

import lombok.Data;

import java.util.UUID;

@Data
public class Biography_Model {
    public UUID user_id;
    public String type;
    public String value;
}
