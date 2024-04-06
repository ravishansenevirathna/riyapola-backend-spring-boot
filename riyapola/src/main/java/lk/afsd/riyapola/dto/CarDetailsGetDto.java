package lk.afsd.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/7/2024
 * Created time : 10:56 AM
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class CarDetailsGetDto {
    private Integer carId;
    private String brand;
    private String model;
    private int year;
    private String engineCap;
    private String fuelType;

}

