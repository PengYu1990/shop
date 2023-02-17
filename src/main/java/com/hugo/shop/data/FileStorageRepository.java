package com.hugo.shop.data;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Repository
public class FileStorageRepository {


    @Value("${user.upload.images.product.path}")
    private String storagePath;

    public String save(String fileName, InputStream inputStream) {
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        String newFilename = UUID.randomUUID() + suffix;
        Path path = Path.of(storagePath).resolve(newFilename).normalize();
        try {
            Files.copy(inputStream, path);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return newFilename;
    }

    public void delete(String fileName) {
        if(fileName == null){
            return;
        }
        Path path = Path.of(storagePath).resolve(fileName).normalize();
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
