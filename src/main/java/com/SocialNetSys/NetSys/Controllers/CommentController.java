package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Requests.CommentRequest;
import com.SocialNetSys.NetSys.Services.Comment.ICommentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    ICommentService _commentService;

    @PostMapping(path = "/{post_id}")
    ResponseEntity<String> createComment(@RequestBody CommentRequest request, HttpServletRequest servletRequest, @PathVariable UUID post_id) {

        _commentService.handleComment(request, servletRequest, post_id);

        return ResponseEntity.ok().body("Comentário adicionado");
    }
}
