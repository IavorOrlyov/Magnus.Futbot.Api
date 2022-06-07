package com.magnus.futbot.services;

import com.magnus.futbot.database.entities.ProfileDocument;
import com.magnus.futbot.database.repositories.ProfileRepository;
import com.magnus.futbot.dtos.ProfileDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
public class ProfilesService {
    private final ModelMapper mapper;
    private final ProfileRepository profilesRepository;

    @Autowired
    public ProfilesService(ProfileRepository profilesRepository) {
        this.mapper = new ModelMapper();
        this.profilesRepository = profilesRepository;
    }

    public ProfileDTO add(ProfileDTO profileDTO) {
        ProfileDocument profileDocument = mapper.map(profileDTO, ProfileDocument.class);
        profilesRepository.add(profileDocument);
        return mapper.map(profileDocument, ProfileDTO.class);
    }

    public List<ProfileDTO> geAll() {
        List<ProfileDTO> result = new ArrayList<>();
        for (ProfileDocument profileDocument : profilesRepository.getAll().find()) {
            result.add(mapper.map(profileDocument, ProfileDTO.class));
        }

        return result;
    }

}
