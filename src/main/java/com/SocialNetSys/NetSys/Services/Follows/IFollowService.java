package com.SocialNetSys.NetSys.Services.Follows;

import com.SocialNetSys.NetSys.Models.Responses.FollowerResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

public interface IFollowService {

    public FollowerResponse followUser(HttpServletRequest servletRequest, UUID userFollowedId) throws Exception;

    public FollowerResponse unfollowUser(HttpServletRequest servletRequest, UUID userFollowedId) throws Exception;
}
