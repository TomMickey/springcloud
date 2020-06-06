package grgbanking;

public class FileDataDefine {

	    /**
	     * ���ļ�������WEB��ַ
	     */
	    public static String baseFileServerSite = "";

	    public static String FileDCEServer = "true";
	    public static String FileDecryptType = "1";


	    /*
	     * �ֲ�������ȡ��������
	     */
	    public static String getBaseServerSite() {
	        return baseFileServerSite;
	    }

	    /**
	     * �ֲ�������ȡ��������
	     *
	     * @param serverSite
	     * @throws Exception
	     */
	    public static void setBaseServerSite(String serverSite) {
	        if (!serverSite.endsWith("/")) {
	            serverSite += "/";
	        }
	        baseFileServerSite = serverSite;
	    }





}
