package com.jobifyProject.jobify.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(
        name = "companies",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "email"),
        }
)
public class Company {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Size(max = 20)
    private String name;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    private String websiteLink;
    private String companyLogo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "company_roles",
            joinColumns = @JoinColumn(name = "company_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<JobOffer> jobOffers = new HashSet<>();

    public Company(
            @NotBlank @Size(max = 20) String name,
            @NotBlank @Size(max = 50) @Email String email,
            @NotBlank @Size(max = 120) String password
    ) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
