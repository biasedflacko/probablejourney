import java.security.*;

public class SHA1Algorithm {
    public static void main(String[] a) {
        try {
            // Create SHA-1 MessageDigest instance
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            
            // Display message digest object information
            System.out.println("Message digest object info:\n ---------------- ");
            System.out.println("Algorithm = " + md.getAlgorithm());
            System.out.println("Provider = " + md.getProvider());
            System.out.println("ToString = " + md.toString());

            // Example 1: Hash of an empty string
            String input = "";
            md.update(input.getBytes());
            byte[] output = md.digest();
            System.out.println("\nSHA-1(\"" + input + "\") = " + bytesToHex(output));

            // Example 2: Hash of the string "abc"
            input = "abc";
            md.update(input.getBytes());
            output = md.digest();
            System.out.println("\nSHA-1(\"" + input + "\") = " + bytesToHex(output));

            // Example 3: Hash of the string "abcdefghijklmnopqrstuvwxyz"
            input = "abcdefghijklmnopqrstuvwxyz";
            md.update(input.getBytes());
            output = md.digest();
            System.out.println("\nSHA-1(\"" + input + "\") = " + bytesToHex(output));
            
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    // Converts byte array to hexadecimal string
    private static String bytesToHex(byte[] b) {
        char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        StringBuilder buf = new StringBuilder();
        for (byte aB : b) {
            buf.append(hexDigit[(aB >> 4) & 0x0f]);  // Get the high nibble
            buf.append(hexDigit[aB & 0x0f]);         // Get the low nibble
        }
        return buf.toString();
    }
}
