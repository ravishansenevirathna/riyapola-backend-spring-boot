package lk.afsd.riyapola.service;

import lk.afsd.riyapola.dto.CustomerDto;
import lk.afsd.riyapola.entity.Customer;
import lk.afsd.riyapola.repo.CustomerRepo;
import lk.afsd.riyapola.util.ModelMapperConfig;
import org.springframework.stereotype.Service;

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




    private Customer dtoToEntity(CustomerDto customerDto){
        return modelMapperConfig.modelMapper().map(customerDto, Customer.class);
    }

    private CustomerDto entityToDto(Customer customer){
        return modelMapperConfig.modelMapper().map(customer,CustomerDto.class);
    }

}
