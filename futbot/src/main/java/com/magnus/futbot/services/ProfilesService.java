package com.magnus.futbot.services;

import com.magnus.futbot.database.entities.ProfileDocument;
import com.magnus.futbot.database.repositories.ProfileRepository;
import com.magnus.futbot.dtos.ProfileDTO;
import com.magnus.futbot.helpers.AppSettings;
import com.magnus.futbot.selenium.LoginService;
import com.magnus.futbot.selenium.models.LoginResponseDTO;
import com.magnus.futbot.selenium.models.LoginResponseType;
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
    @Autowired
    private LoginService loginService;


    public ProfilesService() {
        mapper = new ModelMapper();
    }

    public LoginResponseDTO add(ProfileDTO profileDTO) {
        ProfileDocument profileDocument = mapper.map(profileDTO, ProfileDocument.class);
        profileDocument.setCreateDate(new Date());
        profileDocument.setUserId(appSettings.getUserId());
        profilesRepository.insert(profileDocument);

        LoginResponseDTO loginResponseDTO = null;
        try {
            loginResponseDTO = loginService.Login(profileDTO.getEmail(), profileDTO.getPassword());
        } catch (InterruptedException e) {
            loginResponseDTO = new LoginResponseDTO(LoginResponseType.UnknownError);
            System.out.println("Login was unsuccessful");
        }

        return loginResponseDTO;
    }

    public List<ProfileDTO> geAll() {
        List<ProfileDTO> result = new ArrayList<>();
        for (ProfileDocument profileDocument : profilesRepository.findAllByUserId(appSettings.getUserId())) {
            result.add(mapper.map(profileDocument, ProfileDTO.class));
        }

        return result;
    }

}
