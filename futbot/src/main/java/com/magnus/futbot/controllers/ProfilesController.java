package com.magnus.futbot.controllers;

import javax.servlet.annotation.HttpConstraint;

import com.magnus.futbot.services.ProfilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.magnus.futbot.dtos.ProfileDTO;

import java.util.List;

@RestController()
@RequestMapping("/profiles")
public class ProfilesController {

    @Autowired
    private ProfilesService profilesService;

    @GetMapping("/get-all")
    @HttpConstraint
    public ResponseEntity<List<ProfileDTO>> Get(@RequestParam String accessToken) {
        return new ResponseEntity<>(profilesService.geAll(), HttpStatus.OK);
    }

    @PostMapping("/add-profile")
    @HttpConstraint
    public ResponseEntity<ProfileDTO> AddProfile(@RequestBody ProfileDTO profileDTO) {
        return new ResponseEntity<>(profilesService.add(profileDTO), HttpStatus.OK);
    }
}
