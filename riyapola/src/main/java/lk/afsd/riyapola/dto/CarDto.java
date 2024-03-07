package lk.afsd.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:20 PM
 */
@AllArgsConstructor
@Data
public class CarDto {
    private Integer carId;
    private String brand;
    private String model;
    private LocalDate year;
    private String engineCap;
    private String fuelType;
    private String image;
}
