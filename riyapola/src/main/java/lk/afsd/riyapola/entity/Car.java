package lk.afsd.riyapola.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:20 PM
 */
@NoArgsConstructor
@Data
@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer carId;
    private String brand;
    private String model;
    private LocalDate year;
    private String engineCap;
    private String fuelType;
    private String image;

    @ManyToMany(mappedBy = "cars")
    private List<Customer> customers;

}
