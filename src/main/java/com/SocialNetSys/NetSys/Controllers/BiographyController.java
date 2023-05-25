package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Requests.BiographyRequest;
import com.SocialNetSys.NetSys.Services.Biography.IBiographyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.LinkedList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/biography")
@CrossOrigin(origins = "http://localhost:5173")
public class BiographyController {

    @Autowired
    IBiographyService _biographyService;

    @PostMapping("/new")
    ResponseEntity<String> createBiography(@RequestBody BiographyRequest request, HttpServletRequest servletRequest) {
         _biographyService.createBiography(request, servletRequest);
        return ResponseEntity.ok("Dado cadastrado com sucesso");
    }

    @PatchMapping("/update/{itemBioId}")
    ResponseEntity<LinkedList<Biography_Model>> updateBiography(
            @PathVariable("itemBioId")UUID itemBioId, @RequestBody BiographyRequest request, HttpServletRequest servletRequest) {
       var response = _biographyService.updateBiography(itemBioId, request, servletRequest );

        return  ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{itemBioId}")
    @Operation(description = "Deleta um único objeto da coleção de Biografia, pelo seu ID")
    ResponseEntity<String> deletedBiography(@PathVariable("itemBioId") UUID itemBioId, HttpServletRequest servletRequest) {
        _biographyService.deleteBiography(itemBioId, servletRequest);
        return null;
    }
}
