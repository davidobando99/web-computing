package co.edu.icesi.fi.ci.junit5exercise.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.fi.ci.junit5exercise.model.Order;
import co.edu.icesi.fi.ci.junit5exercise.repository.OrderRepository;

@Service
public class OrderServiceImp implements OrderService {

	private OrderRepository orderRepository;

	@Autowired
	public OrderServiceImp(OrderRepository serviceRepository) {
		this.orderRepository = serviceRepository;
	}

	public String getOrderDescription(int id) {
		Optional<Order> existingOrder = orderRepository.findById(id);
		return existingOrder.get().getDescription();
	}

	public String getOrderStringCode(int id) {
		Optional<Order> existingOrder = orderRepository.findById(id);
		return existingOrder.get().getSecurityCode();
	}

	public LocalDate getOrderDate(int id) {
		Optional<Order> existingOrder = orderRepository.findById(id);
		return existingOrder.get().getOrderDate();
	}

	public String getOrderStatus(int id) {
		Optional<Order> existingOrder = orderRepository.findById(id);
		return existingOrder.get().getOrderStatus();
	}

	public Order createOrder(Order order) {
		orderRepository.save(order);
		return order;
	}

	public Order updateOrder(Order order) {
		Order existingOrder = orderRepository.findById(order.getOrderId()).get();
		existingOrder.setDescription(order.getDescription());
		existingOrder.setOrderStatus(order.getOrderStatus());
		existingOrder.setOrderDate(order.getOrderDate());
		orderRepository.save(existingOrder); // no necesario
		return order;
	}

	public void deleteOrder(int id) {

		Order existingOrder = orderRepository.findById(id).get();
		orderRepository.delete(existingOrder);
	}

	public Order getOrder(int id) {

		Optional<Order> existingOrder = orderRepository.findById(id);
		return existingOrder.get();
	}

}
