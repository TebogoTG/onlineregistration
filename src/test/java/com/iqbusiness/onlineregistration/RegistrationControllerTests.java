package com.iqbusiness.onlineregistration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iqbusiness.onlineregistration.controller.RegistrationController;
import com.iqbusiness.onlineregistration.domain.Person;
import com.iqbusiness.onlineregistration.domain.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = RegistrationController.class)
class RegistrationControllerTests {

	@MockBean
	private PersonService personService;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {
	}

	@Test
	void registerRestControllerInvalidIdNumber() throws Exception {
		Person person =
				Person.builder()
						.fullName("Test Full Name")
						.idNumber("1234567890123")
						.telephoneNumber("+27817722272")
						.build();
		String body = objectMapper.writeValueAsString(person);
		mockMvc.perform(post("/register")
				.contentType("application/json")
				.content(body))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	void registerRestControllerInvalidTelephoneNumber() throws Exception {
		Person person =
				Person.builder()
						.fullName("Test Full Name")
						.idNumber("8002145459081")
						.telephoneNumber("1234567890123")
						.build();
		String body = objectMapper.writeValueAsString(person);
		mockMvc.perform(post("/register")
				.contentType("application/json")
				.content(body))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}
}
