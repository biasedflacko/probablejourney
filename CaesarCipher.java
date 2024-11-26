
import java.io.*;
import java.util.*;

public class CaesarCipher {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the text to be encrypted: ");
        String text = sc.nextLine();
        System.out.println("Enter the key: ");
        int key = sc.nextInt();
        String encryptedText = encrypt(text, key);
        System.out.println("Encrypted text: " + encryptedText);
        String decryptedText = decrypt(encryptedText, key);
        System.out.println("Decrypted text: " + decryptedText);
        sc.close();
    }

    public static String encrypt(String text, int key) {
        key %= 26;
        String encryptedString = "";
        for(int i = 0;i < text.length();i++){
            char ch = text.charAt(i);
            if(Character.isLetter(ch)){
                if(Character.isUpperCase(ch)){
                    char up = (char) (ch + key);
                    if(up > 'Z') {
                        int diff = up - 'Z' - 1;
                        up = (char) ('A' + diff);
                    }
                    encryptedString += up;
                }
                else {
                    char low = (char) (ch + key);
                    if(low > 'z') {
                        int diff = low - 'z' - 1;
                        low = (char) ('a' + diff);
                    }
                    encryptedString += low;
                }
            }
            else {
                encryptedString += ch;
            }
        }
        return encryptedString;
    }


    public static String decrypt(String text, int key){
        return encrypt(text, 26 - (key%26));
    }
}
