package com.grgbanking;

import java.io.ObjectInputStream.GetField;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestMain {
	
	public static void main(String[] args) throws UnknownHostException, SocketException {
		
		ResourceBundle bundle = ResourceBundle.getBundle("my", new Locale("zh", "CN"));
        String cancel = bundle.getString("cancelKey");
        System.out.println(cancel);

        bundle = ResourceBundle.getBundle("my", Locale.US);
        cancel = bundle.getString("cancelKey");
        System.out.println(cancel);

        bundle = ResourceBundle.getBundle("my", Locale.getDefault());
        cancel = bundle.getString("cancelKey");
        System.out.println(cancel);

        bundle = ResourceBundle.getBundle("my", Locale.GERMAN);
        cancel = bundle.getString("cancelKey");
        System.out.println(cancel);
        bundle = ResourceBundle.getBundle("my");
        for (String key : bundle.keySet()) {
            System.out.println(bundle.getString(key));
        }
        
        InetAddress ia = InetAddress.getLocalHost();
        
        System.out.println(ia.getHostName());
        
        System.out.println(ia.getHostAddress());
        
        System.out.println(ia.getLocalHost());

		StringBuffer sb = new StringBuffer();
        
        // �������ӿڶ��󣨼������������õ�mac��ַ��mac��ַ������һ��byte�����С�
		byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();

		for (int i = 0; i < mac.length; i++) {
			if (i != 0) {
				sb.append("-");
			}
			// mac[i] & 0xFF ��Ϊ�˰�byteת��Ϊ������
			String s = Integer.toHexString(mac[i] & 0xFF);
			sb.append(s.length() == 1 ? 0 + s : s);
		}
		
		System.out.println(sb.toString());
        
		getLocalIP();
		
	}
	
	public static void getLocalIP() {
		StringBuilder sb = new StringBuilder();
		try {
			Enumeration allNetInterfaces = NetworkInterface.getNetworkInterfaces();
			while (allNetInterfaces.hasMoreElements()) {
				NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
				Enumeration addresses = netInterface.getInetAddresses();
				while (addresses.hasMoreElements()) {
					InetAddress address = (InetAddress) addresses.nextElement();
					if (address != null && address instanceof Inet4Address) {
						if (address.getHostAddress() != null && !"127.0.0.1".equals(address.getHostAddress())) {
							if (sb.length() > 190) {
								continue;
							}
							sb.append((sb.length() == 0 ? "" : ";") + address.getHostAddress());
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String ipString = sb.toString().split(";", -2)[0];
		System.out.println("----");
		System.out.println(ipString);
	}

}
