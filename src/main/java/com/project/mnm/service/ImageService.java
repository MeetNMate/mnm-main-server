package com.project.mnm.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Service
public class ImageService {
    public byte[] getImage(String imagePath) throws Exception {
        System.out.println(imagePath);
        FileInputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        String absolutePath = new File("").getAbsolutePath() + "\\";
        try {
            inputStream = new FileInputStream(absolutePath + imagePath);
        }
        catch (FileNotFoundException e) {
            throw new Exception("해당 파일을 찾을 수 없습니다.");
        }

        int readCount = 0;
        byte[] buffer = new byte[1024];
        byte[] fileArray = null;

        try {
            while((readCount = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, readCount);
            }
            fileArray = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();

        }
        catch (IOException e) {
            throw new Exception("파일을 변환하는데 문제가 발생했습니다.");
        }

        return fileArray;
    }

    public String saveProfileImage(Long uid, MultipartFile imageFile) throws Exception {
        String imagePath = null;
        String absolutePath = new File("").getAbsolutePath() + "\\";
        String path = "images/profile";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }

        if (!imageFile.isEmpty()) {
            String contentType = imageFile.getContentType();
            String originalFileExtension;
            if (ObjectUtils.isEmpty(contentType)) {
                throw new Exception("이미지 파일은 jpg, png 만 가능합니다.");
            } else {
                if (contentType.contains("image/jpeg")) {
                    originalFileExtension = ".jpg";
                } else if (contentType.contains("image/png")) {
                    originalFileExtension = ".png";
                } else {
                    throw new Exception("이미지 파일은 jpg, png 만 가능합니다.");
                }
            }
            imagePath = path + "/" + uid + originalFileExtension;
            file = new File(absolutePath + imagePath);
            imageFile.transferTo(file);
        }
        else {
            imagePath = "images/profile/default.png";
//            throw new Exception("이미지 파일이 비어있습니다.");
        }

        return imagePath;
    }
}
