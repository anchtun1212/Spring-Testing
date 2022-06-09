package com.anchtun.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anchtun.model.Cities;
import com.anchtun.model.City;
import com.anchtun.service.CityService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class CityController {

	private final CityService cityService;

	@GetMapping("/cities")
	public List<City> getCities() {
		return cityService.findAll();
	}

	@GetMapping("/listCities")
	public Cities getListCities() {
		List<City> cities = cityService.findAll();
		return Cities.builder().listCity(cities).build();
	}
	
	@PostMapping("/addCity")
	public void addCity(@RequestBody City city) {
		cityService.save(city);
	}

	@PutMapping("/updateCity")
	public void updateCity(@RequestBody City city) {
		cityService.save(city);
	}

}
