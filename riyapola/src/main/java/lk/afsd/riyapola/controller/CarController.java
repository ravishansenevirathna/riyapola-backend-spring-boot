package lk.afsd.riyapola.controller;

import lk.afsd.riyapola.dto.CarDetailsGetDto;
import lk.afsd.riyapola.dto.CarDto;
import lk.afsd.riyapola.entity.Car;
import lk.afsd.riyapola.service.CarService;
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

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

//    @PostMapping("addCar")
//    public ResponseEntity<Car> saveCar(@RequestBody CarDto carDto){
//        Car car=carService.saveCar(carDto);
//        return new ResponseEntity<>(car, HttpStatus.CREATED);
//    }

    @PostMapping("/addnewcar")
    public ResponseEntity<Object> saveCar(@ModelAttribute CarDto carDto) throws IOException, URISyntaxException {
        CarDetailsGetDto carDetailsGetDto=carService.saveCar(carDto);
        return new ResponseEntity<>(carDetailsGetDto, HttpStatus.CREATED);
    }

    @GetMapping("/getallcars")
    public ResponseEntity<Object> getAllCars(){
        List<CarDetailsGetDto> carDetailsGetDto = carService.getAllCars();
        return new ResponseEntity<>(carDetailsGetDto, HttpStatus.OK);

    }


    @DeleteMapping("/deletecar/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable Integer carId) throws IOException, URISyntaxException {
        String output=carService.deleteCar(carId);
        return new ResponseEntity<>(output,HttpStatus.OK);

    }

    @PutMapping("/updateCar/{carId}")
    public ResponseEntity<Car> updateCar(@PathVariable Integer carId, @ModelAttribute CarDetailsGetDto carDetailsGetDto){
        Car car=carService.updateCar(carId,carDetailsGetDto);
        return new ResponseEntity<>(car,HttpStatus.OK);

    }

}
