package com.anchtun.springboottest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.anchtun.service.NameService;

// for Junit4 Test
// @RunWith(SpringRunner.class)
// will automatically search from my spring boot application annotated by @SpringBootApplication
// and will automatically create the beans
// we use it for integration test
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	// since nameService introduce random names I want to mock it to have the same values
	// easy way to write the integration test since I can mock any part of the system
	@MockBean
	private NameService nameService;
	
	// org.junit.jupiter.api.Test for Junit 5
	@Test
	public void shouldSayHello() throws Exception {
		when(nameService.getName()).thenReturn("Charrada");
		
		// not interact with HelloController directly but instead we inject MockMvc
		// and with mockMvc we  create get request for the application and fetch the result
		// and then get the content and check if the content equal to Hello test
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/")).andReturn();
		
		assertEquals("Hello Charrada", mvcResult.getResponse().getContentAsString());
	}

}
