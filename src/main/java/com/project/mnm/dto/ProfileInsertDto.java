package com.project.mnm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ProfileInsertDto {
    private MultipartFile imageFile;
    private String name;
    private String sex;
    private int age;
}
