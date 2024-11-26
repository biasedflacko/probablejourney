import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class DESAlgorithm {
    public static void main(String[] argv) {
        try {
            System.out.println("Message Encryption Using DES Algorithm\n-----");

            // Step 1: Key Generation
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();
            System.out.println("Generated Key: " + Base64.getEncoder().encodeToString(myDesKey.getEncoded()));

            // Step 2: Initialize Cipher
            Cipher desCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

            // Step 3: Encrypt the message
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte[] text = "helloworld".getBytes();
            System.out.println("Message [Byte Format]: " + Base64.getEncoder().encodeToString(text));
            System.out.println("Message: " + new String(text));

            byte[] textEncrypted = desCipher.doFinal(text);
            System.out.println("Encrypted Message: " + Base64.getEncoder().encodeToString(textEncrypted));

            // Step 4: Decrypt the message
            desCipher.init(Cipher.DECRYPT_MODE, myDesKey);
            byte[] textDecrypted = desCipher.doFinal(textEncrypted);
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
