package com.example._test.repository;

import com.example._test.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository  extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleName(String user);
}
