package com.SocialNetSys.NetSys.Models.Objects;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Biography_Model {
    @Id
    public UUID id;
    public UUID user_id;
    public String type;
    public String value;

    public Biography_Model() {
        this.id = UUID.randomUUID();
    }
}
