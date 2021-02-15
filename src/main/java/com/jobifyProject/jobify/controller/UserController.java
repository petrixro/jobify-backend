package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.converter.JobOfferConverter;
import com.jobifyProject.jobify.converter.SkillConverter;
import com.jobifyProject.jobify.converter.UserConverter;
import com.jobifyProject.jobify.dto.JobOfferDto;
import com.jobifyProject.jobify.dto.SkillDto;
import com.jobifyProject.jobify.dto.UserDto;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.Skill;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private JobOfferConverter jobOfferConverter;

    @Autowired
    private SkillConverter skillConverter;

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return userConverter.modelToDto(users);
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return userConverter.modelToDto(user);
    }

    @GetMapping("/users/lookingForJob")
    public Set<UserDto> getUsersLookingForJob() {
        Set<User> users = userService.getUsersLookingForJob();
        return userConverter.modelToDto(users);
    }

    @PutMapping("/users/lookingForJob/{id}")
    public void setUserLookingForJob(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        user.setLookingForJob(!user.getLookingForJob());
        userService.updateUserById(id, user);
    }

    @GetMapping("/users/{userId}/skills")
    public Set<SkillDto> getSkillsOfUser(@PathVariable UUID userId) {
        Set<Skill> skills = userService.findSkillsOfUser(userId);
        return skillConverter.modelToDto(skills);
    }

    @GetMapping("/users/{id}/favoriteJobs")
    public Set<JobOfferDto> getFavoriteJobs(@PathVariable UUID id) {
        Set<JobOffer> jobOffers = userService.getFavoriteJobs(id);
        return jobOfferConverter.modelToDto(jobOffers);
    }

    @GetMapping("/users/{id}/appliedJobs")
    public Set<JobOfferDto> getAppliedJobs(@PathVariable UUID id) {
        Set<JobOffer> jobOffers = userService.getAppliedJobs(id);
        return jobOfferConverter.modelToDto(jobOffers);
    }

    @PutMapping("/users/{id}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserDto updateUserById(@PathVariable UUID id, @RequestBody UserDto userDto) {
        User user = userConverter.dtoToModel(userDto);
        User updatedUser = userService.updateUserById(id, user);

        return userConverter.modelToDto(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}

