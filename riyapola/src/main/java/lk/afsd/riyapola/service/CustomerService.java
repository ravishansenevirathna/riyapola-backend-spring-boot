package lk.afsd.riyapola.service;

import lk.afsd.riyapola.dto.CustomerDto;
import lk.afsd.riyapola.entity.Customer;
import lk.afsd.riyapola.repo.CustomerRepo;
import lk.afsd.riyapola.util.ModelMapperConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:32 PM
 */
@Service
public class CustomerService {

    private final CustomerRepo customerRepo;
    private final ModelMapperConfig modelMapperConfig;


    public CustomerService(CustomerRepo customerRepo, ModelMapperConfig modelMapperConfig) {
        this.customerRepo = customerRepo;
        this.modelMapperConfig = modelMapperConfig;
    }


    public CustomerDto registerCustomer(CustomerDto customerDto){

        Customer customer=dtoToEntity(customerDto);
        Customer save = customerRepo.save(customer);
        return entityToDto(save);
    }

    public List<CustomerDto> getAllCustomer(){
        List<Customer> all = customerRepo.findAll();
       List<CustomerDto> list = new ArrayList<>();
       for (Customer customer1:all){
           CustomerDto customerDto = entityToDto(customer1);
           list.add(customerDto);
       }
       return list;

    }


    public String deleteCustomer(Integer cusId){
        if(customerRepo.existsById(cusId)){
            customerRepo.deleteById(cusId);
            return "Customer Deleted";
        }
        return "No Customer Found";


    }

    public CustomerDto updateCustomer(Integer cusId, CustomerDto customerDto){
        if(customerRepo.existsById(cusId)){
            customerDto.setCusId(cusId);
             Customer customer = dtoToEntity(customerDto);
             customerRepo.save(customer);
        }
        return null;
    }




    private Customer dtoToEntity(CustomerDto customerDto){
        return modelMapperConfig.modelMapper().map(customerDto, Customer.class);
    }

    private CustomerDto entityToDto(Customer customer){
        return modelMapperConfig.modelMapper().map(customer,CustomerDto.class);
    }


}
