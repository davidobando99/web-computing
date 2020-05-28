package com.packt.cardatabase.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.packt.cardatabase.domain.Car;

public interface CarRepository extends CrudRepository<Car, Long> {
	// Fetch cars by brand
	List<Car> findByBrand(String brand);

	// Fetch cars by color
	List<Car> findByColor(String color);

	// Fetch cars by year
	List<Car> findByYear(int year);

	// Fetch cars by brand and model
	List<Car> findByBrandAndModel(String brand, String model);

	// Fetch cars by brand or color
	List<Car> findByBrandOrColor(String brand, String color);
	
	// Fetch cars by brand and sort by year
	List<Car> findByBrandOrderByYearAsc(String brand);
	
}
