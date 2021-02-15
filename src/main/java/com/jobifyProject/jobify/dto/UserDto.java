package com.jobifyProject.jobify.dto;

import com.jobifyProject.jobify.model.Skill;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDto {

    private UUID id;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    private String experience;
    private String age;
    private String image;
    private Set<String> roles;
    private String jobRole;
    private boolean lookingForJob;
    private String gender;
}
