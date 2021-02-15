package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.exception.UserNotFoundException;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.Skill;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.repository.JobRepository;
import com.jobifyProject.jobify.repository.SkillRepository;
import com.jobifyProject.jobify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private SkillRepository skillRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).
                orElseThrow(() -> new UserNotFoundException(id));
    }

    public Set<JobOffer> getFavoriteJobs(UUID id) {
        User user = getUserById(id);
        return jobRepository.findJobOfferByUsersStartsWith(user);
    }

    public Set<JobOffer> getAppliedJobs(UUID id) {
        User user = getUserById(id);
        return user.getAppliedJobs();
    }

    public Set<Skill> findSkillsOfUser(UUID userId){
        User user = getUserById(userId);
        return skillRepository.findSkillsByUserIs(user);
    }

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User updateUserById(UUID id, User updatedUser) {
        User user = getUserById(id);
        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setExperience(updatedUser.getExperience());
        user.setAge(updatedUser.getAge());
        user.setImage(updatedUser.getImage());
        user.setSkills(updatedUser.getSkills());
        user.setJobRole(updatedUser.getJobRole());
        return userRepository.save(user);
    }

    public void deleteUser(UUID id) {
        userRepository.delete(getUserById(id));
    }

    public Set<User> getUsersLookingForJob() {
        return userRepository.findUsersByLookingForJobIsTrue();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
