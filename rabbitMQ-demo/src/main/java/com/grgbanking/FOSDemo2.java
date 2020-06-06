package com.grgbanking;

import java.io.FileOutputStream;
import java.io.IOException;

public class FOSDemo2 {
    public static void main(String[] args) throws IOException {
        /*
         * 在创建FOS时，若指定第二个参数，并且该值为true时，则是追加写操作。
         * 那么本次通过FOS写出的内容会被追加到该文件的末尾。
         */
        FileOutputStream fos = new FileOutputStream("D:\\fos.txt", true);

        fos.write("天安门上太阳升".getBytes("UTF-8"));
        System.out.println("写出完毕！");
        fos.close();
    }
}
