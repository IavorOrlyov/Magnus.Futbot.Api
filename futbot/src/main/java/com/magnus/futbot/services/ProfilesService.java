package com.magnus.futbot.services;

import com.magnus.futbot.database.entities.Profile;
import com.magnus.futbot.database.repositories.ProfileRepository;
import com.magnus.futbot.dtos.ProfileDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

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
        Profile profile = mapper.map(profileDTO, Profile.class);
        profilesRepository.add(profile);
        return mapper.map(profile, ProfileDTO.class);
    }
}
