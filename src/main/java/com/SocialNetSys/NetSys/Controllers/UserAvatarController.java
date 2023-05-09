package com.SocialNetSys.NetSys.Controllers;

import com.SocialNetSys.NetSys.Services.User.IUserService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(description = "Altera foto de avatar (Perfil do usuário). Arquivo vem do MultipartForm e é necessário estar com o Token")
    public ResponseEntity<String> updateAvatarProfile(@RequestParam("photo") MultipartFile photo, HttpServletRequest servletRequest) {

            _userService.uploadPhotoProfile(photo, servletRequest);

            return ResponseEntity.ok().body("Foto atualizada");
    }
}
