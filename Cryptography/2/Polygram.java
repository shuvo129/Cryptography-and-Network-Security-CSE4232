import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Polygram {
    private Map<String, String> substitutionMap;

    public Polygram(Map<String, String> substitutionMap) {
        this.substitutionMap = substitutionMap;
    }

    public String encrypt(String plaintext) {
        String ciphertext = "";
        int polygramSize = 3; // Change this value for different polygram sizes

        for (int i = 0; i < plaintext.length(); i += polygramSize) {
            if (i + polygramSize <= plaintext.length()) {
                String polygram = plaintext.substring(i, i + polygramSize);
                String substitution = substitutionMap.get(polygram);
                if (substitution != null) {
                    ciphertext += substitution;
                } else {
                    ciphertext += polygram;
                }
            } else {
                ciphertext += plaintext.substring(i);
            }
        }

        return ciphertext.toString();
    }

    public String decrypt(String plaintext) {
        String ciphertext = "";
        int polygramSize = 3; // Change this value for different polygram sizes

        for (int i = 0; i < plaintext.length(); i += polygramSize) {
            if (i + polygramSize <= plaintext.length()) {
                String polygram = plaintext.substring(i, i + polygramSize);
                // get key from value
                String substitution = null;
                for (Map.Entry<String, String> entry : substitutionMap.entrySet()) {
                    if (entry.getValue().equals(polygram)) {
                        substitution = entry.getKey();
                        break;
                    }
                }
                if (substitution != null) {
                    ciphertext += substitution;
                } else {
                    ciphertext += polygram;
                }
            } else {
                ciphertext += plaintext.substring(i);
            }
        }

        return ciphertext.toString();
    }

    public static void main(String[] args) {
        // Define the substitution map
        Map<String, String> substitutionMap = new HashMap<>();
        substitutionMap.put("ABC", "XYZ");
        substitutionMap.put("DEF", "UVW");
        substitutionMap.put("GHI", "LMN");
        substitutionMap.put("JKL", "PNS");
        // Create the polygram substitution cipher object
        Polygram cipher = new Polygram(substitutionMap);

        // Encrypt a plaintext
        String result;
        String plaintext = readFile("./plaintext.txt");
        result = cipher.encrypt(plaintext);
        writeFile("./Chipertext.txt", result);
        result = cipher.decrypt(result);
        writeFile("./RecoverFile.txt", result);

    }

    public static String readFile(String filename) {
        String content = "";
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }
        return content.toString();
    }

    public static void writeFile(String filename, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(content);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }
}
