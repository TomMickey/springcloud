package com.grgbanking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class HelloController {

    @RequestMapping("/getServerPort")
    public String getServerPort(HttpServletRequest request) throws UnknownHostException {
        String host = request.getRemoteHost();
        String server = request.getServerName();
        int port = request.getServerPort();
        InetAddress inetAddr = InetAddress.getLocalHost();
        byte[] addr = inetAddr.getAddress();
        // Convert to dot representation
        String ipAddr = "";
        for (int i = 0; i < addr.length; i++) {
            if (i > 0) {
                ipAddr += ".";
            }
            ipAddr += addr[i] & 0xFF;
        }
        return ipAddr + "--->" + server+ ":" + host + ":" + port;
    }
}
