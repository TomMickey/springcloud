package grgbanking;


import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

public class BackendServerMain extends Thread{
	
	public static void main(String[] args) {

		ResourceBundle bundle = ResourceBundle.getBundle("grg_system");
		Map<String, Object> map = getBundleMap(bundle);
		String serverIp = objToStr("SystemIp", map);

		BackendServerDataDefine.serverId = UUID.randomUUID().toString().replaceAll("-", "");
		BackendServerDataDefine.serverIP = serverIp == null || "".equals(serverIp) ? getLocalIP() : serverIp;
		BackendServerDataDefine.serverMAC = getMACAddress();


		String baseFileServerSite = objToStr("BaseFileServerSite", map);
		baseFileServerSite = baseFileServerSite.replaceAll("<ip>", BackendServerDataDefine.serverIP);
		FileDataDefine.setBaseServerSite(baseFileServerSite);
		/*BackendServerMain backendServerMain = new BackendServerMain();
		
		backendServerMain.start();*/
	}

	@Override
	public void run() {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle("grg_system");
			Map<String, Object> map = getBundleMap(bundle);
			String serverIp = objToStr("SystemIp", map);

			BackendServerDataDefine.serverId = UUID.randomUUID().toString().replaceAll("-", "");
			BackendServerDataDefine.serverIP = serverIp == null || "".equals(serverIp) ? getLocalIP() : serverIp;
			BackendServerDataDefine.serverMAC = getMACAddress();


			String baseFileServerSite = objToStr("BaseFileServerSite", map);
			baseFileServerSite = baseFileServerSite.replaceAll("<ip>", BackendServerDataDefine.serverIP);
			FileDataDefine.setBaseServerSite(baseFileServerSite);

//			new BackendRmiServer().start();
//			new BackendMqServer().start();
//			//new BackendMpServer().start();
//			new BackendRemoteServer().start();
//			new BackendSmsServer().start();
//			new BackendFileServer().start();
		} catch (Exception e) {
			//logger.error("BackendServerMain start servers error !", e);
		}
	}
	
	/**
	 * ��ȡ����IP
	 * 
	 * @return
	 */
	private static String getLocalIP() {
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
		return ipString;
	}

	// ��ȡMAC��ַ�ķ���
	private static String getMACAddress() {
		// ��������ǰ�mac��ַƴװ��String
		StringBuffer sb = new StringBuffer();
		try {
			InetAddress ia = InetAddress.getLocalHost();
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ���ַ�������Сд��ĸ��Ϊ��д��Ϊ�����mac��ַ������
		return sb.toString().toUpperCase();
	}
	
	public static Map<String, Object> getBundleMap(ResourceBundle bundle) {
		Map<String, Object> mapResult = new HashMap<String, Object>();
		Enumeration<String> keys = bundle.getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			String value = bundle.getString(key);
			mapResult.put(key, value);
		}
		return mapResult;
	}
	
	public static String objToStr(String key, Map<String, Object> obj) {
        return obj.get(key) == null ? "" : obj.get(key).toString().trim();
    }
}
