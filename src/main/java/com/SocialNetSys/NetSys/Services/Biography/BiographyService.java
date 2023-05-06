package com.SocialNetSys.NetSys.Services.Biography;

import com.SocialNetSys.NetSys.Models.Objects.Biography_Model;
import com.SocialNetSys.NetSys.Models.Requests.BiographyRequest;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import java.util.UUID;
@Service
public class BiographyService implements IBiographyService {
    @Autowired
    private IUserService _userService;

    public void createBiography(BiographyRequest request, HttpServletRequest servletRequest) {

        var userIdFromRequest = (String) servletRequest.getAttribute("user_id");

        UUID id = UUID.fromString(userIdFromRequest);

        for(Biography_Model b : request.biography) {
            var bio = new Biography_Model();

            bio.setType(b.getType());
            bio.setValue(b.getValue());
            bio.setUser_id(id);

            _userService.saveBiographyInDB(id, bio);
        }
    };
}
