package com.toan.ecommercedemo.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
    public static String saveFileWithSpecialNameByTime(MultipartFile file, String specialName) {
        try {
            String specialName2 = specialName.substring(1);
            int index = file.getOriginalFilename().lastIndexOf(".");
            String ext = file.getOriginalFilename().substring(index);
            String name = System.currentTimeMillis() + "-" + specialName2 + ext;

            Path pathAvatar = Paths.get(Constants.UPLOAD_FOLDER + Constants.folderImage + specialName + File.separator + name);
            Files.write(pathAvatar, file.getBytes());
            return "/" + name;
        } catch (IOException e) {
            return null;
        }
    }
}
