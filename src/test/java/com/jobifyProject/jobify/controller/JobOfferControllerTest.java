package com.jobifyProject.jobify.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.service.JobOfferService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;


@SpringBootTest
@AutoConfigureMockMvc
class JobOfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JobOfferService jobOfferService;

    private List<JobOffer> jobOfferList;
    private final ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void init() {
        this.jobOfferList = new ArrayList<>();

        JobOffer jobOffer1 = JobOffer.builder()
                .name("Fullstack Engineer")
                .location("Bucharest")
                .type("Full time")
                .description("Description of the job")
                .applyLink("www.google.com")
                .build();

        JobOffer jobOffer2 = JobOffer.builder()
                .name("Senior Engineer")
                .location("San Francisco")
                .type("Full time")
                .description("Description of the job")
                .applyLink("www.lyft.com")
                .build();

        jobOfferList.addAll(List.of(jobOffer1, jobOffer2));
    }

    @Test
    void getAllJobs() throws Exception {
        given(jobOfferService.getAllJobOffers()).willReturn(jobOfferList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/jobs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", is(jobOfferList.size())));
    }

    @Test
    void getJobById() {
    }

    @Test
    void getJobOfferApplicants() {
    }

    @Test
    void addJob() throws Exception {

        UUID companyId = UUID.fromString("15f48708-6c53-4e66-bfc3-189c963a2937");
        JobOffer job = jobOfferList.get(0);

        given(jobOfferService.addJob(job, companyId)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/companies/{company_id}/jobs", companyId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(jobOfferList.get(0))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateJobById() {
    }

    @Test
    void getJobByLocation() {
    }

    @Test
    void getJobByName() {
    }

    @Test
    void getJobByNameAndLocation() {
    }

    @Test
    void deleteJob() {
    }
}