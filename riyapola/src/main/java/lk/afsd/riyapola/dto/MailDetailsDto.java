package lk.afsd.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/19/2024
 * Created time : 11:54 AM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailDetailsDto {
    private String toMail;
    private String message;
    private String subject;

}
