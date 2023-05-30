package Elgamal;

import Data.CipherText;
import Key.PublicKey;
import Utils.BigIntHelper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ElgamalEncryption {
    public CipherText encrypt(String msg, PublicKey pk) {
        byte[] strByte = msg.getBytes();
        List<byte[]> b = new ArrayList<>();
        BigInteger p = pk.getP();
        BigInteger k = getRandomKey(p);
        BigInteger a = pk.getG().modPow(k, p);
        for (int i = 0; i < strByte.length; i++) {
            b.add(
                    pk.getE()
                            .modPow(k, p)
                            .multiply(BigInteger.valueOf(strByte[i]))
                            .mod(p)
                            .toByteArray()
            );
        }
        CipherText cipher = new CipherText(a, b);
        return cipher;
    }

    public CipherText encrypt(byte[] data, PublicKey pk) {
        List<byte[]> b = new ArrayList<>();
        BigInteger p = pk.getP();
        BigInteger k = getRandomKey(p);
        BigInteger a = pk.getG().modPow(k, p);
        for (int i = 0; i < data.length; i++) {
            b.add(
                    pk.getE()
                            .modPow(k, p)
                            .multiply(BigInteger.valueOf(data[i]))
                            .mod(p)
                            .toByteArray()
            );
        }
        CipherText cipher = new CipherText(a, b);
        return cipher;
    }

    private BigInteger getRandomKey(BigInteger prime) {
        BigIntHelper bh = new BigIntHelper();
        BigInteger k = bh.randomBigInt(BigInteger.TWO, prime.subtract(BigInteger.ONE));
        while (!prime.subtract(BigInteger.ONE).gcd(k).equals(BigInteger.ONE)) {
            k = bh.randomBigInt(BigInteger.TWO, prime.subtract(BigInteger.ONE));
        }
        return k;
    }
}
