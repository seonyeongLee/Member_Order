package com.api.idus.common.utility;

import com.api.idus.common.exception.DisabledException;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
public class EncryptUtility {

    public static String SHA256Encrypt(String password){
        StringBuffer hexString = new StringBuffer();

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));


            for(int i=0; i < hash.length; i++){
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');

                hexString.append(hex);
            }

        }catch(SecurityException e){
            throw new DisabledException("SecurityException");
        }catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
            throw new DisabledException("SHA256Encrypt : 암호화 실패하였습니다.");
        }
        return hexString.toString().toUpperCase();
    }

}