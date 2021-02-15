package com.jobifyProject.jobify.repository;

import com.jobifyProject.jobify.model.Skill;
import com.jobifyProject.jobify.model.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<Skill, UUID> {

    @Query(
            value = "SELECT s FROM Skill s")
    Set<Skill> getAll();

    Set<Skill> findSkillsByUserIs(User user);
}
