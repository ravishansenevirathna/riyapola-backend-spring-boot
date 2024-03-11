package lk.afsd.riyapola.service;

import lk.afsd.riyapola.dto.CarDetailsGetDto;
import lk.afsd.riyapola.dto.CarDto;
import lk.afsd.riyapola.entity.Car;
import lk.afsd.riyapola.repo.CarRepo;
import lk.afsd.riyapola.util.ModelMapperConfig;
import org.springframework.stereotype.Service;

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
        File uploadDir = new File(projectPath + "/uploads");
//        meke hari nam project path eka denna one /src/main/resources/static menna me path erke
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
            CarDetailsGetDto carDetailsGetDto = entityToDto(car);
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



//    public String deleteCar(Integer id) throws IOException, URISyntaxException {
//        if (carRepo.existsById(id)) {
//            Car car = carRepo.findById(id).get();
//
//            // Retrieve image path from car entity (assuming it exists)
//            String imagePath = car.getImageName();
//            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
//            File uploadDir = new File(projectPath + "/uploads");
//
//            // Attempt to delete image (robust error handling)
//            if (imagePath != null && new File(uploadDir + "/" + imagePath).exists()) {
//                if (!new File(uploadDir + "/" + imagePath).delete()) {
//                    throw new IOException("Failed to delete image: " + imagePath);
//
//                }
//            }
//
//            // Delete car entity
//            carRepo.deleteById(id);
//
//            return "Car and image deleted successfully";
//        }
//        return "No Car Found";
//    }





    public Car updateCar(Integer id, CarDetailsGetDto carDetailsGetDto){
        if(carRepo.existsById(id)){
            return carRepo.save(new Car(id,carDetailsGetDto.getBrand(),carDetailsGetDto.getModel(),carDetailsGetDto.getYear(),carDetailsGetDto.getEngineCap(),carDetailsGetDto.getFuelType(),carDetailsGetDto.getImageName()));

        }
        return null;
    }





    private Car dtoToEntity(CarDto carDto){
        return modelMapperConfig.modelMapper().map(carDto, Car.class);
    }

    private CarDetailsGetDto entityToDto(Car car){
        return modelMapperConfig.modelMapper().map(car,CarDetailsGetDto.class);
    }

}
