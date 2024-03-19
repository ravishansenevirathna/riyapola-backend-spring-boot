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
import java.util.List;
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

    @GetMapping("/getAllCustomer")
    public ResponseEntity<Object> getAllCustomer(){
        List<CustomerDto> allCustomer=customerService.getAllCustomer();
        return new ResponseEntity<>(allCustomer,HttpStatus.OK);
    }


    @DeleteMapping("/{cusId}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer cusId){

        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
        String output=customerService.deleteCustomer(cusId);
        return new ResponseEntity<>(output,HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }

    }

//    Controller eken service ekt entity ekak pass karanna baha
    
//    put meka aye karanna update wechcha cus ge details pennanna puluwaqn wena widihata

    @PutMapping("updateCustomer/{cusId}")
    public ResponseEntity<Object> updateCustomer(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer cusId, @RequestBody CustomerDto customerDto){
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {

        CustomerDto customerDto1=customerService.updateCustomer(cusId,customerDto);
        return new ResponseEntity("done",HttpStatus.OK);

        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }
    

}
