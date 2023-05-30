package Key;

import java.io.Serializable;
import java.math.BigInteger;

public class PrivateKey implements Serializable {

    private BigInteger u;

    public PrivateKey(BigInteger u) {
        this.u = u;
    }

    public BigInteger getU() {
        return u;
    }

    @Override
    public String toString() {
        return "PrivateKey{" +
                "u=" + u +
                '}';
    }
}