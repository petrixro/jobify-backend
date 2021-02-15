package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.converter.SkillConverter;
import com.jobifyProject.jobify.dto.SkillDto;
import com.jobifyProject.jobify.model.Skill;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.service.SkillService;
import com.jobifyProject.jobify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class SkillController {

    @Autowired
    private SkillConverter skillConverter;

    @Autowired
    private SkillService skillService;

    @Autowired
    private UserService userService;

    @GetMapping("/skills")
    public Set<SkillDto> getAllSkills() {
        Set<Skill> skillList = skillService.getAllSkills();
        return skillConverter.modelToDto(skillList);
    }

    @GetMapping("/user/{id}/skills")
    public Set<SkillDto> getAllSkillsOfUser(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        Set<Skill> skills = skillService.getAllSkillsOfUser(user);
        return skillConverter.modelToDto(skills);
    }

    @PostMapping("/skills/{userId}")
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void addSkill(@PathVariable UUID userId, @RequestBody SkillDto skillDto) {
        Skill skill = skillConverter.dtoToModel(skillDto);
        skillService.addSkill(userId, skill);
    }

    @DeleteMapping("/skills/{skillId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteSkill(@PathVariable UUID skillId) {
        skillService.deleteSkill(skillId);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
