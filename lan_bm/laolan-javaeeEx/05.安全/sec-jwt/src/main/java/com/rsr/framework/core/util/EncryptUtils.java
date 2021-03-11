package com.rsr.framework.core.util;

import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptUtils {
    private static final byte[] DES_KEY = new byte[]{-83, 107, -125, 67, -57, 76, -85, 112};

    public EncryptUtils() {
    }

    // https://blog.csdn.net/qq_32534441/article/details/91957908
    public static String encryptBasedDes(String data) {
        String encryptedData = null;

        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec deskey = new DESKeySpec(DES_KEY);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey key = keyFactory.generateSecret(deskey);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(1, key, sr);
//            encryptedData = (new BASE64Encoder()).encode(cipher.doFinal(data.getBytes()));
            Base64.Encoder encoder = Base64.getEncoder();
            encryptedData = encoder.encodeToString(cipher.doFinal(data.getBytes()));
            return encryptedData;
        } catch (Exception var7) {
            throw new RuntimeException("加密错误，错误信息：", var7);
        }
    }

    private static boolean calSecretLength(String secret) {
        if (StringUtils.isEmpty(secret)) {
            return false;
        } else {
            byte[] secretByte = secret.getBytes();
            int overplus = secretByte.length % 8;
            return overplus == 0;
        }
    }

}

