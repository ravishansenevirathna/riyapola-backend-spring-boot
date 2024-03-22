package lk.afsd.riyapola.repo;

import lk.afsd.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:32 PM
 */
public interface CustomerRepo extends JpaRepository<Customer,Integer> {


    Customer findCustomersByEmail(String email);


    @Query(nativeQuery = true,value = "SELECT password FROM customer WHERE email = :email")
    String findCustomerByEmailToGetPw(String email);

}
