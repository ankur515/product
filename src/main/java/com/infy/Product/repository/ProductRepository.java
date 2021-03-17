package com.infy.Product.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.Product.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer>{
	List<Product> findAll();
	List<Product> findByCategory(String category);
	List<Product> findByProductname(String productname);
	Optional<Product>findById(Integer prodid);

	
}
