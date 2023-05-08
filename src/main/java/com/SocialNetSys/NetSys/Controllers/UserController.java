package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Requests.ChangeNameRequest;
import com.SocialNetSys.NetSys.Models.Requests.ChangePasswordRequest;
import com.SocialNetSys.NetSys.Models.Responses.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Requests.UserRequest;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService _userService;

    @GetMapping("/username")
    public ResponseEntity<FindUserResponse> getUserByUsername(String username) {

        return ResponseEntity.ok().body(_userService.responseUserByUsername(username));
    }

    @PostMapping("/signup")
    ResponseEntity<String> userCreator(@RequestBody UserRequest request) {
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {

        _userService.changePassword(request);

        return ResponseEntity.ok().body("Senha alterada com sucesso");
    };

    @PatchMapping("/name")
    public ResponseEntity<String> changeName(@RequestBody ChangeNameRequest request, HttpServletRequest servletRequest ) {
        _userService.changeName(request, servletRequest);

        return ResponseEntity.ok().body("Nome alterado com suceso!");
    };
    @PatchMapping("/avatar")
    public ResponseEntity addPhoto(@RequestParam("photo") MultipartFile photo, HttpServletRequest servletRequest) {

        try{
            _userService.uploadPhotoProfile(photo, servletRequest);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
