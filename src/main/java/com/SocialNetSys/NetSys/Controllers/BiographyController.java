package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Requests.BiographyRequest;
import com.SocialNetSys.NetSys.Services.Biography.IBiographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user/biography")
public class BiographyController {

    @Autowired
    IBiographyService _biographyService;

    @PatchMapping()
    ResponseEntity<String> userBiographyCreator(@RequestBody BiographyRequest request, HttpServletRequest servletRequest) {
        _biographyService.createBiography(request, servletRequest);
        return ResponseEntity.ok("Created with success!");
    }
}
