package com.examly.springapp.repository;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Product;
import com.examly.springapp.model.ServiceCenter;
import com.examly.springapp.model.User;

@Repository
//@Primary
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByUserperson(User userperson);

    // ----------------Adding Service Center------------------------------------
    List<Product> findByServiceCenter(ServiceCenter serviceCenter);

    // -------------------------------------------------------------------------
    // @Query("select u.username from Product p JOIN User u")
    // @Query("SELECT DISTINCT u.username FROM Product p LEFT OUTER JOIN
    // p.userperson u WHERE p.dateOfPurchase BETWEEN '2022-12-27 05:30:00' AND
    // '2023-01-03 05:30:00'")
    // @Query("SELECT p.dateOfPurchase FROM Product p LEFT OUTER JOIN p.userperson
    // u")
    // @Query("SELECT p from Product p WHERE p.productName=:n")
    @Query("SELECT DISTINCT u.username FROM Product p LEFT OUTER JOIN p.userperson u WHERE p.dateOfPurchase BETWEEN :d1 AND :d2")
    List<String> retrieveUsersBetweenDates(@Param("d1") Date start, @Param("d2") Date end);

    // List<Object> testMe(@Param("d1") String start,@Param("d2") String end);
    
    @Query("SELECT DISTINCT p.productName FROM Product p RIGHT OUTER JOIN p.userperson u WHERE u.username=:n AND p.dateOfPurchase BETWEEN :d1 AND :d2  ")
    List<String> retrieveProductsBetweenDates(@Param("d1") Date start, @Param("d2") Date end, @Param("n") String name);
    
    
}
