package com.anchtun.springboottest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.anchtun.controller.CityController;
import com.anchtun.model.City;

@RunWith(SpringRunner.class)
// will create the Application Context
@SpringBootTest
public class CityControllerSpringBootTest {

	@Autowired
	private CityController cityController;

	// will insert in the DB
	@Test
	public void shouldSaveCities() {
		cityController.addCity(City.builder().name("Monastir").build());
		cityController.addCity(City.builder().name("Sousse").build());
		cityController.addCity(City.builder().name("Riyadh").build());
		
		assertThat(cityController.getListCities().getListCity())
		.containsOnly(
				new City(1L, "Monastir"),
				new City(2L, "Sousse"),
				new City(3L, "Riyadh"));

	}
	
}
