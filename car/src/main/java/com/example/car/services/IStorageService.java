package com.example.car.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IStorageService {

    void init();

    void store(MultipartFile file, String filename);

    Path load(String filename);

    Resource loadAsResource(String filename);

    void delete(String filename);

}
