package Elgamal;

import Key.*;
import Utils.BigIntHelper;

import java.math.BigInteger;
import java.util.Random;

public class ElgamalGenerator {
    private BigInteger generatePrime(int keySize) throws Exception {
        BigIntHelper bh = new BigIntHelper();
        if(keySize < 8) throw new Exception("keySize must greater then 8 bit");
        BigInteger prime = bh.randomBigInt(keySize);
        while (!isPrime(prime, 100)) {
            prime = bh.randomBigInt(keySize);
        }
        return prime;
    }

    public KeyPair createKeyPair(int keySize) throws Exception {
        BigIntHelper bh = new BigIntHelper();
        BigInteger p = generatePrime(keySize);
        BigInteger g = findGenerator(p);
        BigInteger u = bh.randomBigInt(BigInteger.TWO, p.subtract(BigInteger.ONE));
        BigInteger e = g.modPow(u, p);
        PublicKey publicKey = new PublicKey(p, g, e);
        PrivateKey privateKey = new PrivateKey(u);
        return new KeyPair(publicKey, privateKey);
    }

    private boolean isPrime(BigInteger number, int iterations) {
        // Check for some small primes
        if (number.compareTo(BigInteger.ONE) <= 0) return false;
        if (number.compareTo(BigInteger.valueOf(2)) <= 0) return true;
        if (number.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) return false;

        // Perform Miller-Rabin primality test
        BigInteger d = number.subtract(BigInteger.ONE);
        int s = 0;

        while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.valueOf(2));
            s++;
        }

        Random random = new Random();
        for (int i = 0; i < iterations; i++) {
            BigInteger a = new BigInteger(number.bitLength(), random).mod(number.subtract(BigInteger.ONE)).add(BigInteger.ONE);
            BigInteger x = a.modPow(d, number);

            if (x.equals(BigInteger.ONE) || x.equals(number.subtract(BigInteger.ONE)))
                continue;

            boolean composite = true;
            for (int j = 0; j < s - 1; j++) {
                x = x.modPow(BigInteger.valueOf(2), number);
                if (x.equals(BigInteger.ONE)) return false;
                if (x.equals(number.subtract(BigInteger.ONE))) {
                    composite = false;
                    break;
                }
            }
            if (composite) return false;
        }
        return true;
    }

    private BigInteger findGenerator(BigInteger prime) {
        BigIntHelper bh = new BigIntHelper();
        BigInteger alpha = bh.randomBigInt(BigInteger.TWO, prime.subtract(BigInteger.ONE));
        while (!isGenerator(alpha, prime)) {
            alpha = alpha.negate().mod(prime);
        }
        return alpha;
    }

    private boolean isGenerator(BigInteger alpha, BigInteger prime) {
        BigInteger exponent = prime.subtract(BigInteger.ONE).divide(BigInteger.TWO);
        BigInteger result = alpha.modPow(exponent, prime);
        return !result.equals(BigInteger.ONE);
    }
}
