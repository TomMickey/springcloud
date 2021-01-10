package com.grgbanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @RequestMapping("test2")
    public void test2(HttpServletResponse response) throws IOException {
        try {
                        response.setContentType("text/html");
                         String xmlImg = GetImageStr("D:\\1.png");
                         System.out.println(xmlImg);
                        response.getWriter().write("<html><body><img src='data:image/jpeg;base64,"+xmlImg+"'/></body></html>");
                     } catch (Exception ex) {
                        System.out.println(ex.toString());
                     }
            }

    public static String GetImageStr(String imgFilePath) {
         // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
         byte[] data = null;

         // 读取图片字节数组
         try {
                 InputStream in = new FileInputStream(imgFilePath);
                 data = new byte[in.available()];
                 in.read(data);
                 in.close();
             } catch (IOException e) {
                 e.printStackTrace();
             }

         // 对字节数组Base64编码
         BASE64Encoder encoder = new BASE64Encoder();
         return encoder.encode(data);//返回字符串
    }

    @RequestMapping("/getFileImage")
    public void getFileImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String fileId = request.getParameter("fileId");
        String fileAddr = "D:\\fos.txt";
        if(fileAddr.endsWith(".png")||fileAddr.endsWith(".jpg")||fileAddr.endsWith(".jpeg")||fileAddr.endsWith(".bmp")){
            try {
                response.setContentType("text/html");
                String xmlImg = GetImageStr(fileAddr);
                response.getWriter().write("<html><body><img src='data:image/jpeg;base64,"+xmlImg+"'/></body></html>");
            } catch (Exception ex) {
                System.out.println(ex.toString());
            } finally {
                response.getWriter().close();;
            }
        } else {
            String fileName = fileAddr.substring(fileAddr.lastIndexOf(File.separator) + 1);
            downloadFile(fileName, new File(fileAddr),response);
        }

    }

    /**
     *  filename 下载到客户端后的文件名称
          *  file 待下载的文件
     *	下载 文件
     */
    public void downloadFile(String filename,  File file, HttpServletResponse response) {

        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(file);
            byte[] buf = new byte[4096];
            int readLength;
            setResponseHeader(response, filename);
            while (((readLength = inStream.read(buf)) != -1)) {
                response.getOutputStream().write(buf, 0, readLength);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inStream.close();
            } catch (Exception e) {

            }
        }
    }

    /**
     *  设置响应头
     * @param response
     * @param fileName 文件名称
     */
    protected void setResponseHeader(HttpServletResponse response, String fileName) {
        String suffix = fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String prefix = fileName.substring(0, fileName.lastIndexOf("."));
        try {
            response.reset();
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="
                    + new String(prefix.getBytes("GB2312"), "8859_1")
                    + suffix);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


}
