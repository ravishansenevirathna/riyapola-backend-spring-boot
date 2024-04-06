package lk.afsd.riyapola.controller;

import lk.afsd.riyapola.dto.CarDto;
import lk.afsd.riyapola.entity.Car;
import lk.afsd.riyapola.service.CarService;
import lk.afsd.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:20 PM
 */
@CrossOrigin
@RestController
@RequestMapping("/car")
public class CarController {

    public final CarService carService;
    private final JWTTokenGenerator jwtTokenGenerator;

    @Autowired
    public CarController(CarService carService, JWTTokenGenerator jwtTokenGenerator) {
        this.carService = carService;
        this.jwtTokenGenerator = jwtTokenGenerator;
    }


    @PostMapping("/addNewCar")
    public ResponseEntity<Object> saveCar(@RequestHeader(name = "Authorization") String authorizationHeader,@RequestBody CarDto carDto){
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            CarDto carDto1 = carService.saveCar(carDto);
            return new ResponseEntity<>(carDto1, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/getAllCars")
    public ResponseEntity<Object> getAllCars(){
        List<CarDto> GetDto = carService.getAllCars();
        return new ResponseEntity<>(GetDto, HttpStatus.OK);
    }





    @DeleteMapping("/deleteCar/{carId}")
    public ResponseEntity<String> deleteCar(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer carId) throws IOException, URISyntaxException {
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
        String output=carService.deleteCar(carId);
        return new ResponseEntity<>(output,HttpStatus.OK);}
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }

    }



    @PutMapping("/updateCar/{carId}")
    public ResponseEntity<Object> updateCar(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer carId, @RequestBody CarDto carDto){
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            CarDto carDto1=carService.updateCar(carId,carDto);

        return new ResponseEntity<>(carDto1,HttpStatus.OK);}
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }

}
