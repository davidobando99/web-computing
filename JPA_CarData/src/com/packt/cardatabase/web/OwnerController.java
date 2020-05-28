package com.packt.cardatabase.web;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.repositories.CarRepository;
import com.packt.cardatabase.repositories.OwnerRepository;

@RestController
public class OwnerController {
	
	
	@Autowired
	private OwnerRepository repository;
	

	
	@RequestMapping("/owners")
	public Iterable<Owner> getOwners() {
		return repository.findAll();
	}
	
	@RequestMapping(value ="/owners/{id}/cars", method = RequestMethod.GET)
	public Iterable<Car> getCars(@PathVariable("id") long id){	
		
		return repository.findById(id).get().getCars();
	}

}
