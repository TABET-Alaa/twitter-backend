package com.twitter_backend.twitter_backend;

import com.twitter_backend.twitter_backend.modals.ApplicationUser;
import com.twitter_backend.twitter_backend.modals.Role;
import com.twitter_backend.twitter_backend.repositories.RoleRepository;
import com.twitter_backend.twitter_backend.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class  TwitterBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwitterBackendApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepo, UserService userService){
		return args -> {
			roleRepo.save(new Role(1, "USER"));
			ApplicationUser u = new ApplicationUser();
			u.setFirstName("unknown");
			u.setLastName("tabet");

			userService.registerUser(u);
		};
	}













}
