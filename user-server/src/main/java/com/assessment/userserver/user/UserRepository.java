package com.assessment.userserver.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
	// Custom queries can be added here if needed
}