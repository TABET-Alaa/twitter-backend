package com.twitter_backend.twitter_backend.services;

import com.twitter_backend.twitter_backend.exceptions.EmailAlreadyTakenException;
import com.twitter_backend.twitter_backend.modals.ApplicationUser;
import com.twitter_backend.twitter_backend.modals.RegistrationObject;
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


    public ApplicationUser getUserByUsername(String username){
        return userRepo.findByUsername(username).orElseThrow(UserDoesNotExistException::new);
    }
    public ApplicationUser registerUser(RegistrationObject ro) {

        ApplicationUser user = new ApplicationUser();

        user.setLastName(ro.getLastName());
        user.setFirstName(ro.getFirstName());
        user.setEmail(ro.getEmail());
        user.setDateOfBirth(
                ro.getDob()
        );

        String name = user.getFirstName() + user.getLastName();
        boolean nameTaken = true;
        String tempName = "";

        while(nameTaken){
            tempName = generateUsername(name);
            if(userRepo.findByUsername(tempName).isEmpty()){
                nameTaken = false;
            }
        }

        user.setUsername(tempName);

        Set<Role> roles = user.getAuthorities();
        roles.add(roleRepo.findRoleByAuthority("USER").get());
        user.setAuthorities(roles);

        try{
            return userRepo.save(user);
        }catch(Exception e){
             throw  new EmailAlreadyTakenException();

        }
        return userRepo.save(user);
    }

    private String generateUsername(String name){
        long GenerateNumber = (long) Math.floor(Math.random() * 1_000_000_000);
        return name+GenerateNumber ;
    }





















}
