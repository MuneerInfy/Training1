package com.infosys.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);

}
