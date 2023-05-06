package com.SocialNetSys.NetSys.Models.Responses;

import java.util.UUID;

public class FindUserResponse {
    public UUID id;
    public String name;
    public String email;

    public FindUserResponse (UUID id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
