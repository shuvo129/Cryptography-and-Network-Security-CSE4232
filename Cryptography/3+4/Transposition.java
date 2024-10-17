import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Transposition {

    public static String encrypt(String message, int col) {
        char[][] mat = new char[50][50];
        int i = 0, j = 0, k = 0;
        
        // Fill matrix with message
        while (k < message.length()) {
            mat[i][j] = message.charAt(k);
            k++;
            j++;
            if (j == col) {
                j = 0;
                i++;
            }
        }
        int row = i + 1;

        // Display the matrix
        System.out.println("Matrix:");
        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {
                // Check for both uppercase and lowercase letters, and space
                if ((mat[i][j] >= 'A' && mat[i][j] <= 'Z') || (mat[i][j] >= 'a' && mat[i][j] <= 'z') || mat[i][j] == ' ') {
                    System.out.print(mat[i][j] + "\t");
                }
            }
            System.out.println();
        }

        String result = "";
        i = 0;
        j = 0;
        k = 0;
        
        // Read matrix column by column
        while (k < row * col) {
            // Include both uppercase and lowercase letters, and space
            if ((mat[i][j] >= 'A' && mat[i][j] <= 'Z') || (mat[i][j] >= 'a' && mat[i][j] <= 'z') || mat[i][j] == ' ') {
                result += mat[i][j];
            }
            k++;
            i++;
            if (i == row) {
                i = 0;
                j++;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String message = readFromFile("plaintext.txt");
        int col;
        
        // Use Scanner for more compatibility in different environments
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of columns: ");
        col = scanner.nextInt();

        System.out.println("Message: " + message);

        System.out.print("Cipher: ");
        String cipher = encrypt(message, col);
        writeToFile("encrypt.txt", cipher);
        
        // Double encryption
        cipher = encrypt(cipher, col);
        writeToFile("double_encrypt.txt", cipher);
        
        scanner.close();
    }

    public static String readFromFile(String filename) {
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return content;
    }

    public static void writeToFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("Error writing to file");
        }
    }
}
