package com.jobifyProject.jobify.model;

import lombok.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;


@Setter @Getter
@NoArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }
)
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;
    private String age;
    private String experience;
    private Boolean lookingForJob;
    private String image;
    private String JobRole;
    private String gender;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

    public User(
            @NotBlank @Size(max = 20) String username,
            @NotBlank @Size(max = 50) @Email String email,
            @NotBlank @Size(max = 120) String password
    ) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @ManyToMany
    private List<JobOffer> workedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<JobOffer> favoriteJobOffers;

    @ManyToMany
    private Set<JobOffer> appliedJobs = new HashSet<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private Set<Skill> skills = new HashSet<>();

    @Override
    public String toString(){
        return "User [id=" + id + ", name=" + username + "]";
    }
}
