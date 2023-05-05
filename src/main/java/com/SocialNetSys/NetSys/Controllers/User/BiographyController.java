package com.SocialNetSys.NetSys.Controllers.User;

import com.SocialNetSys.NetSys.Models.Objects_Model.BiographyRequest;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/biography")
public class UserBioController {
    @Autowired
    IUserService _createUserService;

    @PatchMapping()
    ResponseEntity<String> userBiographyCreator(@RequestBody BiographyRequest request) {
        _createUserService.createBiography(request);
        return ResponseEntity.ok("Created with success!");
    }
}
