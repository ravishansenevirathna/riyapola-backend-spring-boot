package lk.afsd.riyapola.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 4/1/2024
 * Created time : 11:14 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ImageDto {
    private Integer imageId;
    private MultipartFile imageName;
    private Integer carId;
}
