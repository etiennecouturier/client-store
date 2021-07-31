package com.etienne.client.store.service;

import com.etienne.client.store.model.domain.UserPhoto;
import com.etienne.client.store.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.apache.commons.io.IOUtils.toByteArray;
import static org.bson.BsonBinarySubType.BINARY;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PhotoService {

    private final PhotoRepository photoRepository;

    public InputStream findPhotoByUserName(String userName) throws IOException {
        return photoRepository.findUserPhotoByName(userName)
                .map(photo -> new ByteArrayInputStream(photo.getPhoto().getData()))
                .orElse(new ByteArrayInputStream(toByteArray(PhotoService.class.getResourceAsStream("/unknown.jpg"))));
    }

    public UserPhoto saveUserPhoto(String userName, MultipartFile file) throws IOException {
        return photoRepository.insert(new UserPhoto(userName, new Binary(BINARY, file.getBytes())));
    }

}
