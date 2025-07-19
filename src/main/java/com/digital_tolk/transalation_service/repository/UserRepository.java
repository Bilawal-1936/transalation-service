package com.digital_tolk.transalation_service.repository;

import com.digital_tolk.transalation_service.entity.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDomain, Long> {
    Optional<UserDomain> findByUsername(String username);
}