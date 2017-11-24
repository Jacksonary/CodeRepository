package com.hhu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hhu.domain.Product;

public interface ProductDao extends JpaRepository<Product, Long>{
	public Product findById(Long id);
}
