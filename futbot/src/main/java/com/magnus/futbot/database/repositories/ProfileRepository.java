package com.magnus.futbot.database.repositories;

import com.magnus.futbot.database.entities.ProfileDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends MongoRepository<ProfileDocument, String> {

}