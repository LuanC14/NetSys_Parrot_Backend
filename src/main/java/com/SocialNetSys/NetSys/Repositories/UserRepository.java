package com.SocialNetSys.NetSys.Repositories;

import com.SocialNetSys.NetSys.Models.Entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends MongoRepository<User, UUID> {

    Optional<User> findUserByEmail (String email);
}
