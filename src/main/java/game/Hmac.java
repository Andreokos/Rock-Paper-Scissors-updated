package game;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

public class Hmac{

    public static final SecureRandomG computer = new SecureRandomG();
    public static String key = null;

    static {
        try {
            key = computer.secret_key();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    private static class HMAC{

        public static byte[] hmac256(String secretKey,String message){
            try{
                return hmac256(secretKey.getBytes("UTF-8"), message.getBytes("UTF-8"));
            }catch(Exception e){
                throw new RuntimeException("Failed to generate HMACSHA256 encrypt",e);
            }
        }

        public static byte[] hmac256(byte[] secretKey,byte[] message){
            byte[] hmac256;
            try{
                Mac mac = Mac.getInstance("HmacSHA256");
                SecretKeySpec sks = new SecretKeySpec(secretKey, "HmacSHA256");
                mac.init(sks);
                hmac256 = mac.doFinal(message);
                return hmac256;
            }catch(Exception e){
                throw new RuntimeException("Failed to generate HMACSHA256 encrypt ");
            }
        }
    }


    public static String hmac256(String word_generate) {

        byte[] hmacSha256 = Hmac.HMAC.hmac256(key, word_generate);

        return String.format("HMAC: %032x", new BigInteger(1, hmacSha256));
    }
}