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
public class BiographyController {

    @Autowired
    IBiographyService _biographyService;

    @PatchMapping()
    @Operation(description = "Realiza o cadastro de mais de um objeto na coleção de Biografia do Usuário." +
            "A requisição é uma LinkedList do tipo Biography_Model. É necessário Token, pois o userId vem dele")
    ResponseEntity<LinkedList<Biography_Model>> createBiography(@RequestBody BiographyRequest request, HttpServletRequest servletRequest) {
        var response = _biographyService.createBiography(request, servletRequest);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update/{itemBioId}")
    @Operation(description = "Realiza o cadastro de apenas um objeto na Coleção de biografia do Usuário." +
            "A requisição é um único objeto do tipo Biography_Model. É necessário Token JWT")
    ResponseEntity<LinkedList<Biography_Model>> updateBiography(
            @PathVariable("itemBioId")UUID itemBioId, @RequestBody Biography_Model request, HttpServletRequest servletRequest) {
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
