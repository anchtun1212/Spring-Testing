package com.anchtun.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.anchtun.model.City;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {

	List<City> findAll();
}
