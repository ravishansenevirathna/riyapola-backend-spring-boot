package lk.afsd.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 4/1/2024
 * Created time : 11:18 AM
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
public class ImageDetailsGetDto {

    private Integer imageId;
    private String imageName;
    private Integer carId;
}
