package lk.afsd.riyapola.service;

import lk.afsd.riyapola.dto.CarDto;
import lk.afsd.riyapola.entity.Car;
import lk.afsd.riyapola.repo.CarRepo;
import lk.afsd.riyapola.util.ModelMapperConfig;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:20 PM
 */
@Service
public class CarService {
    private final CarRepo carRepo;
    private final ModelMapperConfig modelMapperConfig;

    public CarService(CarRepo carRepo, ModelMapperConfig modelMapperConfig) {
        this.carRepo = carRepo;
        this.modelMapperConfig = modelMapperConfig;
    }

    public CarDto saveCar(CarDto carDto){


        Car car = dtoToEntity(carDto);
        Car save = carRepo.save(car);
        return entityToDto(save);

    }

    public List<CarDto> getAllCars(){
        List<Car> all = carRepo.findAll();
        List<CarDto> list = new ArrayList<>();
        for (Car car : all) {
            CarDto carDto = entityToDto2(car);
            list.add(carDto);
        }
        return list;
    }


    public String deleteCar(Integer id){
        if(carRepo.existsById(id)){
            carRepo.deleteById(id);


            return "Car Deleted";
        }
        return "No Car Found";
    }




    public CarDto updateCar(Integer id, CarDto carDto){

        Car existingCar = carRepo.findById(id).orElse(null);
        if(existingCar == null){
            return null;
        }

        existingCar.setBrand(carDto.getBrand());
        existingCar.setModel(carDto.getModel());
        existingCar.setYear(carDto.getYear());
        existingCar.setFuelType(carDto.getFuelType());
        existingCar.setEngineCap(carDto.getEngineCap());


        Car savedCar = carRepo.save(existingCar);

        return entityToDto(savedCar);

    }




    private Car dtoToEntity(CarDto carDto){
        return modelMapperConfig.modelMapper().map(carDto, Car.class);
    }

    private CarDto entityToDto(Car car){
        return modelMapperConfig.modelMapper().map(car,CarDto.class);
    }


    private CarDto entityToDto2(Car car){
        return modelMapperConfig.modelMapper().map(car,CarDto.class);
    }





}
