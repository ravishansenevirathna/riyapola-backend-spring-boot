package lk.afsd.riyapola.service;

import lk.afsd.riyapola.dto.CarDto;
import lk.afsd.riyapola.entity.Car;
import lk.afsd.riyapola.repo.CarRepo;
import org.springframework.stereotype.Service;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/4/2024
 * Created time : 4:20 PM
 */
@Service
public class CarService {
    private final CarRepo carRepo;

    public CarService(CarRepo carRepo) {
        this.carRepo = carRepo;
    }

    public Car saveCar(CarDto carDto){
        return carRepo.save(new Car(carDto.getBrand(),carDto.getModel(),carDto.getImage())
    }
}
