package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Responses.FollowerResponse;
import com.SocialNetSys.NetSys.Services.Follows.IFollowService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/follows")
public class FollowController {
    @Autowired
    IFollowService _followService;

    @PostMapping(path = "/{userFollowedId}")
    ResponseEntity<String> follow(HttpServletRequest servletRequest, @PathVariable UUID userFollowedId) throws Exception {

       var response = _followService.followUser(servletRequest, userFollowedId);

        return ResponseEntity.ok().body(response.getNameFollower() + " acabou de seguir " + response.getNameFollowed());
    }

    @DeleteMapping(path = "/{userFollowedId}")
    ResponseEntity<String> unfollow(HttpServletRequest servletRequest, @PathVariable UUID userFollowedId) throws Exception {

        var response = _followService.unfollowUser(servletRequest, userFollowedId);
        return ResponseEntity.ok().body(response.nameFollower + " Parou de seguir " + response.nameFollowed);
    }
}
