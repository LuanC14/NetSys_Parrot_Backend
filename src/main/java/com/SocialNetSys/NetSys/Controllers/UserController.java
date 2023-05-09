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

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private IUserService _userService;

    @GetMapping("/username")
    public ResponseEntity<FindUserResponse> findUserByUsername(String username) {

        return ResponseEntity.ok().body(_userService.findUserByUsername(username));
    }

    @GetMapping("/email")
    public ResponseEntity<FindUserResponse> findUserByEmail(String email) {
        return ResponseEntity.ok().body(_userService.findUSerByEmail(email));
    }

    @PostMapping("/signup")
    ResponseEntity<String> userCreator(@RequestBody UserRequest request) {
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/newpassword")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {

        _userService.changePassword(request);

        return ResponseEntity.ok().body("Senha alterada com sucesso");
    };

    @PutMapping("/rename")
    public ResponseEntity<String> changeNames(@RequestBody ChangeNameRequest request, HttpServletRequest servletRequest ) {
        _userService.nameAndUsernameModifier(request, servletRequest);

        return ResponseEntity.ok().body("Nome alterado com suceso!");
    };
}
