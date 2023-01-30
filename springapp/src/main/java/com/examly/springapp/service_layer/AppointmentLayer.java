package com.examly.springapp.service_layer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.Product;
import com.examly.springapp.model.ServiceCenter;
import com.examly.springapp.model.User;
import com.examly.springapp.payload.request.AppoDetails;
import com.examly.springapp.repository.ProductRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.security.service.UserDetailsImpl;

@Service
public class AppointmentLayer {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;

    public User fetchUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Optional<User> user = userRepository.findById(userDetails.getId());
        if (user.isEmpty()) {
            Long id = userDetails.getId();
            throw new ResourceNotFoundException("User does not exist with id " + id);
        }
        return user.get();
    }

    public void createAppointment(Product product) {

        User user = fetchUser();
        product.setUserperson(user);
        productRepository.save(product);
    }

    public List<Product> fetchAppointment() {
        User user = fetchUser();
        return productRepository.findByUserperson(user);
    }

    public List<Product> fetchAppoByServ(ServiceCenter serviceCenter) {

        return productRepository.findByServiceCenter(serviceCenter);
    }

    public Product updateAppointment(Long id, AppoDetails productDetails) {
        Product product;
        if (productRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Product does not exist with id " + id);
        }else{
            product = productRepository.findById(id).get();
        }
        
        product.setProductName(productDetails.getProductName());
        product.setProductModelNo(productDetails.getProductModelNo());
        product.setDateOfPurchase(productDetails.getDateOfPurchase());
        product.setContactNumber(productDetails.getContactNumber());
        product.setProblemDescription(productDetails.getProblemDescription());
        product.setAvailableSlots(productDetails.getAvailableSlots());
        Product updatedProduct = productRepository.save(product);
        return updatedProduct;
    }
    public void deleteAppointment(Long id){
        Product product;
        if (!productRepository.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Product does not exist with id " + id);
        }else{
            product = productRepository.findById(id).get();
        }
        
        productRepository.delete(product);
       
    }
    public Product fetchAppointment(Long id){
        if(!productRepository.findById(id).isPresent()){
            throw  new ResourceNotFoundException("Product does not exist with id " + id);
        }
        Product product=productRepository.findById(id).get();
        
        return product;
    }
}
