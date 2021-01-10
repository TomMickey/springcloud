package com.grgbanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/imgs/")
public class PictureController {

    @RequestMapping("test")
    public void test(HttpServletResponse response) throws IOException {
         //写给浏览器
         response.setContentType("image/jpeg");
        //浏览器不要缓存
         response.setDateHeader("expries", -1);
         response.setHeader("Cache-Control", "no-cache");
         response.setHeader("Pragma", "no-cache");
         BufferedImage buffImg = ImageIO.read(new FileInputStream("D:\\1.png"));
         ImageIO.write(buffImg, "jpg", response.getOutputStream());
    }
}
