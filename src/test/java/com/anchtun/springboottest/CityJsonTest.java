package com.anchtun.springboottest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import com.anchtun.model.City;

@RunWith(SpringRunner.class)
@JsonTest
public class CityJsonTest {

	@Autowired
	private JacksonTester<City> json;

	@Test
	public void shouldSerialize() throws IOException {
		City city = new City(1L, "Monastir");
		String cityJson = this.json.write(city).getJson();
		assertEquals(cityJson, "{\"id\":1,\"name\":\"Monastir\"}");
	}

	@Test
	public void shouldDeserialize() throws IOException {
		City city = json.parseObject("{\"id\":1,\"name\":\"Monastir\"}");
		assertEquals(1L, city.getId());
		assertEquals("Monastir", city.getName());
	}

}
