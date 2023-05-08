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
                return "O email já está em uso";
            };

            if(checkUsernameAlreadyInUse) {
                return "O @username já está em uso";
            }

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            String hashedPassword = encoder.encode(request.password);

            var response = new User(request.name, request.email, request.username, hashedPassword);

            _userRepository.save(response);

            return response.getId().toString();

        } catch (Exception e) {
            return "Email já está em uso.";
        }
    }

    public FindUserResponse responseUserByEmail(String email) {
        var user = _userRepository.findUserByEmail(email).get();

        return new FindUserResponse(
                user.getId(), user.getName(), user.getEmail(),
                user.getUsername(), user.getFollowers(), user.getFollowing(), user.getBiography(), user.getAvatar());
    }

    public FindUserResponse responseUserByUsername(String username) {
        var user = _userRepository.findUserByUsername(username).get();

        return new FindUserResponse(
                user.getId(), user.getName(), user.getEmail(),
                user.getUsername(), user.getFollowers(), user.getFollowing(), user.getBiography(), user.getAvatar());
    }
    public User getUserByEmail(String email) {
        var optionalUser = _userRepository.findUserByEmail(email);

        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new Error("User Not Found");
        }
    }

    public User getUserByID(UUID id) {

        return _userRepository.findById(id).get();
    }

    public void saveBiographyInDB(UUID id, Biography_Model bio) {
        var user = _userRepository.findById(id).get();
        user.setBiography(bio);

        _userRepository.save(user);
    }

    public void saveAvatarInDB(String avatar, UUID userId) {

        var user = _userRepository.findById(userId).get();
        user.setAvatar(avatar);

        _userRepository.save(user);
    }

    public FollowerResponse followManager(UUID myId, UUID userFollowedId) {

        var myUserEntity = getUserByID(myId); // Minha entidadede usuário
        var followedUserEntity = getUserByID(userFollowedId); // A entidade do usuário a ser seguido

        var verifyIfImFollowing = myUserEntity.isFollowing(userFollowedId);

        if (verifyIfImFollowing) {
            throw new IllegalArgumentException("Usuário já está sendo seguido, o client lhe redirecionará pro serviço de unfollow");
        }

        var myUser = new User_Model(myUserEntity.getName(), myUserEntity.getId(), myUserEntity.getUsername()); // Objeto que será armazenado na Array de seguidores
        var followedUser = new User_Model(followedUserEntity.getName(), followedUserEntity.getId(), followedUserEntity.getUsername()); // E na array de quem estou Seguindo

        myUserEntity.startFollow(followedUser); // Salvando o usuário que estou seguindo na  Array de quem estou seguindo
        followedUserEntity.receiveFollow(myUser); // Salvando meus dados na Array de seguidores do usuário seguido.

        _userRepository.save(myUserEntity);
        _userRepository.save(followedUserEntity);

        return new FollowerResponse(myUser.getName(), followedUser.getName());
    }

    public FollowerResponse unfollowManager(UUID myId, UUID userFollowedId) {
        var myUserEntity = getUserByID(myId);
        var followedUserEntity = getUserByID(userFollowedId);

        var verifyIfImFollowing =  myUserEntity.isFollowing(userFollowedId);

        if(!verifyIfImFollowing) {
            throw new IllegalArgumentException("Você não está seguindo esse usuário, o client lhe encaminhará pro serviço de Follow");
        }

        myUserEntity.stopFollow(userFollowedId); // Removendo o usuário da minha lista de seguidores
        followedUserEntity.lostFollow(myId); // Me removndo daa lista de seguidor da pessoa

        _userRepository.save(myUserEntity);
        _userRepository.save(followedUserEntity);

        return new FollowerResponse(myUserEntity.getName(), followedUserEntity.getName());
    }

    public void changePassword(ChangePasswordRequest request) throws Error {

        var user = getUserByEmail(request.email);

        if(user == null) {
            throw new Error("Email ou senha inválidos");
        }

        var encoder = new BCryptPasswordEncoder();

        var checkPasswordMatch = encoder.matches(request.oldPassword, user.getPassword());

        if(checkPasswordMatch) {
            var newHashedPassword = encoder.encode(request.newPassword);

            user.setPassword(newHashedPassword);
            _userRepository.save(user);

        } else {
            throw  new Error("Email ou senha inválido");
        }

    }

    public void changeName(ChangeNameRequest request, HttpServletRequest servletRequest) {
        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");
        var user_id  = UUID.fromString(userIdFromRequest);

        var user = getUserByID(user_id);

        user.setName(request.name);

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
