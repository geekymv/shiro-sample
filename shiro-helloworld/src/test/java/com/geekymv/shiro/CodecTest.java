package com.geekymv.shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;

import java.security.Key;

public class CodecTest {

    /**
     * base64 编解码
     */
    @Test
    public void testBase64() {
        String str = "hello";

        String s1 = Base64.encodeToString(str.getBytes());
        System.out.println("s1 = " + s1);

        String s2 = Base64.decodeToString(s1);
        System.out.println("s2 = " + s2);

        byte[] s3 = java.util.Base64.getDecoder().decode(s1.getBytes());
        System.out.println("s3 = " + new String(s3, 0, s3.length));
    }

    /**
     * 16进制字符串编解码
     */
    @Test
    public void testHex() {
        String str = "hello";
        String s1 = Hex.encodeToString(str.getBytes());
        System.out.println("s1 = " + s1);

        byte[] s2 = Hex.decode(s1);
        System.out.println("s2 = " + new String(s2, 0, s2.length));
    }

    @Test
    public void testMd5() {
        String str = "hello";
        String salt = "123";

        String s1 = new Md5Hash(str, salt).toBase64();
        System.out.println("s1 = " + s1);
    }

    @Test
    public void testMd5BySalt() {
        String username = "zhangsan";
        String password = "111111";

        String salt = new SecureRandomNumberGenerator().nextBytes().toBase64();
        System.out.println("salt = " + salt);

        String s2 = new Md5Hash(password, username, 2).toBase64();
        System.out.println("s2 = " + s2);
    }


    /**
     * 对称加密
     */
    @Test
    public void testAes() {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key = aesCipherService.generateNewKey();

        String algorithm = key.getAlgorithm();
        System.out.println("algorithm = " + algorithm);

        byte[] encoded = key.getEncoded();
        System.out.println("encoded = " + Hex.encodeToString(encoded));

        String str = "hello";
        String s1 = aesCipherService.encrypt(str.getBytes(), encoded).toBase64();
        System.out.println("s1 = " + s1);
        byte[] s2 = aesCipherService.decrypt(Base64.decode(s1), encoded).getBytes();
        System.out.println("s2 = " + new String(s2));
    }
}
