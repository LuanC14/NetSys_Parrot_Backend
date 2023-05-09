package com.SocialNetSys.NetSys.Services.User;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Objects.User_Model;
import com.SocialNetSys.NetSys.Models.Requests.ChangeNameRequest;
import com.SocialNetSys.NetSys.Models.Requests.ChangePasswordRequest;
import com.SocialNetSys.NetSys.Models.Responses.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Requests.UserRequest;
import com.SocialNetSys.NetSys.Models.Responses.FollowerResponse;
import com.SocialNetSys.NetSys.Repositories.UserRepository;
import com.SocialNetSys.NetSys.Services.FileUpload.IFileUploadService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private IFileUploadService _fileUploadService;

    public String createUser(UserRequest request) {

        try {
            var checkEmailInUse = _userRepository.findUserByEmail(request.email).isPresent();
            var checkUsernameAlreadyInUse = _userRepository.findUserByUsername(request.username).isPresent();

            if(checkEmailInUse) {
                throw new RuntimeException("O email já está em uso");
            };

            if(checkUsernameAlreadyInUse) {
                throw  new RuntimeException("O @username já está em uso");
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            String hashedPassword = encoder.encode(request.password);

            var response = new User(request.name, request.email, request.username, hashedPassword);

            _userRepository.save(response);

            return response.getId().toString();

        } catch (Exception e) {
           throw  new RuntimeException("Não foi possível realizar o cadastro.");
        }
    }

    public FindUserResponse findUSerByEmail(String email) {
        var optionalUser = _userRepository.findUserByEmail(email);

        if(optionalUser.isPresent()) {
            var user = optionalUser.get();

            return new FindUserResponse(
                    user.getId(), user.getName(), user.getEmail(),
                    user.getUsername(), user.getFollowers(), user.getFollowing(), user.getBiography(), user.getAvatar());
        } else  {
            throw new RuntimeException("Usuáario não encontrado");
        }
    }

    public FindUserResponse findUserByUsername(String username) {
        var optionalUser = _userRepository.findUserByUsername(username);

        if(optionalUser.isPresent()) {
            var user = optionalUser.get();

            return new FindUserResponse(
                    user.getId(), user.getName(), user.getEmail(),
                    user.getUsername(), user.getFollowers(), user.getFollowing(), user.getBiography(), user.getAvatar());
        } else  {
            throw new RuntimeException("Usuáario não encontrado");
        }
    }
    public User getUserByEmail(String email) {
        var optionalUser = _userRepository.findUserByEmail(email);

        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new Error("Usuário não encontrado");
        }
    }

    public User getUserByID(UUID id) {

        return _userRepository.findById(id).get();
    }

    public void updateUserInDB(User user) {
        _userRepository.save(user);
    }

    public void changePassword(ChangePasswordRequest request) {

        var user = getUserByEmail(request.email);

        if(user == null) {
            throw new RuntimeException ("Email ou senha inválido");
        }

        var encoder = new BCryptPasswordEncoder();

        var checkPasswordMatch = encoder.matches(request.oldPassword, user.getPassword());

        if(checkPasswordMatch) {
            var newHashedPassword = encoder.encode(request.newPassword);

            user.setPassword(newHashedPassword);
            _userRepository.save(user);

        } else {
            throw  new RuntimeException("Email ou senha inválido");
        }

    }

    public void nameAndUsernameModifier(ChangeNameRequest request, HttpServletRequest servletRequest) {
        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");
        var user_id  = UUID.fromString(userIdFromRequest);

        var user = getUserByID(user_id);

        if(request.getName() != null && request.getUsername() != null) {
            user.setName(request.getName());
            user.setUsername(request.getUsername());
        }

        if(request.getName() == null && request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }

        if(request.getName() != null && request.getUsername() == null) {
            user.setName(request.getName());
        }

        if(request.getName() == null && request.getUsername() == null) {
            throw  new RuntimeException("Operação inválida");
        }

        _userRepository.save(user);
    }

    public void uploadPhotoProfile(MultipartFile photo, HttpServletRequest servletRequest) throws Exception {
    var userIdFromRequest = (String) servletRequest.getAttribute("user_id");
    var userId = UUID.fromString(userIdFromRequest);

    var user = _userRepository.findById(userId).get();

    var photoUri = "";

    try {

    var filename = user.getId() + "." + photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);

    photoUri = _fileUploadService.upload(photo, filename);

    } catch (Exception e) {
        throw new Exception(e.getMessage());
    }

        user.setAvatar(photoUri);
        _userRepository.save(user);
    }
}
