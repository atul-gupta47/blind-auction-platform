package com.assessment.userserver.user;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TokenValidationRequest {
	private UUID userId;
	private String token;
}
