package com.criptography.criptography;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Security;


public class Criptografia {
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String KEY = "B501BFBC85FEF7A2F1C4F754924A8385"; // 128-bit key
    private static final String IV = "00000000000000000000000000000000"; // 128-bit IV

    public static String encrypt(String json) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        SecretKeySpec keySpec = new SecretKeySpec(hexStringToByteArray(KEY), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(hexStringToByteArray(IV));

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(json.getBytes(StandardCharsets.UTF_8));

        // Converter os bytes criptografados para hexadecimal
        String encryptedHex = byteArrayToHexString(encrypted);

        // Retornar JSON com o resultado da criptografia
        return encryptedHex;
    }

    public static String decrypt(String encryptedHex) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM, "BC");
        SecretKeySpec keySpec = new SecretKeySpec(hexStringToByteArray(KEY), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(hexStringToByteArray(IV));

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] encryptedBytes = hexStringToByteArray(encryptedHex);
        byte[] decrypted = cipher.doFinal(encryptedBytes);

        // Converter os bytes de volta para uma string
        return new String(decrypted, StandardCharsets.UTF_8);
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    private static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
