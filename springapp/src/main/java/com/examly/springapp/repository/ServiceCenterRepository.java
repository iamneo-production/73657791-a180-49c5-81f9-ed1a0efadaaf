package com.examly.springapp.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.ServiceCenter;


@Repository
public interface ServiceCenterRepository extends JpaRepository<ServiceCenter,Long>{
    Optional<ServiceCenter> findBySid(Long sid);
}
