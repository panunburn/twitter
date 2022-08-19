package com.example.Twitter.repository;

import com.example.Twitter.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends CrudRepository<Customer, Long>{
	List<Customer> findByFirstName(String FirstName);
	List<Customer> findByLastName(String LastName);
	List<Customer> findAll();
	List<Customer> findByFirstNameLike(String name);

	@Query("SELECT a FROM Customer a WHERE a.firstName like %:name% or a.lastName like %:name%")
	List<Customer> fetchCustomers(@Param("name") String name);
}