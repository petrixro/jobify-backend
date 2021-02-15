package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.exception.CompanyNotFoundException;
import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.repository.CompanyRepository;
import com.jobifyProject.jobify.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JobRepository jobRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(UUID id) {
        return companyRepository.findById(id).
                orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public List<JobOffer> getJobsByCompanyId(UUID id) {
        return jobRepository.findAllByCompanyId(id);
    }

    public JobOffer getJobByIdAndCompanyId(UUID companyId, UUID jobId) {
        Company company = getCompanyById(companyId);
        return jobRepository.findByIdAndCompany(company,jobId);
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public Company updateCompanyById(UUID id, Company updatedCompany) {
        Company company = getCompanyById(id);
        company.setName(updatedCompany.getName());
        company.setEmail(updatedCompany.getEmail());
        company.setWebsiteLink(updatedCompany.getWebsiteLink());
        company.setCompanyLogo(updatedCompany.getCompanyLogo());
        return companyRepository.save(company);
    }

    public void deleteCompany(UUID id) {
        jobRepository.deleteAll(getJobsByCompanyId(id));
        companyRepository.delete(getCompanyById(id));
    }

    public Company findByName(String name) {
        return companyRepository.findByName(name);
    }

    public Boolean existsByName(String name) {
        return companyRepository.existsByName(name);
    }

    public Boolean existsByEmail(String email) {
        return companyRepository.existsByEmail(email);
    }
}