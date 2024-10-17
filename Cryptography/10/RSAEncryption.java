import java.math.BigInteger;

public class RSAEncryption {

    // Method to perform RSA encryption
    public static BigInteger encrypt(BigInteger M, BigInteger e, BigInteger n) {
        // Encryption: C = M^e mod n
        return M.modPow(e, n);
    }

    // Method to perform RSA decryption
    public static BigInteger decrypt(BigInteger C, BigInteger d, BigInteger n) {
        // Decryption: M = C^d mod n
        return C.modPow(d, n);
    }

    // Helper method to encrypt a large number by splitting into blocks
    public static String encryptMessage(String message, BigInteger e, BigInteger n) {
        StringBuilder encryptedMessage = new StringBuilder();

        // Break the message into smaller chunks, each smaller than 'n'
        int blockSize = String.valueOf(n).length() - 1;  // Block size based on modulus size
        for (int i = 0; i < message.length(); i += blockSize) {
            // Get the current block of the message
            String block = message.substring(i, Math.min(i + blockSize, message.length()));

            // Convert the block to BigInteger
            BigInteger M = new BigInteger(block);

            // Encrypt the block
            BigInteger encryptedBlock = encrypt(M, e, n);

            // Append the encrypted block to the final encrypted message
            encryptedMessage.append(encryptedBlock.toString());
        }

        return encryptedMessage.toString();
    }

    // Helper method to decrypt the encrypted message (split into blocks)
    public static String decryptMessage(String ciphertext, BigInteger d, BigInteger n) {
        StringBuilder decryptedMessage = new StringBuilder();

        // Block size for decryption (should match encryption)
        int blockSize = String.valueOf(n).length();  // Number of digits in 'n' (encrypted blocks are this size or larger)

        // Iterate through each block of the encrypted message
        for (int i = 0; i < ciphertext.length(); i += blockSize) {
            // Get the current encrypted block
            String block = ciphertext.substring(i, Math.min(i + blockSize, ciphertext.length()));

            // Convert the encrypted block to BigInteger
            BigInteger C = new BigInteger(block);

            // Decrypt the block
            BigInteger decryptedBlock = decrypt(C, d, n);

            // Append the decrypted block to the final decrypted message
            decryptedMessage.append(decryptedBlock.toString());
        }

        return decryptedMessage.toString();
    }

    public static void main(String[] args) {
        // Sample values
        String plaintext = "6882326879666683";  // Plaintext message (M)

        BigInteger e = new BigInteger("79");       // Public exponent (e)
        BigInteger d = new BigInteger("1019");     // Private exponent (d)
        BigInteger n = new BigInteger("3337");     // Modulus (n)

        // Encrypt the message in blocks
        String ciphertext = encryptMessage(plaintext, e, n);
        System.out.println("Encrypted text (C): " + ciphertext);

        // Decrypt the message to verify
        String decryptedMessage = decryptMessage(ciphertext, d, n);
        System.out.println("Decrypted text (M): " + decryptedMessage);
    }
}
