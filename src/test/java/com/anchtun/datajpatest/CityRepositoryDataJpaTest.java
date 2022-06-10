package com.anchtun.datajpatest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.anchtun.model.City;
import com.anchtun.repository.CityRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CityRepositoryDataJpaTest {

	private static final City CITY_1 = new City(1L, "Monastir");
	private static final City CITY_2 = new City(2L, "Riyadh");

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void shouldSaveCityAndFindById() {
		cityRepository.saveAll(Arrays.asList(CITY_1, CITY_2));

		assertEquals(CITY_1, testEntityManager.find(City.class, 1L));
		assertEquals(CITY_2, testEntityManager.find(City.class, 2L));
	}
}
