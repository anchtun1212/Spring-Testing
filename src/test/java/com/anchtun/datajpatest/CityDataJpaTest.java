package com.anchtun.datajpatest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.anchtun.model.City;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CityDataJpaTest {

	private static final City CITY_1 = new City(1L, "Monastir");
	private static final City CITY_2 = new City(2L, "Riyadh");

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	public void shouldSaveCityAndFindById() {
		testEntityManager.merge(CITY_1);
		testEntityManager.merge(CITY_2);

		assertEquals(CITY_1, testEntityManager.find(City.class, 1L));
		assertEquals(CITY_2, testEntityManager.find(City.class, 2L));
	}
}
