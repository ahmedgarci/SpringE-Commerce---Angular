package com.example.SpringE_Commerce.Service.Image;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.SpringE_Commerce.Entities.Image;

public interface IImageService {
    Image getImageById(Long id);
    void deleteImageById(Long id);
    List<ImageDTO> saveImage(List<MultipartFile> files,Long ProductId);
    Image updateImage(MultipartFile file,Long id);
}
