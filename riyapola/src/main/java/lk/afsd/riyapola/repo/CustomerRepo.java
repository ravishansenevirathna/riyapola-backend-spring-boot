package lk.afsd.riyapola.repo;

import lk.afsd.riyapola.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:32 PM
 */
public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Customer findCustomersByEmailAndPassword(String email,String password);
}
