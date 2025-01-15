package com.twitter_backend.twitter_backend.repositories;

import com.twitter_backend.twitter_backend.modals.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findRoleByAuthority(String authority);
}
