package com.SocialNetSys.NetSys.Controllers;


import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects_Model.AvatarRequest;
import com.SocialNetSys.NetSys.Services.AvatarService.IAvatarService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/avatar")
public class AvatarController {

    @Autowired
    IAvatarService _avatarService;

    @PatchMapping()
    @PreAuthorize("Authorized")
    ResponseEntity<String> setAvatar(@RequestBody AvatarRequest request, HttpServletRequest servletRequest) {

        _avatarService.setAvatar(request, servletRequest);

        return ResponseEntity.ok().body("Foto adicionado");
    }
}
