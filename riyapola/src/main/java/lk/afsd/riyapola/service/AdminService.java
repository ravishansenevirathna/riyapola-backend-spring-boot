package lk.afsd.riyapola.service;

import lk.afsd.riyapola.dto.AdminDto;
import lk.afsd.riyapola.dto.CustomerDto;
import lk.afsd.riyapola.entity.Admin;
import lk.afsd.riyapola.entity.Customer;
import lk.afsd.riyapola.repo.AdminRepo;
import lk.afsd.riyapola.util.ModelMapperConfig;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 3/11/2024
 * Created time : 4:18 PM
 */
@Service
public class AdminService {
    private final AdminRepo adminRepo;
    private final ModelMapperConfig modelMapperConfig;

    public AdminService(AdminRepo adminRepo, ModelMapperConfig modelMapperConfig) {
        this.adminRepo = adminRepo;
        this.modelMapperConfig = modelMapperConfig;
    }

    public AdminDto registerAdmin(AdminDto adminDto){

        Admin admin=dtoToEntity(adminDto);
        Admin save = adminRepo.save(admin);
        return entityToDto(save);
    }


    private Admin dtoToEntity(AdminDto adminDto){
        return modelMapperConfig.modelMapper().map(adminDto, Admin.class);
    }

    private AdminDto entityToDto(Admin admin){
        return modelMapperConfig.modelMapper().map(admin,AdminDto.class);
    }
}
