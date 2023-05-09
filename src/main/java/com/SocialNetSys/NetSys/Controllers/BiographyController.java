package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Requests.BiographyRequest;
import com.SocialNetSys.NetSys.Services.Biography.IBiographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/biography")
public class BiographyController {

    @Autowired
    IBiographyService _biographyService;

    @PatchMapping()
    ResponseEntity<String> createBiography(@RequestBody BiographyRequest request, HttpServletRequest servletRequest) {
        _biographyService.createBiography(request, servletRequest);
        return ResponseEntity.ok("Created with success!");
    }

    @PatchMapping("/update/{biographyId}")
    ResponseEntity<String> updateBiography(
            @PathVariable("biographyId")UUID biographyId, @RequestBody Biography_Model request, HttpServletRequest servletRequest) {
       _biographyService.updateBiography(biographyId, request, servletRequest );

        return  ResponseEntity.ok("Atualizado");
    }
}
