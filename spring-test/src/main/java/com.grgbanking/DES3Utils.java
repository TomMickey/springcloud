package com.grgbanking;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.cxf.helpers.IOUtils;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class DES3Utils {

    private static String key = "feelviewlfyh@grgbanking.com";

    private static String flag = "VvvvV";

    private static final String KEY_ALGORITHM = "DESede";

    private static String charset = "utf-8";

    /**
      *默认的加密算法
      */
    private static final String DEFAULT_CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    /**
     * 加密
     * @param //text 待加密内容
     * @param //key 公钥 长度32字符
     * @return
     */
    public static List<String> DESEncrypt(List<String> lines) {
        if (lines == null || lines.size() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>(lines.size());
        try {
            //进行DES3加密后的内容的字节
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(charset));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey skey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.startsWith(flag)) {
                    result.add(line);
                } else {
                    byte[] encryptedData = cipher.doFinal(line.getBytes("utf-8"));
                    // 进行DES3加密后的内容进行BASE64编码
                    BASE64Encoder base64en = new BASE64Encoder();
                    String encryptStr = base64en.encode(encryptedData);
                    String newLine = flag + encryptStr;
                    result.add(newLine);
                }
            }
            return result;
        } catch (Exception e){
            log.info("DES3Utils encrypt error !", e);
        }
        return result;
    }

    /**
     * 加密
     * @return
     */
    public static String encrypt(String line) {
        String result = "";
        try {
            // 进行DES3加密后的内容的字节
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(charset));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey skey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.ENCRYPT_MODE, skey);
            byte[] encryptedData = cipher.doFinal(line.getBytes("utf-8"));
            // 进行DES3加密后的内容进行BASE64编码
            BASE64Encoder base64en = new BASE64Encoder();
            String encryptStr = base64en.encode(encryptedData);
            return encryptStr;
        } catch (Exception e){
            log.info("DES3Utils encrypt error !", e);
        }
        return result;
    }

    /**
     * 解密
     * @return
     */
    public static String decrypt(String line) {
        String result = "";
        try {
            // 进行DES3加密后的内容进行BASE64解码
            BASE64Decoder base64Decode = new BASE64Decoder();
            byte[] base64DValue = base64Decode.decodeBuffer(line);
            // 进行DES3解密后的内容的字节
            DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(charset));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey skey = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DESede");
            cipher.init(Cipher.DECRYPT_MODE, skey);
            byte[] encryptedData = cipher.doFinal(base64DValue);
            result = new String(encryptedData,"utf-8");
            return result;
        } catch (Exception e){
            log.info("DES3Utils decrypt error !", e);
        }
        return result;
    }

    /**
     * 解密
     * @param //text 待解密内容
     * @param //key 公钥
     * @return
     */
    public static List<String> DESDecrypt(List<String> lines) {
        if (lines == null || lines.size() == 0) {
            return null;
        }
        List<String> result = new ArrayList<>(lines.size());
        try {
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.startsWith(flag) ) {
                    String message = line.replaceAll(flag, "");
                    // 进行DES3加密后的内容进行BASE64解码
                    BASE64Decoder base64Decode = new BASE64Decoder();
                    byte[] base64DValue = base64Decode.decodeBuffer(message);
                    // 进行DES3解密后的内容的字节
                    DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(charset));
                    SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
                    SecretKey skey = keyFactory.generateSecret(dks);
                    Cipher cipher = Cipher.getInstance("DESede");
                    cipher.init(Cipher.DECRYPT_MODE, skey);
                    byte[] encryptedData = cipher.doFinal(base64DValue);
                    String newLine = new String(encryptedData,"utf-8");
                    result.add(newLine);
                } else {
                    result.add(line);
                }
            }
            return result;
        } catch (Exception e) {
            log.info("DES3Utils DESDecrypt error !", e);
        }
        return result;
    }

    /**
     * DESede 加密操作
     * @param content
     *            待加密内容
     * @param key
     *            加密密钥
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String key) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);
            // 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
            // 加密
            byte[] result = cipher.doFinal(byteContent);
            // 通过Base64转码返回
            return Base64.encodeBase64String(result);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     }
     * 生成加密秘钥
     *
     * @return
     */ private static SecretKeySpec getSecretKey() {
            //返回生成指定算法密钥生成器的KeyGenerator 对象
            KeyGenerator kg = null;
            try { kg = KeyGenerator.getInstance(KEY_ALGORITHM);
            kg.init(new SecureRandom(key.getBytes(charset)));
            //生成一个密钥
            SecretKey secretKey = kg.generateKey();
            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);
            // 转换为DESede专用密钥
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String getFileDes3(File file) {
        String encryptResult = null;
        if (!file.isFile()) {
            return null;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            InputStream inputStream = in;
            String result = IOUtils.toString(inputStream);
            encryptResult = encrypt(result, key);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BigInteger bigInt = new BigInteger(1, encryptResult.getBytes(StandardCharsets.UTF_8));
        String md5 = "00000000000000000000000000000000" + bigInt.toString(16);

        return md5.substring(md5.length() - 32);
    }

    /**
     * 获取文件MD5和文件大小
     * ----这里是获取文件MD5 不是加密的
     * @param file
     * @return filesize,md5
     * @throws //IOException
     */
    public synchronized static String getFileMD5String(File file) {
        return getFileMD5(file);
    }

    public static String getFileMD5(File file) {
        String encryptResult = null;
        if (!file.isFile()) {
            return null;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            InputStream inputStream = in;
            String result = IOUtils.toString(inputStream);
            encryptResult = encrypt(result, key);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        BigInteger bigInt = new BigInteger(1, encryptResult.getBytes(StandardCharsets.UTF_8));
        String md5 = "00000000000000000000000000000000" + bigInt.toString(16);
        return md5.substring(md5.length() - 32);
    }



    /**
     * 根据输入流获得文件MD5.
     *
     * @param inputStream
     * @return
     */
    public static String streamToMD5(InputStream inputStream) {
        try {
            String result = IOUtils.toString(inputStream);
            String encryptResult = encrypt(result, key);
            return toHexString(encryptResult.getBytes(StandardCharsets.UTF_8));
        } catch (IOException  e) {
            log.info("encryption exception", e);
        }
        return null;
    }

    private static String toHexString(byte[] md) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < md.length; i++) {
            if ((md[i] & 0xff) < 0x10) {
                sb.append("0");
            }
            sb.append(Long.toString(md[i] & 0xff, 16));
        }
        return sb.toString();
    }
}
