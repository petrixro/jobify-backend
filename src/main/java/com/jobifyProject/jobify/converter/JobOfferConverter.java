package com.jobifyProject.jobify.converter;

import com.jobifyProject.jobify.dto.JobOfferDto;
import com.jobifyProject.jobify.model.JobOffer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JobOfferConverter {

    @Autowired
    private ModelMapper modelMapper;
    
    public JobOfferDto modelToDto(JobOffer jobOffer) {
        return modelMapper.map(jobOffer, JobOfferDto.class);
    }

    public List<JobOfferDto> modelToDto(List<JobOffer> jobOfferList) {
        return jobOfferList.stream().map(this::modelToDto).collect(Collectors.toList());
    }

    public Set<JobOfferDto> modelToDto(Set<JobOffer> jobOfferSet) {
        return jobOfferSet.stream().map(this::modelToDto).collect(Collectors.toSet());
    }

    public JobOffer dtoToModel(JobOfferDto jobOfferDto) {
        return modelMapper.map(jobOfferDto, JobOffer.class);
    }

    public List<JobOffer> dtoToModel(List<JobOfferDto> jobOfferDtoList) {
        return jobOfferDtoList.stream().map(this::dtoToModel).collect(Collectors.toList());
    }
}

