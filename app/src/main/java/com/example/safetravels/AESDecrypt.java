package com.example.safetravels;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.spec.KeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AESDecrypt {

    static void Decrypt(String file_format, String password, Context ctx) throws Exception {

        try {
            // reading the salt
            // user should have secure mechanism to transfer the
            // salt, iv and password to the recipient
            String path = ctx.getFilesDir().getAbsolutePath();
            File file_s = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "salt.enc");
            FileInputStream saltFis = new FileInputStream(file_s);
            byte[] salt = new byte[8];
            saltFis.read(salt);
            saltFis.close();
//           // delete enc file
//           new File("salt.enc").delete();


            // reading the iv
            File file_i = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "iv.enc");
            FileInputStream ivFis = new FileInputStream(file_i);
            byte[] iv = new byte[16];
            ivFis.read(iv);
            ivFis.close();
//           // delete enc file
//           new File("iv.enc").delete();


            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536, 256);
            SecretKey tmp = factory.generateSecret(keySpec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            // file decryption
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
            File file_e = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "encryptedfile.des");
            FileInputStream fis = new FileInputStream(file_e);

            FileOutputStream fos;
            File decryptfile = new File(ctx.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "decrypted_file");
            fos = new FileOutputStream(decryptfile + file_format);
            byte[] in = new byte[64];
            int read;
            while ((read = fis.read(in)) != -1) {
                byte[] output = cipher.update(in, 0, read);
                if (output != null)
                    fos.write(output);
            }

            byte[] output = cipher.doFinal();
            if (output != null)
                fos.write(output);
            fis.close();
            fos.flush();
            fos.close();

//           // delete encrypted file
//           new File("encryptedfile.des").delete();
            String mydecPath = path + "decrypted_file" + file_format;
            Toast.makeText(ctx, "Decrypted to " + mydecPath, Toast.LENGTH_LONG).show();
        }

        catch (BadPaddingException e) {
            Toast.makeText(ctx, "Incorrect key used for decryption", Toast.LENGTH_LONG).show();
        }
    }
}
