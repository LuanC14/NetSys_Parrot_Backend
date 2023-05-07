package com.SocialNetSys.NetSys.Models.Responses;

import lombok.Data;

@Data
public class FollowerResponse {
    public String nameFollower;
    public String nameFollowed;

    public FollowerResponse(String nameFollower, String nameFollowed) {
        this.nameFollower = nameFollower;
        this.nameFollowed = nameFollowed;
    }
}
