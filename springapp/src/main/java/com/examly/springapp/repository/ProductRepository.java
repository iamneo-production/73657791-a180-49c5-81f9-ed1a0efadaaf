package com.examly.springapp.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.Product;
import com.examly.springapp.model.User;


public interface ProductRepository extends JpaRepository<Product,Long>{
    List<Product> findByUserperson(User userperson);
}
