package lk.afsd.riyapola.controller;

import lk.afsd.riyapola.dto.CustomerDto;
import lk.afsd.riyapola.entity.Customer;
import lk.afsd.riyapola.repo.CustomerRepo;
import lk.afsd.riyapola.service.CustomerService;
import lk.afsd.riyapola.util.JWTTokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encryptPwd = bCryptPasswordEncoder.encode(customerDto.getPassword());
        customerDto.setPassword(encryptPwd);

        CustomerDto customerDto1 = customerService.registerCustomer(customerDto);
        return new ResponseEntity<>(customerDto1, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginCustomer(@RequestBody CustomerDto customerDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();


        Map<String, String> response = new HashMap<>();
        String customerByEmailtoGetPw = customerRepo.findCustomerByEmailToGetPw(customerDto.getEmail());
        Customer customersByEmail= customerRepo.findCustomersByEmail(customerDto.getEmail());

        if (customersByEmail != null && bCryptPasswordEncoder.matches(customerDto.getPassword(),customerByEmailtoGetPw)) {
            String token = this.jwtTokenGenerator.generateJwtToken(customersByEmail);
            response.put("token", token);
            response.put("customerId", String.valueOf(customersByEmail.getCusId()));
            return new ResponseEntity<>(response,HttpStatus.OK);
        } else {
            response.put("massage", "wrong Credentials");
            return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
        }

    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<Object> getAllCustomer(){
        List<CustomerDto> allCustomer=customerService.getAllCustomer();
        return new ResponseEntity<>(allCustomer,HttpStatus.OK);
    }


    @DeleteMapping("/deleteCustomer/{cusId}")
    public ResponseEntity<String> deleteCustomer(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer cusId){

        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
        String output=customerService.deleteCustomer(cusId);
        return new ResponseEntity<>(output,HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping("/updateCustomer/{cusId}")
    public ResponseEntity<Object> updateCustomer(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer cusId, @RequestBody CustomerDto customerDto){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            String encryptPwd = bCryptPasswordEncoder.encode(customerDto.getPassword());
            customerDto.setPassword(encryptPwd);

            CustomerDto customerDto1=customerService.updateCustomer(cusId,customerDto);
            return new ResponseEntity<>(customerDto1,HttpStatus.OK);

        } else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/searchCustomer/{cusId}")
    public ResponseEntity<Object> searchCustomer(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer cusId){
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            CustomerDto customerDto = customerService.searchCustomer(cusId);
            return new ResponseEntity<>(customerDto,HttpStatus.OK);

        }else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }


    

}
