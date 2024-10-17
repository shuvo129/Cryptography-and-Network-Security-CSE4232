import java.math.BigInteger;

class Diffie {
    private static BigInteger power(BigInteger a, BigInteger b, BigInteger p) {
        if (b.equals(BigInteger.ONE))
            return a;
        else
            return a.modPow(b, p);
    }

    public static void main(String[] args) {
        BigInteger p, q, x, a, y, b, ka, kb;

        p = BigInteger.valueOf(353);
        System.out.println("The value of p: " + p);

        q = BigInteger.valueOf(3);
        System.out.println("The value of q: " + q);

        a = BigInteger.valueOf(97);
        System.out.println("The private key a for Alice: " + a);

        x = power(q, a, p);

        b = BigInteger.valueOf(233);
        System.out.println("The private key b for Bob: " + b);

        y = power(q, b, p);

        ka = power(y, a, p);
        kb = power(x, b, p);

        System.out.println("Secret key for Alice: " + ka);
        System.out.println("Secret key for Bob: " + kb);
    }
}
