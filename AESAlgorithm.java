import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class AESAlgorithm {
    public static void main(String[] argv) {
        try {
            System.out.println("Message Encryption Using AES Algorithm\n-----");

            // Step 1: Key Generation
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // AES supports 128, 192, or 256-bit keys
            SecretKey myAesKey = keyGenerator.generateKey();
            System.out.println("Generated Key: " + Base64.getEncoder().encodeToString(myAesKey.getEncoded()));

            // Step 2: Initialize Cipher
            Cipher aesCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            // Step 3: Encrypt the message
            aesCipher.init(Cipher.ENCRYPT_MODE, myAesKey);
            byte[] text = "helloworld".getBytes(); // Plaintext
            System.out.println("Message [Byte Format]: " + Base64.getEncoder().encodeToString(text));
            System.out.println("Message: " + new String(text));

            byte[] textEncrypted = aesCipher.doFinal(text); // Encrypt the plaintext
            System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(textEncrypted));

            // Step 4: Decrypt the message
            aesCipher.init(Cipher.DECRYPT_MODE, myAesKey);
            byte[] textDecrypted = aesCipher.doFinal(textEncrypted); // Decrypt the ciphertext
            System.out.println("Decrypted Message: " + new String(textDecrypted));

        } catch (NoSuchAlgorithmException e) {
            System.err.println("No such algorithm: " + e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.err.println("No such padding: " + e.getMessage());
        } catch (InvalidKeyException e) {
            System.err.println("Invalid key: " + e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.err.println("Illegal block size: " + e.getMessage());
        } catch (BadPaddingException e) {
            System.err.println("Bad padding: " + e.getMessage());
        }
    }
}
