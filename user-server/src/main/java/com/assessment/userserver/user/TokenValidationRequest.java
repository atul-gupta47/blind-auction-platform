package com.assessment.userserver.user;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class TokenValidationRequest {
	private Long userId;
	private String token;
}
