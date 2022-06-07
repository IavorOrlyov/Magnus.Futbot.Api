package com.magnus.futbot.database.repositories;

import com.magnus.futbot.database.entities.ProfileDocument;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.InsertOneResult;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfileRepository extends BaseRepository<ProfileDocument> {

    public ProfileDocument findByEmail(String email) {
        return null;
    }

    public List<ProfileDocument> findByUserId(String userId) {
        return null;
    }

    public ProfileDocument add(ProfileDocument profile) {
        MongoCollection collection = getCollection();
        InsertOneResult result = collection.insertOne(profile.toDocument(profile));
        profile.setId(result.getInsertedId().asObjectId().getValue());
        return profile;
    }

    public MongoCollection<ProfileDocument> getAll() {
        return getCollection();
    }

    @Override
    protected MongoCollection getCollection() {
        return database.getCollection("profile");
    }
}