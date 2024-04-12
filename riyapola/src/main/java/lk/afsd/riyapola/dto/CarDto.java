package lk.afsd.riyapola.dto;

import lk.afsd.riyapola.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:20 PM
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CarDto {
    private Integer carId;
    private String brand;
    private String model;
    private int year;
    private String engineCap;
    private String fuelType;
    private List<ImageDetailsGetDto> images;
//    private List<Reservation> reservation;

}
