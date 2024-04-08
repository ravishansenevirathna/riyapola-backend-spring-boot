package lk.afsd.riyapola.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 4/1/2024
 * Created time : 11:15 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@ToString
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer imageId;
    private String imageName;

    @ManyToOne
    @JoinColumn(name ="carId")
    Car car;
}
