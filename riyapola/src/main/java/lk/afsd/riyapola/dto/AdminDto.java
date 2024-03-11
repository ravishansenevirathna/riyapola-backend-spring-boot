package lk.afsd.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/11/2024
 * Created time : 3:47 PM
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class AdminDto {
    private Integer adminId;
    private String userName;
    private String password;
}
