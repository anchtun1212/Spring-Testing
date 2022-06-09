package com.anchtun.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anchtun.model.City;
import com.anchtun.repository.CityRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CityService {

	private final CityRepository cityRepository;

	public List<City> findAll() {
		return cityRepository.findAll();
	}

	public City save(City city) {
		return cityRepository.save(city);
	}

}
