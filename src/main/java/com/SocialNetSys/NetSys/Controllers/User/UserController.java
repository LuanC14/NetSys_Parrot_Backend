package com.SocialNetSys.NetSys.Controllers.User;

import com.SocialNetSys.NetSys.Models.Objects_Model.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Objects_Model.UserRequest;
import com.SocialNetSys.NetSys.Services.Users.Interfaces.ICreateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    @Autowired
    private ICreateUserService _userService;

    @PostMapping()
    ResponseEntity<String> userCreator(@RequestBody UserRequest request) {
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<FindUserResponse> getUser(String email) {

        return ResponseEntity.ok().body(_userService.findUserByEmail(email));
    }
}
