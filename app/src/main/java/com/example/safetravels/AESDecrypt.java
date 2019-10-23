package com.example.safetravels;

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

    static void Decrypt(String des_file, String file_format, String password) throws Exception {

        try {
            // reading the salt
            // user should have secure mechanism to transfer the
            // salt, iv and password to the recipient
            FileInputStream saltFis = new FileInputStream("salt.enc");
            byte[] salt = new byte[8];
            saltFis.read(salt);
            saltFis.close();
//           // delete enc file
//           new File("salt.enc").delete();



            // reading the iv
            FileInputStream ivFis = new FileInputStream("iv.enc");
            byte[] iv = new byte[16];
            ivFis.read(iv);
            ivFis.close();
//           // delete enc file
//           new File("iv.enc").delete();


            SecretKeyFactory factory = SecretKeyFactory
                    .getInstance("PBKDF2WithHmacSHA1");
            KeySpec keySpec = new PBEKeySpec(password.toCharArray(), salt, 65536,
                    256);
            SecretKey tmp = factory.generateSecret(keySpec);
            SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

            // file decryption
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
            FileInputStream fis = new FileInputStream(des_file);

            FileOutputStream fos = new FileOutputStream("decrypted_file" + file_format);
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

            System.out.println("File Decrypted");
        }

        catch (BadPaddingException e) {
            System.out.println("Incorrect key used for decryption");
        }
    }
}
