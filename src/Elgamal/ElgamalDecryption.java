package Elgamal;

import Data.CipherText;
import Key.PrivateKey;
import Key.PublicKey;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ElgamalDecryption {
    public byte[] decrypt(CipherText cipher, PrivateKey sk, PublicKey pk) {
        BigInteger a = cipher.getA();
        List<byte[]> b = cipher.getB();
        BigInteger u = sk.getU();
        BigInteger p = pk.getP();
        BigInteger exponent = p.subtract(BigInteger.ONE).subtract(u);
        BigInteger c = a.modPow(exponent, p);
        List<Byte> decryptedBytes = new ArrayList<>();

        b.forEach(element -> {
            BigInteger data = new BigInteger(element).multiply(c).mod(p);
            byte[] bytes = data.toByteArray();
            for (byte n : bytes) {
                decryptedBytes.add(n);
            }
        });

        byte[] decryptedData = new byte[decryptedBytes.size()];
        for (int i = 0; i < decryptedBytes.size(); i++) {
            decryptedData[i] = decryptedBytes.get(i);
        }

        return decryptedData;
    }
}
