package com.assessment.auctionserver;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;

@Component
public class ApiFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

		String userToken = httpServletRequest.getHeader("user-token");

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		try {
			HttpEntity<String> requestEntity = new HttpEntity<>(userToken, headers);
			ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:9002/users/validate-token", requestEntity, String.class);

		} catch (HttpClientErrorException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User validation failed");
		}


		filterChain.doFilter(httpServletRequest, servletResponse);
	}

	@Bean
	public FilterRegistrationBean<ApiFilter> myFilterRegistration() {
		FilterRegistrationBean<ApiFilter> registrationBean
				= new FilterRegistrationBean<>();

		registrationBean.setFilter(new ApiFilter());

		return registrationBean;
	}
}