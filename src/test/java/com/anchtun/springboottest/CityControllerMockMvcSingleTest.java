package com.anchtun.springboottest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.anchtun.controller.CityController;
import com.anchtun.model.City;
import com.anchtun.service.CityService;

@RunWith(SpringRunner.class)
// it create only the instance of the cityController
@WebMvcTest(CityController.class)
public class CityControllerMockMvcSingleTest {

	@Autowired
	private MockMvc mvc;
	
	// because we create instance of cityController and cityController require CityService
	@MockBean
	private CityService cityService;
	
	@Test
	public void shouldSaveCities() throws Exception {
		mvc.perform(post("/addCity")
				.content("{\"name\": \"Monastir\"}")
				.contentType(MediaType.APPLICATION_JSON)
				).andExpect(status().isOk());
		
		verify(cityService).save(new City(null, "Monastir"));
	}
	
	@Test
	public void shouldFetchCities() throws Exception {
		City cities[] = new City[] { new City(1L, "Monastir"), new City(2L, "Riyadh") };
		when(cityService.findAll())
		.thenReturn(Arrays.asList(cities));
		
		String jsonResponse = mvc.perform(get("/cities")
				).andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();

		assertThat(jsonResponse)
				.contains("{\"id\":1,\"name\":\"Monastir\"}")
		        .contains("{\"id\":2,\"name\":\"Riyadh\"}");
	}
}
