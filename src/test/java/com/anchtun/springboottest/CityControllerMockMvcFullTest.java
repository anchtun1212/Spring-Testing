package com.anchtun.springboottest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CityControllerMockMvcFullTest {

	@Autowired
	private MockMvc mvc;
	
	// will insert in the DB those values
	@Test
	public void shouldSaveCities() throws Exception {
		mvc.perform(post("/addCity")
				.content("{\"name\": \"Monastir\"}")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		mvc.perform(post("/addCity")
				.content("{\"name\": \"Riyadh\"}")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		String jsonResponse = mvc.perform(get("/cities")
				).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertThat(jsonResponse)
				.contains("{\"id\":1,\"name\":\"Monastir\"}")
		        .contains("{\"id\":2,\"name\":\"Riyadh\"}");
	}
}
