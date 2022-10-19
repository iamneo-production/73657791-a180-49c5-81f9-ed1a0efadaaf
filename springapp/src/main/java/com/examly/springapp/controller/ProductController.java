package com.examly.springapp.controller;

import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
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

import com.examly.springapp.model.Product;
import com.examly.springapp.payload.response.MessageResponse;
import com.examly.springapp.repository.ProductRepository;



@RestController
@RequestMapping("/api/test/user")
@PreAuthorize("hasRole('USER')")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/addProd")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        //return ResponseEntity.ok(product);
        return ResponseEntity.ok(new MessageResponse("Appointment added"));
    }
    @GetMapping("/getProd/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        return ResponseEntity.ok(product);
    }
    @GetMapping("/getProd")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getProduct(@RequestBody Product product) {
                //.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        return ResponseEntity.ok(product);
    }
    @DeleteMapping("/delProd/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable Long id){
        Product product = productRepository.findById(id).get();
        //.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        productRepository.delete(product);
        Map<String,Boolean>response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/editProd/{id}")
    public ResponseEntity<?> editProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        Product product = productRepository.findById(id).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        product.setProductName(productDetails.getProductName());
        product.setProductModelNo(productDetails.getProductModelNo());
        product.setDateOfPurchase(productDetails.getDateOfPurchase());
        product.setContactNumber(productDetails.getContactNumber());
        product.setProblemDescription(productDetails.getProblemDescription());
        product.setAvailableSlots(productDetails.getAvailableSlots());
        Product updatedProduct= productRepository.save(product);
        return ResponseEntity.ok(updatedProduct);
       // return ResponseEntity.ok(new MessageResponse("Service center updated"));
        //    return ResponseEntity.ok(productDetails);
    }
}
