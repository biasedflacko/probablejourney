import java.security.*;
import java.util.Scanner;

public class DigitalSignature {
    public static void main(String args[]) throws Exception {
        // Take user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter some text:");
        String msg = sc.nextLine();

        // Generate a key pair (private and public keys)
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");
        keyPairGen.initialize(2048);  // Use a 2048-bit key for security
        KeyPair pair = keyPairGen.generateKeyPair();
        
        // Get the private key for signing
        PrivateKey privKey = pair.getPrivate();
        
        // Create a Signature object
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initSign(privKey);
        
        // Convert the message to bytes
        byte[] bytes = msg.getBytes();
        
        // Update the Signature object with the message bytes
        sign.update(bytes);
        
        // Generate the digital signature
        byte[] signature = sign.sign();
        
        // Print the digital signature in Base64 encoding to make it readable
        System.out.println("Digital signature for given text (in Base64 encoding): " + bytesToBase64(signature));
        sc.close();
    }

    // Helper method to convert byte array to Base64 encoded string
    private static String bytesToBase64(byte[] byteArray) {
        return java.util.Base64.getEncoder().encodeToString(byteArray);
    }
}
