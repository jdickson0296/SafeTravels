package com.example.safetravels;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.AlgorithmParameters;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESEncrypt {

    static void Encrypt(String filename, String password, Context ctx) throws Exception {

        // file to be encrypted
        FileInputStream inFile = new FileInputStream(filename);
        // encrypted file
        FileOutputStream outFile;
        File file_e = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "encryptedfile.des");
        outFile = new FileOutputStream(file_e);

        // password, iv and salt should be transferred to the other end
        // in a secure manner

        // salt is used for encoding
        // writing it to a file
        // salt should be transferred to the recipient securely
        // for decryption
        byte[] salt = new byte[8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        FileOutputStream saltOutFile;
        File file_s = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "salt.enc");
        saltOutFile = new FileOutputStream(file_s);
        saltOutFile.write(salt);
        saltOutFile.close();

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
        SecretKey secretKey = factory.generateSecret(keySpec);
        SecretKey secret = new SecretKeySpec(secretKey.getEncoded(), "AES");

        //
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        AlgorithmParameters params = cipher.getParameters();

        // iv adds randomness to the text and just makes the mechanism more
        // secure
        // used while initializing the cipher
        // file to store the iv
        FileOutputStream ivOutFile;
        File file_i = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "iv.enc");
        ivOutFile= new FileOutputStream(file_i);
        byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
        ivOutFile.write(iv);
        ivOutFile.close();

        //file encryption
        byte[] input = new byte[64];
        int bytesRead;

        while ((bytesRead = inFile.read(input)) != -1) {
            byte[] output = cipher.update(input, 0, bytesRead);
            if (output != null)
                outFile.write(output);
        }

        byte[] output = cipher.doFinal();
        if (output != null)
            outFile.write(output);
        else
            Toast.makeText(ctx, "File already exists ", Toast.LENGTH_LONG).show();

        inFile.close();
        outFile.flush();
        outFile.close();
        String path = Environment.getExternalStorageDirectory().getPath();
        String myencPath = path + "encryptedfile.des";
        Toast.makeText(ctx, "Saved to " + myencPath, Toast.LENGTH_LONG).show();


    }
}
