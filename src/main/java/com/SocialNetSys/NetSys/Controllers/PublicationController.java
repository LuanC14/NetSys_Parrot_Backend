package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Entities.Publication;
import com.SocialNetSys.NetSys.Models.Responses.PublicationResponse;
import com.SocialNetSys.NetSys.Services.Publications.IPublicationService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping(path = "/all/{userId}")
    @Operation(description = "Retorna todas as publicações do usuário pelo ID do usuário")
    ResponseEntity<List<PublicationResponse>> findAllPublication(@PathVariable UUID userId) {

       var response = _publicationService.findAllPublications(userId);

        return ResponseEntity.ok().body(response);
    };

    @GetMapping(path = "/{postId}")
    @Operation(description = "Retorna a publicação pelo ID dela")
    ResponseEntity<Publication> getPublicationById(@PathVariable UUID postId) {

        var response = _publicationService.findPublicationById(postId);

        return ResponseEntity.ok().body(response);
    }


    @PostMapping("/new")
    @Operation(description = "Cria uma nova publicação. Foto e texto vem do Multipartform. Há a liberdade de enviar ou só texto ou só imagem. O ID do criador vem do token.")
    ResponseEntity<Publication> createPublication(
        @RequestParam(name = "title", required = false) String title, @RequestParam(name = "photo", required = false) MultipartFile  photo, HttpServletRequest servletRequest) throws Exception {

        var response = _publicationService.createPublication(title, photo, servletRequest);

        return ResponseEntity.ok().body(response);
    };

    @DeleteMapping("/{postId}")
    @Operation(description = "Remove a publicação de acordo com o postId que vem do Path Param. É necessário o Token de autenticação para validação")
    ResponseEntity<String> deletePublication(@PathVariable UUID postId, HttpServletRequest servletRequest) {
        _publicationService.deletePublication(postId, servletRequest);
        return ResponseEntity.ok().body("Post deletado");
    }
}
