package com.jobifyProject.jobify.converter;

import com.jobifyProject.jobify.dto.SkillDto;
import com.jobifyProject.jobify.model.Skill;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SkillConverter {

    @Autowired
    private ModelMapper modelMapper;

    public SkillDto modelToDto(Skill skill) {
        return modelMapper.map(skill, SkillDto.class);
    }

    public Set<SkillDto> modelToDto(Set<Skill> skillList){
        return skillList.stream().map(this::modelToDto).collect(Collectors.toSet());
    }

    public List<SkillDto> modelToDto(List<Skill> skillList){
        return skillList.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public Skill dtoToModel(SkillDto skillDto) {
        return modelMapper.map(skillDto, Skill.class);
    }
}
