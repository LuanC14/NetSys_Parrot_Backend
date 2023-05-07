package com.SocialNetSys.NetSys.Services.Follows;

import com.SocialNetSys.NetSys.Models.Responses.FollowerResponse;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class FollowService implements  IFollowService {
    @Autowired
    IUserService _userService;

    public FollowerResponse followUser(HttpServletRequest servletRequest, UUID userFollowedId) {

        var userIdFromRequest = (String) servletRequest.getAttribute("user_id"); // Meu ID

        var myId = UUID.fromString(userIdFromRequest);

       return _userService.followManager(myId, userFollowedId);
    }

    public FollowerResponse unfollowUser(HttpServletRequest servletRequest, UUID userFollowedId) {
        var myIdFromRequest = (String) servletRequest.getAttribute("user_id");

        var myId = UUID.fromString(myIdFromRequest);

        return _userService.unfollowManager(myId, userFollowedId);
    }
}
