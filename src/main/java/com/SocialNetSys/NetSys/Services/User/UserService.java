package com.SocialNetSys.NetSys.Services.User;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Objects.User_Model;
import com.SocialNetSys.NetSys.Models.Responses.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Requests.UserRequest;
import com.SocialNetSys.NetSys.Models.Responses.FollowerResponse;
import com.SocialNetSys.NetSys.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository _userRepository;

    public String createUser(UserRequest request) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String hashedPassword = encoder.encode(request.password);

        var response = new User(request.name, request.email, hashedPassword);

        _userRepository.save(response);

        return response.getId().toString();
    }

    public FindUserResponse responseUserByEmail(String email) {
        var user = _userRepository.findUserByEmail(email).get();

        return new FindUserResponse(user.getId(), user.getName(), user.getEmail());
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

    public FollowerResponse saveNewFollower(UUID youId, UUID userFollowedId) {

        var youUserEntity = getUserByID(youId);
        var userThatGetNewFollowEntity = getUserByID(userFollowedId);

        var youUser = new User_Model(youUserEntity.getName(), youUserEntity.getId());
        var userThatGetNewFollow = new User_Model(userThatGetNewFollowEntity.getName(), userThatGetNewFollowEntity.getId());

        userThatGetNewFollowEntity.setFollower(youUser);
        youUserEntity.setFollowing(userThatGetNewFollow);

        _userRepository.save(youUserEntity);
        _userRepository.save(userThatGetNewFollowEntity);

        return new FollowerResponse(youUser.getName(), userThatGetNewFollow.getName());
    }
}
