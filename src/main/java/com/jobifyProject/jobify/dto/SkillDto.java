package com.jobifyProject.jobify.dto;

import com.jobifyProject.jobify.model.User;
import lombok.Data;

import java.util.UUID;

@Data
public class SkillDto {
    private UUID id;
    private String name;
}
