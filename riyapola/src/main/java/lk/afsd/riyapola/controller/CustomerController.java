package lk.afsd.riyapola.controller;

import lk.afsd.riyapola.dto.CustomerDto;
import lk.afsd.riyapola.entity.Customer;
import lk.afsd.riyapola.repo.CustomerRepo;
import lk.afsd.riyapola.service.CustomerService;
import lk.afsd.riyapola.util.JWTTokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:31 PM
 */
@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerRepo customerRepo;
    private final JWTTokenGenerator jwtTokenGenerator;


    public CustomerController(CustomerService customerService, CustomerRepo customerRepo, JWTTokenGenerator jwtTokenGenerator) {
        this.customerService = customerService;
        this.customerRepo = customerRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    @PostMapping("/register")
    public ResponseEntity<CustomerDto> registerCustomer(@RequestBody CustomerDto customerDto){
        CustomerDto customerDto1 = customerService.registerCustomer(customerDto);
        return new ResponseEntity<>(customerDto1, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public Map<String, String> loginCustomer(@RequestBody Customer customer) {
        Map<String, String> response = new HashMap<>();
        Customer userByEmailAndPassword= customerRepo.findCustomersByEmailAndPassword(customer.getEmail(),customer.getPassword());

        if (userByEmailAndPassword != null) {
            String token = this.jwtTokenGenerator.generateJwtToken(userByEmailAndPassword);
            response.put("token", token);
        } else {
            response.put("massage", "wrong Credentials");
        }
        return response;
    }



}
