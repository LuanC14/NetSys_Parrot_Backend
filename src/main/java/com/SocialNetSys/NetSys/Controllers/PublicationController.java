package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Responses.PublicationResponse;
import com.SocialNetSys.NetSys.Services.Publications.IPublicationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/publications")
public class PublicationController {
    @Autowired
    IPublicationService _publicationService;

    @GetMapping(path = "/{userId}")
    ResponseEntity<List<PublicationResponse>> findPublication(@PathVariable UUID userId) {

       var response = _publicationService.findPublications(userId);

        return ResponseEntity.ok().body(response);
    };
    @PostMapping("/new")
    ResponseEntity<Publication> createPublication(
        @RequestParam(name = "title", required = false) String title, @RequestParam(name = "photo", required = false) MultipartFile  photo, HttpServletRequest servletRequest) throws Exception {

        if(photo == null && title == null) {
            return null;
        }

        var response = _publicationService.createPublication(title, photo, servletRequest);

        return ResponseEntity.ok().body(response);
    };

    @DeleteMapping("/{postId}")
    ResponseEntity<String> deletePublication(@PathVariable UUID postId) {
        _publicationService.deletePublication(postId);
        return ResponseEntity.ok().body("Post deletado");
    }
}
