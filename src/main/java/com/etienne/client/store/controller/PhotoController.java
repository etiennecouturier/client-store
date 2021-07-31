package com.etienne.client.store.controller;

import com.etienne.client.store.model.domain.UserPhoto;
import com.etienne.client.store.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(path = "/photo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class PhotoController {

    private final PhotoService photoService;

    @GetMapping("/{userName}")
    public ResponseEntity<InputStreamResource> findPhotoByUserName(@PathVariable String userName) {
        return ResponseEntity
                .ok()
                .body(new InputStreamResource(photoService.findPhotoByUserName(userName)));
    }

    @PostMapping(path = "/new")
    @ResponseStatus(CREATED)
    public UserPhoto saveFile(@RequestParam("userName") String userName,
                              @RequestParam("file") MultipartFile file) throws IOException {
        return photoService.saveUserPhoto(userName, file);
    }

}
