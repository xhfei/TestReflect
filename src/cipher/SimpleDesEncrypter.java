package cipher;

import javax.crypto.*; 
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import java.io.UnsupportedEncodingException; 

/**
 * 简单的加密算法，可以对输入的字符串进行加密、解密操作
 */

public class SimpleDesEncrypter {

    Cipher ecipher;

    Cipher dcipher;

    public SimpleDesEncrypter(SecretKey key) {

        try {

            ecipher = Cipher.getInstance("DES");

            dcipher = Cipher.getInstance("DES");

            ecipher.init(Cipher.ENCRYPT_MODE, key);

            dcipher.init(Cipher.DECRYPT_MODE, key);

        } catch (javax.crypto.NoSuchPaddingException e) {
        	e.printStackTrace();
        } catch (java.security.NoSuchAlgorithmException e) {
        	e.printStackTrace();

        } catch (java.security.InvalidKeyException e) {
        	e.printStackTrace();

        }

    }

    public String encrypt(String str) {

        try {

            // Encode the string into bytes using utf-8

            byte[] utf8 = str.getBytes("UTF8");

            // Encrypt

            byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string

            return new Base64().encodeToString(enc);

        } catch (javax.crypto.BadPaddingException e) {
        	e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
        	e.printStackTrace();

        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();

        } catch (java.io.IOException e) {
        	e.printStackTrace();

        }

        return null;

    }

    public String decrypt(String str) {

        try {

            // Decode base64 to get bytes

            byte[] dec = new Base64().decode(str);

            // Decrypt

            byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8

            return new String(utf8, "UTF8");

        } catch (javax.crypto.BadPaddingException e) {
        	e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
        	e.printStackTrace();

        } catch (UnsupportedEncodingException e) {
        	e.printStackTrace();

        } 

        return null;
 
    }
    public static void main(String...args){
    	byte[] dec = null;
        try {
            dec = new Base64().decode("XiBugLyDp98=");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	SecretKey key = new SecretKeySpec(dec, "DES");
    	SimpleDesEncrypter encrypter2 = new SimpleDesEncrypter(key);
    	String decrypted = encrypter2.decrypt("uEjvTiLoNgt//01lqwSJjzDo2eIAe0x4k");
    	System.out.println(decrypted);
    }
}
