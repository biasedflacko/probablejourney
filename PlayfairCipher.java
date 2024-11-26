import java.util.*;

public class PlayfairCipher {
    private static final int MATRIX_SIZE = 5;

    // Create the 5x5 key matrix
    public static char[][] generateKeyMatrix(String key) {
        boolean[] seen = new boolean[26];
        char[][] matrix = new char[MATRIX_SIZE][MATRIX_SIZE];
        key = key.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", ""); // Normalize key

        int index = 0;
        for (char c : key.toCharArray()) {
            if (!seen[c - 'A']) {
                seen[c - 'A'] = true;
                matrix[index / MATRIX_SIZE][index % MATRIX_SIZE] = c;
                index++;
            }
        }

        // Fill remaining letters
        for (char c = 'A'; c <= 'Z'; c++) {
            if (!seen[c - 'A'] && c != 'J') {
                matrix[index / MATRIX_SIZE][index % MATRIX_SIZE] = c;
                index++;
            }
        }
        return matrix;
    }

    // Prepares the plaintext for encryption
    public static String prepareText(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", ""); // Normalize text
        StringBuilder prepared = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            prepared.append(text.charAt(i));
            if (i < text.length() - 1 && text.charAt(i) == text.charAt(i + 1)) {
                prepared.append('X'); // Insert filler for repeated characters
            }
        }
        if (prepared.length() % 2 != 0) {
            prepared.append('X'); // Add filler if odd length
        }
        return prepared.toString();
    }

    // Finds the position of a character in the key matrix
    public static int[] findPosition(char[][] matrix, char c) {
        for (int i = 0; i < MATRIX_SIZE; i++) {
            for (int j = 0; j < MATRIX_SIZE; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null; // Should not happen
    }

    // Encrypts the plaintext using the key matrix
    public static String encrypt(String plaintext, char[][] matrix) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i += 2) {
            char a = plaintext.charAt(i);
            char b = plaintext.charAt(i + 1);
            int[] posA = findPosition(matrix, a);
            int[] posB = findPosition(matrix, b);

            if (posA[0] == posB[0]) { // Same row
                ciphertext.append(matrix[posA[0]][(posA[1] + 1) % MATRIX_SIZE]);
                ciphertext.append(matrix[posB[0]][(posB[1] + 1) % MATRIX_SIZE]);
            } else if (posA[1] == posB[1]) { // Same column
                ciphertext.append(matrix[(posA[0] + 1) % MATRIX_SIZE][posA[1]]);
                ciphertext.append(matrix[(posB[0] + 1) % MATRIX_SIZE][posB[1]]);
            } else { // Rectangle
                ciphertext.append(matrix[posA[0]][posB[1]]);
                ciphertext.append(matrix[posB[0]][posA[1]]);
            }
        }
        return ciphertext.toString();
    }

    public static String decrypt(String ciphertext, char[][] matrix) {
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < ciphertext.length(); i += 2) {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);
            int[] posA = findPosition(matrix, a);
            int[] posB = findPosition(matrix, b);

            if (posA[0] == posB[0]) { // Same row
                plaintext.append(matrix[posA[0]][(posA[1] + MATRIX_SIZE - 1) % MATRIX_SIZE]);
                plaintext.append(matrix[posB[0]][(posB[1] + MATRIX_SIZE - 1) % MATRIX_SIZE]);
            } else if (posA[1] == posB[1]) { // Same column
                plaintext.append(matrix[(posA[0] + MATRIX_SIZE - 1) % MATRIX_SIZE][posA[1]]);
                plaintext.append(matrix[(posB[0] + MATRIX_SIZE - 1) % MATRIX_SIZE][posB[1]]);
            } else { // Rectangle
                plaintext.append(matrix[posA[0]][posB[1]]);
                plaintext.append(matrix[posB[0]][posA[1]]);
            }
        }
        return plaintext.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input the key
        System.out.print("Enter key: ");
        String key = scanner.nextLine();

        // Generate the key matrix
        char[][] keyMatrix = generateKeyMatrix(key);
        System.out.println("Key Matrix:");
        for (char[] row : keyMatrix) {
            System.out.println(Arrays.toString(row));
        }

        // Input the plaintext
        System.out.print("Enter plaintext: ");
        String plaintext = scanner.nextLine();

        // Prepare the plaintext and encrypt
        String preparedText = prepareText(plaintext);
        String ciphertext = encrypt(preparedText, keyMatrix);
        String decryptedText = decrypt(ciphertext, keyMatrix);

        // Display the result
        System.out.println("Prepared Plaintext: " + preparedText);
        System.out.println("Ciphertext: " + ciphertext);
        System.out.println("Decrypted Text: " + decryptedText);
        scanner.close();

    }
}
