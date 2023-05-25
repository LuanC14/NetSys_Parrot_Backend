package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Requests.ChangeNameRequest;
import com.SocialNetSys.NetSys.Models.Requests.ChangePasswordRequest;
import com.SocialNetSys.NetSys.Models.Responses.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Requests.UserRequest;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
    @Autowired
    private IUserService _userService;


    @GetMapping(path = "/all/{name}")
    public ResponseEntity<List<FindUserResponse>> getAllUsers(@PathVariable String name) {

       var response = _userService.getAllUserByNameOrUsername(name);

       return ResponseEntity.ok(response);
    }

    @GetMapping("/username")
    @Operation(description = "Realiza busca dos dados do usuário pelo username (nickname único).")
    public ResponseEntity<FindUserResponse> findUserByUsername(String username) {

        return ResponseEntity.ok().body(_userService.findUserByUsername(username));
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping("/email")
    @Operation(description = "Realiza busca de usuário pelo ID")
    public ResponseEntity<FindUserResponse> findUserByEmail(String email) {

        return ResponseEntity.ok().body(_userService.findUSerByEmail(email));
    }

    @PostMapping("/signup")
    @Operation(description = "Realiza o cadastro de usuário, são necessários nome, username, email e senha")
    ResponseEntity<String> userCreator(@RequestBody UserRequest request) {
        var response = _userService.createUser(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PatchMapping("/newpassword")
    @Operation(description = "Redefine a senha do usuário.")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request) {

        _userService.changePassword(request);

        return ResponseEntity.ok().body("Senha alterada com sucesso");
    };

    @PutMapping("/rename")
    @Operation(description = "Esse método redefine o nome e username. Pode ser também somente um ou outro. É necessário Token")
    public ResponseEntity<String> changeNames(@RequestBody ChangeNameRequest request, HttpServletRequest servletRequest ) {
        _userService.nameAndUsernameModifier(request, servletRequest);

        return ResponseEntity.ok().body("Nome alterado com suceso!");
    };
}
