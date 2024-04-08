package lk.afsd.riyapola.controller;

import lk.afsd.riyapola.dto.ImageDetailsGetDto;
import lk.afsd.riyapola.dto.ImageDto;
import lk.afsd.riyapola.entity.Images;
import lk.afsd.riyapola.service.ImageService;
import lk.afsd.riyapola.util.JWTTokenGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 4/1/2024
 * Created time : 11:14 AM
 */
@CrossOrigin
@RestController
@RequestMapping("/carImage")
public class ImageController {

    private final ImageService imageService;
    private final JWTTokenGenerator jwtTokenGenerator;
    private final ModelMapper modelMapper;

    public ImageController(ImageService imageService, JWTTokenGenerator jwtTokenGenerator, ModelMapper modelMapper) {
        this.imageService = imageService;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/addNewCarImage")
    public ResponseEntity<Object> saveCarImage(@RequestHeader(name = "Authorization") String authorizationHeader, @ModelAttribute ImageDto imageDto) throws IOException, URISyntaxException {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            ImageDetailsGetDto imageDetailsGetDto = imageService.saveCarImage(imageDto);
            return new ResponseEntity<>(imageDetailsGetDto, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/getAllCarsImages")
    public ResponseEntity<Object> getAllCarsImages(){
        List<ImageDetailsGetDto> GetDto = imageService.getAllCarsImages();
        return new ResponseEntity<>(GetDto, HttpStatus.OK);
    }


    @DeleteMapping("/deleteCarImage/{carId}")
    public ResponseEntity<String> deleteCarImage(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer carId) throws IOException, URISyntaxException {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            String output=imageService.deleteCarImage(carId);
            return new ResponseEntity<>(output,HttpStatus.OK);}
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }

    }

    @PutMapping("/updateCarImage/{imageId}")
    public ResponseEntity<Object> updateCarImage(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer imageId, @ModelAttribute ImageDto imageDto) throws IOException, URISyntaxException {
        System.out.println("hi");
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {

            Images updatedImage =imageService.updateCarImage(imageId,imageDto);

            ImageDetailsGetDto responseDto = modelMapper.map(updatedImage, ImageDetailsGetDto.class);

            Optional<Images> selectedImage = imageService.selectImageName(responseDto.getImageId());
            if (selectedImage.isPresent()) {
                Images image = selectedImage.get();
                responseDto.setImageName(image.getImageName());  // Set the actual imageName
            } else {
                System.out.println("Image with id " + responseDto.getImageId() + " not found");
            }

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }
}
