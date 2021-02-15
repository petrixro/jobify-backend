package com.jobifyProject.jobify.repository;

import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Set<User> findUsersByLookingForJobIsTrue();
    Set<User> findUsersByWorkedAtIs(JobOffer jobOffer);
    User findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
