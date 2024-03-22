package lk.afsd.riyapola.service;

import lk.afsd.riyapola.dto.CarDetailsGetDto;
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

    public CarDetailsGetDto saveCar(CarDto carDto) throws IOException, URISyntaxException {
        String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
        File uploadDir = new File(projectPath + "/src/main/resources/static/uploads");

        uploadDir.mkdir();

        carDto.getImageName().transferTo(new File(uploadDir.getAbsolutePath() + "/" + carDto.getImageName().getOriginalFilename()));

        Car car = dtoToEntity(carDto);
        car.setImageName("uploads/" + carDto.getImageName().getOriginalFilename());

        Car save = carRepo.save(car);
        return entityToDto(save);

    }




    public List<CarDetailsGetDto> getAllCars(){
        List<Car> all = carRepo.findAll();
        List<CarDetailsGetDto> list = new ArrayList<>();
        for (Car car : all) {
            CarDetailsGetDto carDetailsGetDto = entityToDto2(car);
            list.add(carDetailsGetDto);
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




    public Car updateCar(Integer id, CarDto carDto) throws IOException, URISyntaxException {

        String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
        File uploadDir = new File(projectPath + "/src/main/resources/static/uploads");

        uploadDir.mkdir();

        carDto.getImageName().transferTo(new File(uploadDir.getAbsolutePath() + "/" + carDto.getImageName().getOriginalFilename()));

        Car car = dtoToEntity(carDto);
        car.setImageName("uploads/" + carDto.getImageName().getOriginalFilename());

        if(carRepo.existsById(id)){
            Car save = carRepo.save(car);
            return save;
        }
        return null;

    }




    private Car dtoToEntity(CarDto carDto){
        return modelMapperConfig.modelMapper().map(carDto, Car.class);
    }

    private CarDetailsGetDto entityToDto(Car car){
        return modelMapperConfig.modelMapper().map(car,CarDetailsGetDto.class);
    }


    private CarDetailsGetDto entityToDto2(Car car){
        return modelMapperConfig.modelMapper().map(car,CarDetailsGetDto.class);
    }





}
