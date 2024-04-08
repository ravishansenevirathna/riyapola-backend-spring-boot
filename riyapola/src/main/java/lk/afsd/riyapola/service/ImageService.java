package lk.afsd.riyapola.service;

import lk.afsd.riyapola.dto.ImageDetailsGetDto;
import lk.afsd.riyapola.dto.ImageDto;
import lk.afsd.riyapola.entity.Images;
import lk.afsd.riyapola.repo.ImageRepo;
import lk.afsd.riyapola.util.ModelMapperConfig;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Hi 👋, I'm ravishansenevirathna
 * Project : riyapola
 * Created date : 4/1/2024
 * Created time : 11:15 AM
 */
@Service
public class ImageService {
    private final ModelMapperConfig modelMapperConfig;
    private final ImageRepo imageRepo;

    public ImageService(ModelMapperConfig modelMapperConfig, ImageRepo imageRepo) {
        this.modelMapperConfig = modelMapperConfig;
        this.imageRepo = imageRepo;
    }

    public ImageDetailsGetDto saveCarImage(ImageDto imageDto) throws IOException, URISyntaxException {
        String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
        File uploadDir = new File(projectPath + "/src/main/resources/static/uploads");

        uploadDir.mkdir();

        imageDto.getImageName().transferTo(new File(uploadDir.getAbsolutePath() + "/" + imageDto.getImageName().getOriginalFilename()));

        Images images = dtoToEntity(imageDto);
        images.setImageName("uploads/" + imageDto.getImageName().getOriginalFilename());

        Images save = imageRepo.save(images);
        return entityToDto(save);

    }







    public java.util.List<ImageDetailsGetDto> getAllCarsImages(){
        java.util.List<Images> all = imageRepo.findAll();
        List<ImageDetailsGetDto> list = new ArrayList<>();
        for (Images images : all) {
            ImageDetailsGetDto imageDetailsGetDto = entityToDto2(images);
            list.add(imageDetailsGetDto);
        }
        return list;
    }




    public String deleteCarImage(Integer id){
        if(imageRepo.existsById(id)){
            imageRepo.deleteById(id);


            return "Car Image Deleted";
        }
        return "No Car Image Found";
    }


    public Images updateCarImage(Integer id, ImageDto imageDto) throws IOException, URISyntaxException {

        String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();
        File uploadDir = new File(projectPath + "/src/main/resources/static/uploads");

        uploadDir.mkdir();

        imageDto.getImageName().transferTo(new File(uploadDir.getAbsolutePath() + "/" + imageDto.getImageName().getOriginalFilename()));

        Images images = dtoToEntity(imageDto);
        images.setImageName("uploads/" + imageDto.getImageName().getOriginalFilename());


        if(imageRepo.existsById(id)){
            Images save = imageRepo.save(images);

            return save;
        }
        return null;

    }


    public Optional<Images> selectImageName(Integer imageId) {
        Optional<Images> name = imageRepo.findById(imageId);

        return name;
    }



















    private Images dtoToEntity(ImageDto imageDto){
        return modelMapperConfig.modelMapper().map(imageDto, Images.class);
    }

    private ImageDetailsGetDto entityToDto(Images images){
        return modelMapperConfig.modelMapper().map(images,ImageDetailsGetDto.class);
    }

    private ImageDetailsGetDto entityToDto2(Images images){
        return modelMapperConfig.modelMapper().map(images,ImageDetailsGetDto.class);
    }


}
