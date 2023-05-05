package com.SocialNetSys.NetSys.Models.Objects_Model;

import lombok.Data;

import java.util.UUID;

@Data
public class Biography {
    public UUID user_id;
    public String type;
    public String value;
}
