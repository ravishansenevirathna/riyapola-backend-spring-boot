package lk.afsd.riyapola.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:31 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cusId;
    private String name;
    private String telephoneNum;
    private String email;
    private String password;


    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "customerId")
    List<Reservation> reservation = new ArrayList<>();





}
