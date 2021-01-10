package com.grgbanking;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class TestNetty {

    public static void main(String[] args){
        for(int i = 0; i < 100000; i++){
            try {
                Socket socket=new Socket("192.168.44.4",12000);
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream);
                printWriter.write("Test");
                printWriter.flush();
                //Thread.sleep(1000);
                printWriter.write("sleep threads!");
                printWriter.flush();
                socket.shutdownOutput();
                socket.close();
            } catch (IOException  e) {
                e.printStackTrace();
            }
        }
    }
}
