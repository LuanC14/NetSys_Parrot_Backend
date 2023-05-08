package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Requests.LikeRequest;
import com.SocialNetSys.NetSys.Services.Likes.ILikesService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publications/like")
public class LikesController {
    @Autowired
    ILikesService _likeService;

    @PostMapping(path = "/{post_id}")
    ResponseEntity<String> likePublication( HttpServletRequest servletRequest, @PathVariable UUID post_id) {

        _likeService.setLikePublication( servletRequest, post_id);

        return ResponseEntity.ok().body("Like Publication");
    }

    @DeleteMapping( path = "/delete/{post_id}")
    ResponseEntity<String> removeLike(HttpServletRequest servletRequest, @PathVariable UUID post_id ) {
        _likeService.removeLikePublication(servletRequest, post_id);

        return ResponseEntity.ok().body("Like removido");
    }
}
