package com.example.SpringE_Commerce.Service.Image;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;
import javax.sql.rowset.serial.SerialBlob;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.SpringE_Commerce.Entities.Image;
import com.example.SpringE_Commerce.Entities.Product;
import com.example.SpringE_Commerce.Exceptions.ResourceNotFoundException;
import com.example.SpringE_Commerce.Service.Product.ProductService;
import com.example.SpringE_Commerce.Service.Repositories.ImageRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService implements IImageService {

    private final ImageRepository imageRepository;
    private final ProductService productService;

    @Override
    public Image getImageById(Long id) {
    return imageRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("image not found")) ;       
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id)
        .ifPresentOrElse(imageRepository::delete,()->new ResourceNotFoundException("image not found"));
    }

    @Override
    public List<ImageDTO> saveImage(List<MultipartFile> files, Long ProductId) {
        Product product = productService.getProductById(ProductId);
        List<ImageDTO> savedImages = new ArrayList<>();
        for(MultipartFile file : files){
            try {
            Image image = new Image();
            
            image.builder()
            .fileName(file.getOriginalFilename()).fileType(file.getOriginalFilename())
            .image(new SerialBlob(file.getBytes())).product(product).build();
            
            imageRepository.save(image);
            image.setDownloadUrl("/api/v1/images/image/"+ image.getId());
            imageRepository.save(image);
            
            ImageDTO imageDto = new ImageDTO();
            imageDto.setImageId(image.getId());
            imageDto.setDownloadUrl(image.getDownloadUrl());
            imageDto.setImageName(image.getFileName()+image.getFileType());
            
            savedImages.add(imageDto);
            
        } catch (IOException | SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return savedImages;
    }

    @Override
    public Image updateImage(MultipartFile file, Long id) {
        Image image = getImageById(id);
        try {
            image.setFileName(file.getOriginalFilename());
            image.setFileType(getFileMime(file.getOriginalFilename()));
            image.setImage(new SerialBlob(file.getBytes()));   
            imageRepository.save(image);   
            return image; 
        } catch (Exception e) {
        throw new RuntimeErrorException(null, e.getMessage());
        }
     
    }

    private String getFileMime(String path){
     if (path.isEmpty() || path==null){
        return "";
     }   
     int PointIndex = path.lastIndexOf(".");
     if(PointIndex == -1){return "";}
     return path.substring(PointIndex+1);
    }
    


}
