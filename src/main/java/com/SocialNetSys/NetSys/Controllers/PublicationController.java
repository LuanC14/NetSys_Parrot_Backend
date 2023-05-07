package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Requests.CommentRequest;
import com.SocialNetSys.NetSys.Models.Requests.PublicationRequest;
import com.SocialNetSys.NetSys.Models.Responses.PublicationResponse;
import com.SocialNetSys.NetSys.Services.Comment.ICommentService;
import com.SocialNetSys.NetSys.Services.Publications.IPublicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/publication")
public class PublicationController {
    @Autowired
    IPublicationService _publicationService;

    @GetMapping(path = "/{userId}")
    ResponseEntity<List<PublicationResponse>> findPublication(@PathVariable UUID userId) {

       var response = _publicationService.findPublications(userId);

        return ResponseEntity.ok().body(response);
    };
    @PostMapping()
    ResponseEntity<Publication> createPublication(@RequestBody PublicationRequest request, HttpServletRequest servletRequest) {

        var response = _publicationService.createPublication(request, servletRequest);

        return ResponseEntity.ok().body(response);
    };
}