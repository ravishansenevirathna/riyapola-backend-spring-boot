package lk.afsd.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:31 PM
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CustomerDto{
    private Integer cusId;
    private String name;
    private String telephoneNum;
    private String email;
    private String password;
}
