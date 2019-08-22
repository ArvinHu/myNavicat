package com.milla.navicat.util.token;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @Package: com.milla.navicat.util.token
 * @Description: <md5加密类>
 * @Author: MILLA
 * @CreateDate: 2019/8/20 11:26
 * @UpdateUser: MILLA
 * @UpdateDate: 2019/8/20 11:26
 * @UpdateRemark: <>
 * @Version: 1.0
 */
public final class MD5Util {
    private static Charset charset_utf8 = Charset.forName("UTF-8");

    private static Charset charset_gbk = Charset.forName("GBK");

    public static String md5Sorted(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return md5(chars.toString());
    }

    public static String md5(String str) {
        String md5 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = str.getBytes(charset_utf8);
            bytes = digest.digest(bytes);
            md5 = toHexStr(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }


    private static String toHexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int var;
        for (byte b : bytes) {
            var = ((int) b) & 0xFF;
            if (var < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(var));
        }
        return sb.toString().toUpperCase();
    }

}
