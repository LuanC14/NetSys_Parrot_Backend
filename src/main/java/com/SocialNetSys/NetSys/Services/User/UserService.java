package com.SocialNetSys.NetSys.Services;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects_Model.Biography;
import com.SocialNetSys.NetSys.Models.Objects_Model.BiographyRequest;
import com.SocialNetSys.NetSys.Models.Objects_Model.FindUserResponse;
import com.SocialNetSys.NetSys.Models.Objects_Model.UserRequest;
import com.SocialNetSys.NetSys.Repositories.UserRepository;
import com.SocialNetSys.NetSys.Services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository _userRepository;

    public String createUser(UserRequest request) {
        var response = new User(request.name, request.email, request.password);

        _userRepository.save(response);

        return response.getId().toString();
    }

    public String createBiography(BiographyRequest request) {

        UUID user_id = UUID.fromString(request.getUserId());

        Optional<User> optionalUser =  _userRepository.findById(user_id);

        if(optionalUser.isPresent()) {
            User user = optionalUser.get();

            for(Biography b : request.biography){

                Biography bio = new Biography();
                bio.setUser_id(b.getUser_id());
                bio.setType(b.getType());
                bio.setValue(b.getValue());
                user.setBiography(bio);
            }

            _userRepository.save(user);

        } else {
            throw new Error("User Not Found");
        };

        return null;
    };

    public FindUserResponse findUserByEmail(String email) {
        var user = _userRepository.findUserByEmail(email).get();

        return new FindUserResponse(user.getId(), user.getName(), user.getEmail());
    }

    public User getUser(String email) {
       var optionalUSer = _userRepository.findUserByEmail(email);
       User user;

       if(optionalUSer.isPresent()) {
           return user = optionalUSer.get();
       } else {
           throw new Error("User Not Found");
       }
    }

}
