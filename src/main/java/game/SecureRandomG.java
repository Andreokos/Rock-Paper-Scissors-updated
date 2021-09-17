package game;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRandomG {

    private static final SecureRandom secureRandom = new SecureRandom();

    public static int random_index(int ind) {
        int index_word = secureRandom.nextInt(ind);
        return index_word;
    }

    public  static String secret_key() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(secureRandom);
        SecretKey secretKey = keyGen.generateKey();
        String key = new BigInteger(1, secretKey.getEncoded()).toString(16);
        return key;
    }
}
