package com.anchtun.springboottest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.anchtun.model.Cities;
import com.anchtun.model.City;

@RunWith(SpringRunner.class)
// full webEnvironment
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CityControllerRestTemplateTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void shouldSaveCities() {
		String url = String.format("http://localhost:%d/addCity", port);
		restTemplate.postForLocation(url, City.builder().name("Monastir").build());
		restTemplate.postForLocation(url, City.builder().name("Riyadh").build());

		String urlGet = String.format("http://localhost:%d//listCities", port);
		Cities cities = restTemplate.getForEntity(urlGet, Cities.class).getBody();

		assertThat(cities.getListCity())
		.containsOnly(
				new City(1L, "Monastir"),
				new City(2L, "Riyadh")
		);
	}
}
