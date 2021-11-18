package com.project.mnm.controller;

import com.project.mnm.domain.Response;
import com.project.mnm.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user/image")
@RestController
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("")
    public Response getImage(@RequestParam("imagePath") String imagePath) {
        System.out.println("imagePath: " + imagePath);
        Response response = new Response();

        try {
            response.setResponse("success");
            response.setMessage("이미지 조회를 성공적으로 완료했습니다.");
            response.setData(imageService.getImage(imagePath));
        }
        catch (Exception e) {
            response.setResponse("failed");
            response.setMessage("이미지 조회를 하는 도중 오류가 발생했습니다.");
            response.setData(e.toString());
        }

        return response;
    }

}
