package lk.afsd.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 4/9/2024
 * Created time : 11:27 PM
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ReservationDto {
    private Integer reservationId;
    private LocalDate startDate;
    private Time startTime;
    private LocalDate endDate;
    private Time endTime;
    private String pickUpLocation;
    private Integer carId;
    private Integer customerId;
    private String status;


}
