package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/user/avatar")
public class UserAvatarController {

    @Autowired
    private IUserService _userService;

    @PatchMapping()
    public ResponseEntity addPhoto(@RequestParam("photo") MultipartFile photo, HttpServletRequest servletRequest) {

        try{
            _userService.uploadPhotoProfile(photo, servletRequest);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
