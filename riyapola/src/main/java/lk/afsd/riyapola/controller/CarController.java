package lk.afsd.riyapola.controller;

import lk.afsd.riyapola.dto.CarDto;
import lk.afsd.riyapola.entity.Car;
import lk.afsd.riyapola.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:20 PM
 */
@RestController
@RequestMapping("/car")
public class CarController {

    public final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("addCar")
    public ResponseEntity<Car> saveCar(@RequestBody CarDto carDto){
        Car car=carService.saveCar(carDto);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }
}
