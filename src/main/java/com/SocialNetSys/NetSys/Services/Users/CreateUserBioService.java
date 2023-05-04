package com.SocialNetSys.NetSys.Services.Users;

import com.SocialNetSys.NetSys.Models.Entities.User;
import com.SocialNetSys.NetSys.Models.Objects_Model.Biography;
import com.SocialNetSys.NetSys.Models.Objects_Model.BiographyRequest;
import com.SocialNetSys.NetSys.Repositories.UserRepository;
import com.SocialNetSys.NetSys.Services.Users.Interfaces.ICreateUserBioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class CreateUserBioService implements ICreateUserBioService {
    @Autowired
    private UserRepository _userRepository;
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
    }
}
