package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Requests.CommentRequest;
import com.SocialNetSys.NetSys.Services.Comment.ICommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/v1/publications/comment")
public class CommentController {
    @Autowired
    ICommentService _commentService;

    @PostMapping(path = "/{post_id}")
    ResponseEntity<String> createComment(@RequestBody CommentRequest request, HttpServletRequest servletRequest, @PathVariable UUID post_id) throws Exception {

        _commentService.createComment(request, servletRequest, post_id);

        return ResponseEntity.ok().body("Comentário adicionado");
    }

    @DeleteMapping("/{postId}/{commentId}")
    ResponseEntity<String> deleteComment(
            @PathVariable("postId") UUID postId, @PathVariable("commentId") UUID commentId, HttpServletRequest servletRequest) throws Exception {

        _commentService.removeComment(postId, servletRequest, commentId);
        return ResponseEntity.ok().body("Comentário deletado");
    }
}
