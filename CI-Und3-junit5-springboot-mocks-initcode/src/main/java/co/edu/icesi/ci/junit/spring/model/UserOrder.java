package co.edu.icesi.ci.junit.spring.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class UserOrder {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	public int orderId;
	public String orderStatus;
	public String securityCode;
	public String description;
	public Date orderDate;

}
