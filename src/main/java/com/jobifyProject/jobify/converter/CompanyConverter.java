package com.jobifyProject.jobify.converter;

import com.jobifyProject.jobify.dto.CompanyDto;
import com.jobifyProject.jobify.model.Company;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CompanyConverter {

    @Autowired
    private ModelMapper modelMapper;

    public CompanyDto modelToDto(Company company) {
        return modelMapper.map(company, CompanyDto.class);
    }

    public List<CompanyDto> modelToDto(List<Company> companyList) {
        return companyList.stream().map(company -> modelToDto(company)).collect(Collectors.toList());
    }

    public Company dtoToModel(CompanyDto companyDto) {
        return modelMapper.map(companyDto, Company.class);
    }

    public List<Company> dtoToModel(List<CompanyDto> companyDtoList) {
        return companyDtoList.stream().map(companyDto -> dtoToModel(companyDto)).collect(Collectors.toList());
    }
}
