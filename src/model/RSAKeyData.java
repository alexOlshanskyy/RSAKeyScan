package model;

import java.math.BigInteger;
import java.util.Objects;

public class RSAKeyData {
    private BigInteger n;
    private BigInteger e;
    private String originalKey;
    private BigInteger p;
    private BigInteger q;
    private boolean broken;

    public RSAKeyData(BigInteger n, BigInteger e, String originalKey) {
        this.n = n;
        this.e = e;
        this.originalKey = originalKey;
        this.p = BigInteger.valueOf(-1);
        this.q = BigInteger.valueOf(-1);
        broken = false;

    }

    public BigInteger getN() {
        return n;
    }

    public BigInteger getE() {
        return e;
    }

    public String getOriginalKey() {
        return originalKey;
    }

    public BigInteger getP() {
        return p;
    }

    public BigInteger getQ() {
        return q;
    }

    public boolean isBroken() {
        return broken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true; }
        if (!(o instanceof RSAKeyData)) {return false; }
        RSAKeyData rsaKeyData = (RSAKeyData) o;
        return n.equals(rsaKeyData.n) &&
                e.equals(rsaKeyData.e) &&
                originalKey.equals(rsaKeyData.originalKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, e, originalKey);
    }

    @Override
    public String toString() {
        return
                "n=" + n +
                ",e=" + e;
    }
}
