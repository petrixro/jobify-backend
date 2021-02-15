package com.jobifyProject.jobify.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "job_offers")
public class JobOffer {

    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String description;
    private String applyLink;
    @CreationTimestamp
    private Date publishedDate;
    private String type;
    private String location;
//    @Enumerated(EnumType.STRING)
//    private JobOfferStates state;

    @ManyToMany(mappedBy = "workedAt")
    private Set<User> employed;

    @ManyToOne
    private Company company;

    @ManyToMany(mappedBy = "appliedJobs")
    private Set<User> applicants = new HashSet<>();

    @ManyToMany(mappedBy = "favoriteJobOffers",fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @Override
    public String toString(){
        return "JobOffer [id=" + id + ", name=" + name + "]";
    }
}