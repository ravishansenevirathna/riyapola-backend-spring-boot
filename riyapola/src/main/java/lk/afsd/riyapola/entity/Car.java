package lk.afsd.riyapola.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
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

    @OneToMany(mappedBy = "car",cascade = CascadeType.ALL)
    List<Images> images;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "carId")
    List<Reservation> reservation = new ArrayList<>();



}
