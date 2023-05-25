package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Requests.LikeRequest;
import com.SocialNetSys.NetSys.Services.Likes.ILikesService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/like")
@CrossOrigin(origins = "http://localhost:5173")
public class LikesController {
    @Autowired
    ILikesService _likeService;

    @PostMapping("{userId}/{postId}")
    ResponseEntity<String> likePublication(@PathVariable("userId") UUID userId, @PathVariable("postId") UUID postId ) {

        _likeService.setLikePublication(userId, postId );

        return ResponseEntity.ok().body("Like Publication");
    }

    @DeleteMapping("/{userId}/{postId}")
    @Operation(description = "Remove o like dado na publicação. O ID da publicação vem do Path Params e o do curtidor vem do Token")
    ResponseEntity<String> removeLike(@PathVariable("userId") UUID userId, @PathVariable("postId") UUID postId ) {
        _likeService.removeLikePublication(userId, postId);

        return ResponseEntity.ok().body("Like removido");
    }
}
