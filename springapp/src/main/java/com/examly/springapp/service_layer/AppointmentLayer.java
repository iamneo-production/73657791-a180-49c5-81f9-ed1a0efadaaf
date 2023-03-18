package com.examly.springapp.service_layer;

import java.lang.StackWalker.Option;
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
import com.examly.springapp.model.child.PlasticProduct;
import com.examly.springapp.model.child.WoodProduct;
import com.examly.springapp.payload.request.AppoDetails;
import com.examly.springapp.payload.request.DateUtils;
import com.examly.springapp.repository.ProductRepository;
import com.examly.springapp.repository.UserRepository;
//import com.examly.springapp.repository.WoodProductRepository;
import com.examly.springapp.security.service.UserDetailsImpl;

@Service
public class AppointmentLayer {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    // @Autowired
    // private WoodProductRepository woodProductRepository;
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
    //overloading
    public void createAppointment(Product product,Optional<String> woodtype,
                    Optional<String> plastictype ) {
        User user = fetchUser();
        if(woodtype.isPresent()){
            WoodProduct woodProduct=new WoodProduct();
            woodProduct.setWoodtype(woodtype.get());
            woodProduct.setAvailableSlots(product.getAvailableSlots());
            woodProduct.setContactNumber(product.getContactNumber());
            woodProduct.setDateOfPurchase(product.getDateOfPurchase());
            woodProduct.setProblemDescription(product.getProblemDescription());
            woodProduct.setProductModelNo(product.getProductModelNo());
            woodProduct.setProductName(product.getProductName());
            woodProduct.setServiceCenter(product.getServiceCenter());
            woodProduct.setUserperson(user);
            productRepository.save(woodProduct);
            return;    
        }
        if(plastictype.isPresent()){
            PlasticProduct plasticProduct=new PlasticProduct();
            plasticProduct.setPlastictype(plastictype.get());
            plasticProduct.setAvailableSlots(product.getAvailableSlots());
            plasticProduct.setContactNumber(product.getContactNumber());
            plasticProduct.setDateOfPurchase(product.getDateOfPurchase());
            plasticProduct.setProblemDescription(product.getProblemDescription());
            plasticProduct.setProductModelNo(product.getProductModelNo());
            plasticProduct.setProductName(product.getProductName());
            plasticProduct.setServiceCenter(product.getServiceCenter());
            plasticProduct.setUserperson(user);
            productRepository.save(plasticProduct);
            return ;
        }
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
        //admin operation
    public List<String> fetchUsersBetweenDates(DateUtils obj){
        return productRepository.retrieveUsersBetweenDates(obj.getStartDate(), obj.getEndDate());
    }
        //user and admin operation
    public List<String> fetchProductsBetweenDates(DateUtils obj){
        User user=fetchUser();
        return productRepository.retrieveProductsBetweenDates(obj.getStartDate(), obj.getEndDate(), user.getUsername());
    }
    // public List<?> fetchProductsBetweenDates(DateUtils obj){
    //         User user=fetchUser();
    //         return productRepository.retrieveProductsBetweenDates(obj.getStartDate(), obj.getEndDate(), user.getUsername());
    //     }

}
