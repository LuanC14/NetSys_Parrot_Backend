package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Requests.CommentRequest;
import com.SocialNetSys.NetSys.Models.Requests.PublicationRequest;
import com.SocialNetSys.NetSys.Services.Comment.ICommentService;
import com.SocialNetSys.NetSys.Services.Publications.IPublicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/publication")
public class PublicationController {
    @Autowired
    IPublicationService _publicationService;

    @Autowired
    ICommentService _commentService;
    @PostMapping()
    @PreAuthorize("Authorized")
    ResponseEntity<Publication> createPublication(@RequestBody PublicationRequest request, HttpServletRequest servletRequest) {

        var response = _publicationService.createPublication(request, servletRequest);

        return ResponseEntity.ok().body(response);
    };

    @PostMapping(path = "/{post_id}")
    ResponseEntity<String> createComment(@RequestBody CommentRequest request, HttpServletRequest servletRequest, @PathVariable UUID post_id) {

        _commentService.handleComment(request, servletRequest, post_id);

        return ResponseEntity.ok().body("Comentário adicionado");
    }


}
