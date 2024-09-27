package com.keniding.tomato.test.util;

import android.util.Base64;
import android.util.Log;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class CryptoUtils {
    private static final String ALGORITHM = "AES";
    private static final String TAG = "CryptoUtils";

    public static String decrypt(String dataCifrada, String key) throws Exception {
        Log.d(TAG, "Iniciando descifrado");
        Log.d(TAG, "Longitud de la clave codificada: " + key.length());

        // Convertir la clave a bytes UTF-8
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);

        // Crear un hash MD5 de la clave (CryptoJS usa MD5 para generar una clave de 16 bytes)
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] keyDigest = md.digest(keyBytes);

        SecretKeySpec secretKey = new SecretKeySpec(keyDigest, ALGORITHM);

        // Decodificar los datos cifrados de Base64
        byte[] cipherTextBytes = Base64.decode(dataCifrada, Base64.DEFAULT);

        // Extraer el salt (primeros 8 bytes) y los datos cifrados
        byte[] salt = Arrays.copyOfRange(cipherTextBytes, 8, 16);
        byte[] cipherText = Arrays.copyOfRange(cipherTextBytes, 16, cipherTextBytes.length);

        // Generar la clave y el IV usando el salt (similar a como lo hace CryptoJS)
        byte[] key_iv = generateKeyAndIV(keyBytes, salt);
        byte[] aesKey = Arrays.copyOfRange(key_iv, 0, 32);
        byte[] iv = Arrays.copyOfRange(key_iv, 32, 48);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(aesKey, ALGORITHM), ivSpec);

        byte[] decrypted = cipher.doFinal(cipherText);

        String result = new String(decrypted, StandardCharsets.UTF_8);
        Log.d(TAG, "Descifrado completado. Longitud del resultado: " + result.length());

        return result;
    }

    private static byte[] generateKeyAndIV(byte[] keyBytes, byte[] salt) throws Exception {
        final int keySize = 32;
        final int ivSize = 16;
        final int iterations = 1;

        byte[] keyAndIV = new byte[keySize + ivSize];

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] result = new byte[0];
        int currentPosition = 0;

        while (currentPosition < keyAndIV.length) {
            if (currentPosition > 0) {
                md.update(result);
            }
            md.update(keyBytes);
            md.update(salt);
            result = md.digest();

            System.arraycopy(result, 0, keyAndIV, currentPosition, Math.min(result.length, keyAndIV.length - currentPosition));
            currentPosition += result.length;
        }

        return keyAndIV;
    }
}
