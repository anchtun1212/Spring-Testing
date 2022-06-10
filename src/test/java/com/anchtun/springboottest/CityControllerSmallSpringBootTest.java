package com.anchtun.springboottest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.anchtun.controller.CityController;
import com.anchtun.model.Cities;
import com.anchtun.model.City;
import com.anchtun.service.CityService;

@RunWith(SpringRunner.class)
// will create only CityController in the Application Context
@SpringBootTest(classes = CityController.class)
public class CityControllerSmallSpringBootTest {

	@Autowired
	private CityController cityController;

	// should be mocked because we add this: @SpringBootTest(classes = CityController.class)
	@MockBean
	private CityService cityService;

	@Test
	public void shouldSaveCities() {
		cityController.addCity(City.builder().name("Monastir").build());
		cityController.addCity(City.builder().name("Sousse").build());
		cityController.addCity(City.builder().name("Riyadh").build());

		verify(cityService).save(new City(null, "Monastir"));
		verify(cityService).save(new City(null, "Sousse"));
		verify(cityService).save(new City(null, "Riyadh"));
	}
	
	@Test
	public void shouldFetchCities() {
		when(cityService.findAll())
		.thenReturn(Arrays.asList(
				new City(1L, "Monastir"),
				new City(2L, "Sousse"),
				new City(3L, "Riyadh")));
		
		Cities cities = cityController.getListCities();
		assertThat(cities.getListCity())
		.containsOnly(
				new City(1L, "Monastir"),
				new City(2L, "Sousse"),
				new City(3L, "Riyadh"));
	}
	
}
