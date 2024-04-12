package lk.afsd.riyapola.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Time;
import java.time.LocalDate;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 4/9/2024
 * Created time : 5:04 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
