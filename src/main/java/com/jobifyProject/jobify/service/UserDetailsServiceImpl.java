package com.jobifyProject.jobify.service;

import com.jobifyProject.jobify.model.Company;
import com.jobifyProject.jobify.model.User;
import com.jobifyProject.jobify.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyService companyService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {

        User user = userService.findByUsername(username);
        Company company = companyService.findByName(username);

        if (user != null) {
            return UserDetailsImpl.buildUser(user);
        }
        return UserDetailsImpl.buildCompany(company);
    }
}
