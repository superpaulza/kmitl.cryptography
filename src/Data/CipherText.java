package Data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

public class CipherText implements Serializable {
    private BigInteger a;
    private List<byte[]> b;

    public CipherText(BigInteger a, List<byte[]> b) {
        this.a = a;
        this.b = b;
    }

    public BigInteger getA() {
        return a;
    }

    public List<byte[]> getB() {
        return b;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("[");
        for (int i = 0; i < b.size(); i++) {
            BigInteger bigInteger = new BigInteger(b.get(i));
            str.append(bigInteger);
            if (i != b.size() - 1) {
                str.append(", ");
            }
        }
        str.append("]");
        return "CipherText{" +
                "a=" + a +
                ", b=" + str +
                '}';
    }
}
