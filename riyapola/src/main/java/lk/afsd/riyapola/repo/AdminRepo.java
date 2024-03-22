package lk.afsd.riyapola.repo;

import lk.afsd.riyapola.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/11/2024
 * Created time : 4:18 PM
 */
public interface AdminRepo extends JpaRepository<Admin, Integer> {


    @Query(nativeQuery = true, value = "SELECT password FROM admin WHERE user_name = :userName")
    String findAdminByCredentials(String userName);

    Admin findAdminByUserName(String userName);




}
