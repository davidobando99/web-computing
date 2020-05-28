package com.packt.cardatabase.web;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.repositories.CarRepository;

@RestController
public class CarController {
	
	
	@Autowired
	private CarRepository repository;
	
	
	@RequestMapping("/cars")
	public Iterable<Car> getCars() {
		return repository.findAll();
	}
	
	@PostMapping("/cars")
	public Car saveCar(@RequestBody Car car){
		return repository.save(car);
	}
	
	@DeleteMapping("/cars/{id}")
	public void deleteCar(@PathParam("id") long id){
		 repository.deleteById(id);
	}
}
