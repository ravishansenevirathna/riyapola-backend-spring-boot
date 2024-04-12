package lk.afsd.riyapola.service;
import lk.afsd.riyapola.dto.CarDto;
import lk.afsd.riyapola.dto.ReservationDto;
import lk.afsd.riyapola.entity.Car;
import lk.afsd.riyapola.entity.Reservation;
import lk.afsd.riyapola.repo.ReservationRepo;
import lk.afsd.riyapola.util.ModelMapperConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 4/11/2024
 * Created time : 1:24 PM
 */
@Service
public class ReservationService {

    private final ReservationRepo reservationRepo;
    private final ModelMapperConfig modelMapperConfig;

    public ReservationService(ReservationRepo reservationRepo, ModelMapperConfig modelMapperConfig) {
        this.reservationRepo = reservationRepo;
        this.modelMapperConfig = modelMapperConfig;
    }


    public ReservationDto saveReservation(ReservationDto reservationDto){
        Reservation reservation = dtoToEntity(reservationDto);

        Reservation save = reservationRepo.save(reservation);

        return entityToDto(save);

    }



    public List<ReservationDto> getAllReservations(){
        List<Reservation> all = reservationRepo.findAll();
        List<ReservationDto> list = new ArrayList<>();
        for (Reservation reservation : all) {
            ReservationDto reservationDto = entityToDto(reservation);
            list.add(reservationDto);
        }
        return list;
    }




    private Reservation dtoToEntity(ReservationDto reservationDto){
        return modelMapperConfig.modelMapper().map(reservationDto, Reservation.class);
    }

    private ReservationDto entityToDto(Reservation reservation){
        return modelMapperConfig.modelMapper().map(reservation,ReservationDto.class);
    }


}
