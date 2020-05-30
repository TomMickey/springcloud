package com.grgbanking;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientSocket {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(8080));
        System.out.println(socket);
    }
}
