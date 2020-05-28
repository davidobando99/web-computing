package co.edu.icesi.fi.ci.junit5exercise.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public Integer orderId;
	public String orderStatus;
	public String securityCode;
	public String description;
	public LocalDate orderDate;
	
}
