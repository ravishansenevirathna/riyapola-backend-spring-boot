package lk.afsd.riyapola.controller;

import lk.afsd.riyapola.dto.MailDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private JavaMailSender javaMailSender;


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
}
