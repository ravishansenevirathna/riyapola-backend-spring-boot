package lk.afsd.riyapola.controller;

import lk.afsd.riyapola.dto.AdminDto;
import lk.afsd.riyapola.entity.Admin;
import lk.afsd.riyapola.repo.AdminRepo;
import lk.afsd.riyapola.service.AdminService;
import lk.afsd.riyapola.util.JWTTokenGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/11/2024
 * Created time : 4:20 PM
 */
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
        AdminDto save = adminService.registerAdmin(adminDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public Map<String, String> loginAdmin(@RequestBody AdminDto adminDto) {
        Map<String, String> response = new HashMap<>();

        Admin adminByUserNameAndPassword = adminRepo.findAdminByUserNameAndPassword(adminDto.getUserName(), adminDto.getPassword());

        if (adminByUserNameAndPassword != null) {
            String token = this.jwtTokenGenerator.generateJwtTokenForAdmin(adminByUserNameAndPassword);
            response.put("token", token);
        } else {
            response.put("massage", "wrong Credentials");
        }
        return response;
    }
}
