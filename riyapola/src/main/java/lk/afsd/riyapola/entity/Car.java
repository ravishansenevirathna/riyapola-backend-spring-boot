package lk.afsd.riyapola.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:20 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;
    private String brand;
    private String model;
    private int year;
    private String engineCap;
    private String fuelType;
    private String imageName;


//    public Car(Integer carId, String brand, String model, int year, String engineCap, String fuelType, String imageName) {
//        this.carId=carId;
//        this.brand=brand;
//        this.model=model;
//        this.year=year;
//        this.engineCap=engineCap;
//        this.fuelType=fuelType;
//        this.imageName=imageName;
//    }
}
