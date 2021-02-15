package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.exception.SkillNotFoundException;
import com.jobifyProject.jobify.exception.UserNotFoundException;
import com.jobifyProject.jobify.model.Skill;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.repository.SkillRepository;
import com.jobifyProject.jobify.repository.UserRepository;
import org.checkerframework.checker.nullness.Opt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class SkillService {
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private UserRepository userRepository;

    public Set<Skill> getAllSkills() {
        return skillRepository.getAll();
    }

    public Set<Skill> getAllSkillsOfUser(User user) {
        return skillRepository.findSkillsByUserIs(user);
    }

    public void addSkill(UUID userId, Skill skill) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new UserNotFoundException(userId));
        user.getSkills().add(skill);
        skill.setUser(user);

        skillRepository.save(skill);
        userRepository.save(user);
    }

    public void deleteSkill(UUID skillId) {
        Skill skill = skillRepository.findById(skillId).
                orElseThrow(() -> new SkillNotFoundException(skillId));
        skillRepository.delete(skill);
    }
}
