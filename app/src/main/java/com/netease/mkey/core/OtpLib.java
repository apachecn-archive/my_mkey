package com.netease.mkey.core;

import java.util.Locale;

public class OtpLib {



    static {
        System.loadLibrary("mkey");
    }

    public static String my_otp( long e,String str, String str2) {

        return String.format(Locale.ENGLISH, "%06d", new Object[]{Integer.valueOf(getOtp(e, Long.parseLong(str), str2byte(str2)))});
    }

    public static byte[] str2byte(String str) {
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length(); i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }




    private static native int getOtp(long j, long j2, byte[] bArr);












}



