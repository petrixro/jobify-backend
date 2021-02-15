package com.jobifyProject.jobify.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.service.CompanyService;
import com.jobifyProject.jobify.service.JobOfferService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CompanyControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    public CompanyService companyService;

    @Autowired
    public JobOfferService jobOfferService;

    @Test
    void getAllCompanies() throws Exception {

        Company company = Company.builder()
                .name("Test company name")
                .websiteLink("linkForTheCompany.com")
                .companyLogo("logoOfTheCompany")
                .build();

        companyService.addCompany(company);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/companies").
                contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is(company.getName())));

        companyService.deleteCompany(company.getId());
    }

    @Test
    void getCompanyById() throws Exception {
        Company company = Company.builder()
                .name("Test company name")
                .websiteLink("linkForTheCompany.com")
                .companyLogo("logoOfTheCompany")
                .build();

        companyService.addCompany(company);

                mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/companies/{id}", company.getId()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.id", is(company.getId().toString())));

        companyService.deleteCompany(company.getId());
    }

    @Test
    void getJobsByCompanyId() throws Exception {
        Company company = Company.builder()
                .name("Test company name")
                .websiteLink("linkForTheCompany.com")
                .companyLogo("logoOfTheCompany")
                .build();

        JobOffer jobOffer = JobOffer.builder()
                .name("Job offer test name")
                .description("description of the job")
                .applyLink("linkForApplication.com")
                .type("Type of job")
                .location("Location")
                .build();
        companyService.addCompany(company);
        jobOfferService.addJob(jobOffer, company.getId());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/companies/{id}/jobs", company.getId()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1))).
                andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(jobOffer.getId().toString())));

        jobOfferService.deleteJob(jobOffer.getId());
        companyService.deleteCompany(company.getId());
    }

    @Test
    void getJobByIdAndCompanyId() throws Exception {
        Company company = Company.builder()
                .name("Test company name")
                .websiteLink("linkForTheCompany.com")
                .companyLogo("logoOfTheCompany")
                .build();

        JobOffer jobOffer = JobOffer.builder()
                .name("Job offer test name")
                .description("description of the job")
                .applyLink("linkForApplication.com")
                .type("Type of job")
                .location("Location")
                .build();
        companyService.addCompany(company);
        jobOfferService.addJob(jobOffer, company.getId());

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/companies/{companyId}/jobs/{jobId}", company.getId(), jobOffer.getId()).
                        contentType(MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk()).
                andExpect(MockMvcResultMatchers.jsonPath("$.id", is(jobOffer.getId().toString()))).
                andExpect(MockMvcResultMatchers.jsonPath("$.company.id", is(company.getId().toString())));

        jobOfferService.deleteJob(jobOffer.getId());
        companyService.deleteCompany(company.getId());
    }

    @Test
    void addCompany() throws Exception {
        Company companyToSave = Company.builder()
                .name("Test company name")
                .websiteLink("linkForTheCompany.com")
                .companyLogo("logoOfTheCompany")
                .build();

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/companies").
                        contentType(MediaType.APPLICATION_JSON).
                        content(mapper.writeValueAsString(companyToSave))).
                andExpect(MockMvcResultMatchers.status().isOk());

        List<String> dbCompanies = new ArrayList<>();
        companyService.getAllCompanies().forEach(company -> dbCompanies.add(company.getName()));
        assertThat(dbCompanies).contains(companyToSave.getName());
    }

    @Test
    void updateCompanyById() {
    }

    @Test
    void deleteCompany() {
    }
}