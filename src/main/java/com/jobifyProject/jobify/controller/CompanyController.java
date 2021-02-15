package com.jobifyProject.jobify.controller;

import com.jobifyProject.jobify.converter.CompanyConverter;
import com.jobifyProject.jobify.converter.JobOfferConverter;
import com.jobifyProject.jobify.dto.CompanyDto;
import com.jobifyProject.jobify.dto.JobOfferDto;
import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:3000")
public class CompanyController {

    @Autowired
    private CompanyConverter companyConverter;

    @Autowired
    private JobOfferConverter jobOfferConverter;

    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<CompanyDto> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return companyConverter.modelToDto(companies);
    }

    @GetMapping("/companies/{id}")
    public CompanyDto getCompanyById(@PathVariable UUID id) {
        Company company = companyService.getCompanyById(id);
        return companyConverter.modelToDto(company);
    }

    @GetMapping("/companies/{id}/jobs")
    public List<JobOfferDto> getJobsByCompanyId(@PathVariable UUID id) {
        List<JobOffer> jobOffers = companyService.getJobsByCompanyId(id);
        return jobOfferConverter.modelToDto(jobOffers);
    }

    @GetMapping("/companies/{companyId}/jobs/{jobId}")
    public JobOfferDto getJobByIdAndCompanyId(@PathVariable UUID companyId, @PathVariable UUID jobId) {
        JobOffer jobOffer = companyService.getJobByIdAndCompanyId(companyId, jobId);
        return jobOfferConverter.modelToDto(jobOffer);
    }

    @PutMapping("/companies/{id}")
//    @PreAuthorize("hasRole('COMPANY')")
    public CompanyDto updateCompanyById(@PathVariable UUID id, @RequestBody CompanyDto companyDto) {
        Company company = companyConverter.dtoToModel(companyDto);
        Company updatedCompany = companyService.updateCompanyById(id, company);
        return companyConverter.modelToDto(updatedCompany);
    }

    @DeleteMapping("/companies/{id}")
    @PreAuthorize("hasRole('COMPANY') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteCompany(@PathVariable UUID id) {
        companyService.deleteCompany(id);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
