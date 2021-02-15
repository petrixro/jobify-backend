package com.jobifyProject.jobify.dto;

import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.JobOfferStates;
import com.jobifyProject.jobify.model.User;
import lombok.Data;

import java.sql.Date;
import java.util.UUID;

@Data
public class JobOfferDto {

    private UUID id;
    private String name;
    private String description;
    private String applyLink;
    private Date publishedDate;
    private String type;
    private String location;
    private JobOfferStates state;
    private User employed;
    private Company company;
}
