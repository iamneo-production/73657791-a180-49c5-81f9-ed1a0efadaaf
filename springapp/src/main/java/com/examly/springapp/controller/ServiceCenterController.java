package com.examly.springapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.ServiceCenter;
import com.examly.springapp.payload.response.MessageResponse;
import com.examly.springapp.repository.ServiceCenterRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/test/admin")
@PreAuthorize("hasRole('ADMIN')")
public class ServiceCenterController {
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    @PostMapping("/addServ")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addService(@RequestBody ServiceCenter serviceCenter) {
        serviceCenterRepository.save(serviceCenter);
        // return ResponseEntity.ok(serviceCenter);
        return ResponseEntity.ok(new MessageResponse("Service center added"));
    }

    @PutMapping("/editServ/{id}")
    public ResponseEntity<?> editService(@PathVariable Long id, @RequestBody ServiceCenter serviceDetails) {
        ServiceCenter serviceCenter = serviceCenterRepository.findById(id).get();
        // .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:
        // " + id));
        serviceCenter.setName(serviceDetails.getName());
        serviceCenter.setPhone(serviceDetails.getPhone());
        serviceCenter.setAddress(serviceDetails.getAddress());
        serviceCenter.setImg_url(serviceDetails.getImg_url());
        serviceCenter.setMailId(serviceDetails.getMailId());
        serviceCenter.setDescription(serviceDetails.getDescription());
        ServiceCenter updatedService = serviceCenterRepository.save(serviceCenter);
        return ResponseEntity.ok(updatedService);
        // return ResponseEntity.ok(new MessageResponse("Service center updated"));
        // return ResponseEntity.ok(serviceDetails);
    }

    @DeleteMapping("/delServ/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteService(@PathVariable Long id) {
        ServiceCenter serviceCenter = serviceCenterRepository.findById(id).get();
        // .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:
        // " + id));
        serviceCenterRepository.delete(serviceCenter);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getServ/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getServiceCenterById(@PathVariable Long id) {
        ServiceCenter serviceCenter = serviceCenterRepository.findBySid(id).get();
        // .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:
        // " + id));
        return ResponseEntity.ok(serviceCenter);
    }

    @GetMapping("/viewServ")
    public List<ServiceCenter> getServiceCenter() {

        return serviceCenterRepository.findAll();
    }
}
