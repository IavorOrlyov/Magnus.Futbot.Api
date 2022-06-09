package com.magnus.futbot.services;

import com.magnus.futbot.database.entities.ProfileDocument;
import com.magnus.futbot.database.repositories.ProfileRepository;
import com.magnus.futbot.dtos.ProfileDTO;
import com.magnus.futbot.helpers.AppSettings;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class ProfilesService {

    private final ModelMapper mapper;
    @Autowired
    private ProfileRepository profilesRepository;
    @Autowired
    private AppSettings appSettings;


    public ProfilesService() {
        mapper = new ModelMapper();
    }

    public ProfileDTO add(ProfileDTO profileDTO) {
        ProfileDocument profileDocument = mapper.map(profileDTO, ProfileDocument.class);
        profileDocument.setCreateDate(new Date());
        profileDocument.setUserId(appSettings.getUserId());
        profilesRepository.insert(profileDocument);
        return mapper.map(profileDocument, ProfileDTO.class);
    }

    public List<ProfileDTO> geAll() {
        List<ProfileDTO> result = new ArrayList<>();
        for (ProfileDocument profileDocument : profilesRepository.findAllByUserId(appSettings.getUserId())) {
            result.add(mapper.map(profileDocument, ProfileDTO.class));
        }

        return result;
    }

}
