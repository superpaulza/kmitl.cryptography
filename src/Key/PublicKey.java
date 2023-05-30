package Key;

import java.io.Serializable;
import java.math.BigInteger;

public class PublicKey implements Serializable {
    private BigInteger p;
    private BigInteger g;
    private BigInteger e;

    public PublicKey(BigInteger p, BigInteger g, BigInteger e) {
        this.p = p;
        this.g = g;
        this.e = e;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getG() {
        return g;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "PublicKey{" +
                "p=" + p +
                ", g=" + g +
                ", e=" + e +
                '}';
    }
}