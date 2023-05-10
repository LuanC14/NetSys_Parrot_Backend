package com.SocialNetSys.NetSys.Services.Follows;

import com.SocialNetSys.NetSys.Models.Objects.User_Model;
import com.SocialNetSys.NetSys.Models.Responses.FollowerResponse;
import com.SocialNetSys.NetSys.Services.User.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class FollowService implements  IFollowService {
    @Autowired
    private IUserService _userService;

    public FollowerResponse followUser(HttpServletRequest servletRequest, UUID userFollowedId) throws Exception {

        try{
            var userIdFromRequest = (String) servletRequest.getAttribute("user_id"); // Meu ID

            var myId = UUID.fromString(userIdFromRequest);

            if(myId == userFollowedId) {
                throw new RuntimeException("Você não pode dar follow em você mesmo");
            }

            var myUserEntity = _userService.getUserByID(myId);
            var followedUserEntity = _userService.getUserByID(userFollowedId);

            if(followedUserEntity == null) {
                throw new RuntimeException("Usuário a ser seguido não encontrado");
            }

            var verifyIfImFollowing = myUserEntity.isFollowing(userFollowedId);

            if (verifyIfImFollowing) {
                throw new RuntimeException("Usuário já está sendo seguido, o client lhe redirecionará pro serviço de unfollow");
            }

            var myUser = new User_Model(myUserEntity.getName(), myUserEntity.getId(), myUserEntity.getUsername());
            var followedUser = new User_Model(followedUserEntity.getName(), followedUserEntity.getId(), followedUserEntity.getUsername());

            myUserEntity.startFollow(followedUser);
            followedUserEntity.receiveFollow(myUser);

            _userService.updateUserInDB(myUserEntity);
            _userService.updateUserInDB(followedUserEntity);

            return new FollowerResponse(myUser.getName(), followedUser.getName());

        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public FollowerResponse unfollowUser(HttpServletRequest servletRequest, UUID userFollowedId) throws Exception {

        try{
            var myIdFromRequest = (String) servletRequest.getAttribute("user_id");

            var myId = UUID.fromString(myIdFromRequest);

            var myUserEntity = _userService.getUserByID(myId);
            var followedUserEntity = _userService.getUserByID(userFollowedId);

            var verifyIfImFollowing =  myUserEntity.isFollowing(userFollowedId);

            if(!verifyIfImFollowing) {
                throw new RuntimeException("Você não está seguindo esse usuário, o client lhe encaminhará pro serviço de Follow");
            }

            myUserEntity.stopFollow(userFollowedId);
            followedUserEntity.lostFollow(myId);

            _userService.updateUserInDB(myUserEntity);
            _userService.updateUserInDB(followedUserEntity);

            return new FollowerResponse(myUserEntity.getName(), followedUserEntity.getName());

        } catch (Exception e) {
            throw  new Exception(e.getMessage());
        }
    }
}   
