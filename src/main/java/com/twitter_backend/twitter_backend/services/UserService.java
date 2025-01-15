package com.twitter_backend.twitter_backend.services;

import com.twitter_backend.twitter_backend.modals.ApplicationUser;
import com.twitter_backend.twitter_backend.modals.Role;
import com.twitter_backend.twitter_backend.repositories.RoleRepository;
import com.twitter_backend.twitter_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final RoleRepository roleRepo;

    @Autowired
    public UserService(UserRepository userRepo, RoleRepository roleRepo){
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    public ApplicationUser registerUser(ApplicationUser user) {
        Set<Role> roles = user.getAuthorities();
        roles.add(roleRepo.findRoleByAuthority("USER").get());
        user.setAuthorities(roles);

        return userRepo.save(user);
    }






















}
