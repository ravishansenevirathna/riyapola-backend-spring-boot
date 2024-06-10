package lk.afsd.riyapola.controller;
import lk.afsd.riyapola.dto.CarDto;
import lk.afsd.riyapola.dto.MailDetailsDto;
import lk.afsd.riyapola.dto.ReservationDto;
import lk.afsd.riyapola.entity.Reservation;
import lk.afsd.riyapola.service.ReservationService;
import lk.afsd.riyapola.util.JWTTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/19/2024
 * Created time : 11:51 AM
 */

@CrossOrigin
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final JavaMailSender javaMailSender;

    private final JWTTokenGenerator jwtTokenGenerator;
    private final ReservationService reservationService;

    @Autowired
    public ReservationController(JavaMailSender javaMailSender, JWTTokenGenerator jwtTokenGenerator, ReservationService reservationService) {
        this.javaMailSender = javaMailSender;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.reservationService = reservationService;
    }


    @PostMapping("/addNewReservation")
    public ResponseEntity<Object> saveReservation(@RequestHeader(name = "Authorization") String authorizationHeader, @RequestBody ReservationDto reservationDto){
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            ReservationDto savedReservationDto = reservationService.saveReservation(reservationDto);
            return new ResponseEntity<>(savedReservationDto, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }



    @GetMapping("/getAllReservations")
    public ResponseEntity<Object> getAllReservations(){
        List<ReservationDto> GetDto = reservationService.getAllReservations();
       
        return new ResponseEntity<>(GetDto, HttpStatus.OK);
    }







    @PostMapping("/send/mail")
    public String sendEmail(@RequestBody MailDetailsDto mailDetailsDto){

        try {
            SimpleMailMessage message=new SimpleMailMessage();
            message.setSubject(mailDetailsDto.getSubject());
            message.setTo(mailDetailsDto.getToMail());
            message.setFrom("tharindurandika633@gmail.com");
            message.setText(mailDetailsDto.getMessage());

            javaMailSender.send(message);

            return "Success";
        }catch(Exception e){
            return e.getMessage();
        }


    }



    @PutMapping("/updateReservation/{reservationId}")
    public ResponseEntity<Object> updateReservation(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer reservationId, @RequestBody ReservationDto reservationDto){
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
            ReservationDto reservationDto1=reservationService.updateReservation(reservationId,reservationDto);

            return new ResponseEntity<>(reservationDto1,HttpStatus.OK);}
        else {
            return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
        }
    }



    @GetMapping("searchReservation/{cusId}")
    public ResponseEntity<Object> searchReservation(@RequestHeader(name = "Authorization") String authorizationHeader,@PathVariable Integer cusId){
        if (this.jwtTokenGenerator.validateJwtToken(authorizationHeader)) {
        List<ReservationDto> reservation=reservationService.searchReservation(cusId);

        return new ResponseEntity<>(reservation,HttpStatus.OK);}

         else {
                return new ResponseEntity<>("invalid Token", HttpStatus.FORBIDDEN);
            }
    }



}
