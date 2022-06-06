package com.magnus.futbot.database.repositories;

import com.magnus.futbot.database.entities.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProfileRepository  extends MongoRepository<Profile, String> {

    Profile findByEmail(String email);
    List<Profile> findByUserId(String userId);
    Profile add(Profile profile);

}