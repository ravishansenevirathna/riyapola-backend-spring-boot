package lk.afsd.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:31 PM
 */
@AllArgsConstructor
@Data
public class CustomerDto {
    private Integer cusid;
    private String name;
    private String address;
    private int telephoneNum;
    private String email;
}
