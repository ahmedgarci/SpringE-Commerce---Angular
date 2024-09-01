package com.example.SpringE_Commerce.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.HttpHeaders;

import com.example.SpringE_Commerce.Entities.Image;
import com.example.SpringE_Commerce.Exceptions.ResourceNotFoundException;
import com.example.SpringE_Commerce.Responses.ApiResponse;
import com.example.SpringE_Commerce.Service.Image.ImageDTO;
import com.example.SpringE_Commerce.Service.Image.ImageService;

import lombok.RequiredArgsConstructor;

import java.sql.SQLException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadImages(@RequestParam List<MultipartFile> files, @RequestParam Long ProductId ) {
        try{
            List<ImageDTO> images = imageService.saveImage(files, ProductId);
            return ResponseEntity.ok(new ApiResponse("uploaded successfully",images));
        }catch(Exception e){
            return ResponseEntity.status(500).body(new ApiResponse("upload failed",null));
        }
       
    }
    
    @GetMapping("/image/download/{imageId}")
    public ResponseEntity<ByteArrayResource> downloadImage(@PathVariable Long imageId) throws SQLException{
        Image image = imageService.getImageById(imageId);
        ByteArrayResource ressource = new ByteArrayResource(image.getImage().getBytes(1,(int)image.getImage().length()));
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(image.getFileType()))
        .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+ image.getFileName())
        .body(ressource);
    }
    
    @PostMapping("/{imageId}/update")
    public ResponseEntity<ApiResponse> updateImage(@RequestBody MultipartFile file,@PathVariable Long imageId){
        try {
            imageService.updateImage(file, imageId);
            return ResponseEntity.ok(new ApiResponse("updated successfully", imageId));         
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }   
    }

    @DeleteMapping("/{imageId}/delete")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId){
        try {
            imageService.deleteImageById(imageId);
            return ResponseEntity.ok(new ApiResponse("deleted successfully", imageId));         
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), null));
        }   
    }




}
