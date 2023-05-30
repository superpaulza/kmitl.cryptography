package Utils;

import java.math.BigInteger;
import java.util.Random;

public class BigIntHelper {
    public BigInteger randomBigInt(BigInteger minLimit, BigInteger maxLimit) {
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        BigInteger res = new BigInteger(len, randNum);
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);
        return res;
    }

    public BigInteger randomBigInt(int keySize) {
        Random random = new Random();
        return new BigInteger(keySize, random).setBit(keySize - 1);
    }
}
