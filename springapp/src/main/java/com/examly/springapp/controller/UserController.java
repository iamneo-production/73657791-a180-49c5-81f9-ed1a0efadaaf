package com.examly.springapp.controller;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.ERole;
import com.examly.springapp.model.Role;
import com.examly.springapp.payload.request.RequestBox;
import com.examly.springapp.repository.RoleRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.model.User;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/user")
@PreAuthorize("hasRole('USER')")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;

    public Set<Role> retrieveRoles(Set<String> roles) {
        Set<Role> res = new HashSet<>();
        for (String role : roles) {
            switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    res.add(adminRole);

                    break;

                case "user":
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    res.add(userRole);
                    break;
                // default:
                // return ResponseEntity.ok(new MessageResponse("Invalid Role"));
            }
        }
        return res;
    }

    @PostMapping("/add")
    public User addUser(@RequestBody RequestBox request)// this is just a request body not a signup
    {
        Set<Role> roles = retrieveRoles(request.getRole());

        User user = new User(request.getUsername(),
                request.getEmail(),
                encoder.encode(request.getPassword()),
                request.getMobilenum());

        user.setRoles(roles);
        return userRepository.save(user);
        // return request;
    }

    @GetMapping("/get/{id}")
    public Optional<User> getUser(@PathVariable("id") Long id) {
        return userRepository.findById(id);
    }
    @GetMapping("/get")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

   

    @PutMapping("/edit/{id}")

    // public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody
    // User userDetails)//this is just a request body not a signup
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody RequestBox request) {
        User user = userRepository.findById(id).get();
        // .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:
        // " + id));
        // user.setFirstName(employeeDetails.getFirstName());
        // employee.setEmailId(employeeDetails.getEmailId());
        // employee.setLastName(employeeDetails.getLastName());
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setMobilenum(request.getMobilenum());
        Set<Role> roles = retrieveRoles(request.getRole());
        user.setRoles(roles);
        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
        // User user = userRepository.findById(id)
        // .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:
        // " + id));
        // userRepository.delete(user);
        userRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
 
}
