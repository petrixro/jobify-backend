package com.jobifyProject.jobify.repository;

import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.JobOffer;
import com.jobifyProject.jobify.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<JobOffer, UUID> {
    List<JobOffer> findAllByCompanyId(UUID id);

    @Query(
            value = "SELECT j FROM JobOffer j WHERE j.id = :jobId AND j.company = :company")
    JobOffer findByIdAndCompany(@Param("company") Company company, @Param("jobId") UUID jobId);

    Set<JobOffer> findJobOfferByUsersStartsWith(User user);

    Set<JobOffer> findJobOffersByLocationContainingIgnoreCase(String location);

    Set<JobOffer> findJobOffersByNameContainingIgnoreCaseAndLocationContainingIgnoreCase(String name,String location);

    List<JobOffer> findJobOfferByNameContainingIgnoreCase(String name);

}
