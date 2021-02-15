package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.model.EnumRole;
import com.jobifyProject.jobify.model.Role;
import com.jobifyProject.jobify.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findByName(EnumRole enumRole) {
        return roleRepository.findByName(enumRole)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
    }

    public void saveAll(List<Role> roles) {
        roleRepository.saveAll(roles);
    }
}
