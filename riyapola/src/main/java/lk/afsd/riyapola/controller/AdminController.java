package lk.afsd.riyapola.controller;

import lk.afsd.riyapola.dto.AdminDto;
import lk.afsd.riyapola.entity.Admin;
import lk.afsd.riyapola.repo.AdminRepo;
import lk.afsd.riyapola.service.AdminService;
import lk.afsd.riyapola.util.JWTTokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/11/2024
 * Created time : 4:20 PM
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    private final AdminRepo adminRepo;
    private final JWTTokenGenerator jwtTokenGenerator;
    private final AdminService adminService;

    public AdminController(AdminRepo adminRepo, JWTTokenGenerator jwtTokenGenerator, AdminService adminService) {
        this.adminRepo = adminRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.adminService = adminService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerAdmin(@RequestBody AdminDto adminDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String encryptPwd = bCryptPasswordEncoder.encode(adminDto.getPassword());
        adminDto.setPassword(encryptPwd);

        AdminDto save = adminService.registerAdmin(adminDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> loginAdmin(@RequestBody AdminDto adminDto) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            Map<String, String> response = new HashMap<>();
            String adminByUserNameGetPw = adminRepo.findAdminByCredentials(adminDto.getUserName());
//        String use = adminByUserNameGetPw.getPassword();


//        if(bCryptPasswordEncoder.matches(adminDto.getPassword(),adminByUserNameGetPw)){
//            System.out.println("hi");
//        }

//        Admin adminByUserNameAndPassword = adminRepo.findAdminByUserNameAndPassword(adminDto.getUserName(), adminDto.getPassword());

            Admin adminByUserName = adminRepo.findAdminByUserName(adminDto.getUserName());


            if (adminByUserName != null && bCryptPasswordEncoder.matches(adminDto.getPassword(), adminByUserNameGetPw)) {
                String token = this.jwtTokenGenerator.generateJwtTokenForAdmin(adminByUserName);
                response.put("token", token);
                return new ResponseEntity<>(response,HttpStatus.OK);
            } else {
                response.put("massage", "wrong Credentials");
                return new ResponseEntity<>(response,HttpStatus.FORBIDDEN);
            }
    }



}