package com.examly.springapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.examly.springapp.model.Product;
import com.examly.springapp.model.ServiceCenter;
import com.examly.springapp.model.User;
import com.examly.springapp.payload.request.AppoDetails;
import com.examly.springapp.payload.response.MessageResponse;
import com.examly.springapp.repository.ProductRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.security.service.UserDetailsImpl;
import com.examly.springapp.service_layer.AppointmentLayer;
import com.examly.springapp.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/test/user")
@PreAuthorize("hasRole('USER')")
public class AppointmentController {
    @Autowired
    AuthenticationManager authenticationManager;  
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private AppointmentLayer appointmentLayer;
    @PostMapping("/addAppo")
    public ResponseEntity<?> addAppo(@RequestBody Product product) {
        // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        // User user=userRepository.findById(userDetails.getId()).get();
        // product.setUserperson(user);
        // productRepository.save(product);
        // //return ResponseEntity.ok(product);
        appointmentLayer.createAppointment(product);
       return ResponseEntity.ok(new MessageResponse("Appointment added"));
    }
    @GetMapping("/viewAppo")
    public List<Product> getAppo() {
  
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    //     User user=userRepository.findById(userDetails.getId()).get();
    //     //product.setUserperson(user);
    //     return productRepository.findByUserperson(user);
    //     //return ResponseEntity.ok(product);
    //    //return ResponseEntity.ok(new MessageResponse("Appointment added"));
       return appointmentLayer.fetchAppointment();   
           
    }
    //----------------Adding Service Center------------------------------------
    @GetMapping("/viewServAppo")
    public List<Product> getAppoByServ(@RequestBody ServiceCenter serviceCenter) {

       //return productRepository.findByServiceCenter(serviceCenter);
       return appointmentLayer.fetchAppoByServ(serviceCenter);
    }
    //-------------------------------------------------------------------------
    @PutMapping("/editAppo/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> editAppo(@PathVariable Long id, @RequestBody AppoDetails productDetails) {
        // if(!productRepository.findById(id).isPresent()){
        //     throw  new ResourceNotFoundException("Product does not exist with id " + id);
        // }
        //  Product product = productRepository.findById(id).get();
        // product.setProductName(productDetails.getProductName());
        // product.setProductModelNo(productDetails.getProductModelNo());
        // product.setDateOfPurchase(productDetails.getDateOfPurchase());
        // product.setContactNumber(productDetails.getContactNumber());
        // product.setProblemDescription(productDetails.getProblemDescription());
        // product.setAvailableSlots(productDetails.getAvailableSlots());
        // //product.setUserperson(product.getUserperson());
        // Product updatedProduct= productRepository.save(product);
        // return ResponseEntity.ok(updatedProduct);
        return ResponseEntity.ok(appointmentLayer.updateAppointment(id, productDetails));
        
   
       
    }
    @DeleteMapping("/delAppo/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteProduct(@PathVariable Long id){
        //  if(!productRepository.findById(id).isPresent()){
        //      throw  new ResourceNotFoundException("Product does not exist with id " + id);
        //  }
        //  Product product = productRepository.findById(id).get();
        // // //.orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        //  productRepository.delete(product);
        appointmentLayer.deleteAppointment(id);
        Map<String,Boolean>response=new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getAppo")//testing purpose only,doesnt store it in db
    public ResponseEntity<?> getAppo(@RequestBody Product product) {
  
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user=userRepository.findById(userDetails.getId()).get();
        product.setUserperson(user);
        //productRepository.save(product);
        return ResponseEntity.ok(product);
       //return ResponseEntity.ok(new MessageResponse("Appointment added"));
    }
    @GetMapping("/getAppo/{id}")
    public ResponseEntity<?> getAppo(@PathVariable Long id) {
        // if(!productRepository.findById(id).isPresent()){
        //     throw  new ResourceNotFoundException("Product does not exist with id " + id);
        // }
  
        
        // Product product=productRepository.findById(id).get();
        // //.orElseThrow(() -> new ResourceNotFoundException("P not exist with id: " + id));
        Product product=appointmentLayer.fetchAppointment(id);
        return ResponseEntity.ok(product);
      
    }
  
}
