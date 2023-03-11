package com.bikes.backend.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("GET /api/csrf/")
class CsrfControllerTest {
	@Autowired
	MockMvc mockMvc;

	@Test
	@WithMockUser
	@DisplayName("...should return a string with the value 'CSRF Ok' and contain the 'XSRF-TOKEN' cookie")
	void getCsrf_returnsStringWithCsrfOkAndXsrfTokenInHeaders() throws Exception {
		mockMvc.perform(get("/api/csrf/"))
				.andExpect(status().isOk())
				.andExpect(content().string("CSRF Ok"))
				.andExpect(cookie().exists("XSRF-TOKEN"));
	}
}