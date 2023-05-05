package com.SocialNetSys.NetSys.Controllers.User;

import com.SocialNetSys.NetSys.Models.Objects_Model.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Objects_Model.UserRequest;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IUserService _userService;

    @PostMapping("/signup")
    ResponseEntity<String> userCreator(@RequestBody UserRequest request) {
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PreAuthorize("Authorized")
    @GetMapping()
    public ResponseEntity<FindUserResponse> getUser(String email) {

        return ResponseEntity.ok().body(_userService.responseUserByEmail(email));
    }
}
