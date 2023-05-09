package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Responses.FollowerResponse;
import com.SocialNetSys.NetSys.Services.Follows.IFollowService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "Começa a seguir alguém, o id do usuário seguido vem pelo Path Params. É necessário Token, pois o id do seguidor vem dele")
    ResponseEntity<String> follow(HttpServletRequest servletRequest, @PathVariable UUID userFollowedId) throws Exception {

       var response = _followService.followUser(servletRequest, userFollowedId);

        return ResponseEntity.ok().body(response.getNameFollower() + " acabou de seguir " + response.getNameFollowed());
    }

    @DeleteMapping(path = "/{userFollowedId}")
    @Operation(description = "Remove o follow dado no usuário seguido. O id do usuário seguido vem do Path Params e o do seguidor vem do Token")
    ResponseEntity<String> unfollow(HttpServletRequest servletRequest, @PathVariable UUID userFollowedId) throws Exception {

        var response = _followService.unfollowUser(servletRequest, userFollowedId);
        return ResponseEntity.ok().body(response.nameFollower + " Parou de seguir " + response.nameFollowed);
    }
}
