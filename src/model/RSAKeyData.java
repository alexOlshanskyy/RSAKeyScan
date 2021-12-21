package model;

import java.math.BigInteger;
import java.util.Objects;

public class RSAKeyData {
    private BigInteger n;
    private BigInteger e;
    private String username;
    private String originalKey;
    private BigInteger p;
    private BigInteger q;
    private boolean broken;

    public RSAKeyData(BigInteger n, BigInteger e, String username, String originalKey) {
        this.n = n;
        this.e = e;
        this.username = username;
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

    public String getUsername() {
        return username;
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
        RSAKeyData RSAKeyData = (RSAKeyData) o;
        return n.equals(RSAKeyData.n) &&
                e.equals(RSAKeyData.e) &&
                username.equals(RSAKeyData.username) &&
                originalKey.equals(RSAKeyData.originalKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(n, e, username, originalKey);
    }

    @Override
    public String toString() {
        return "KeyData{" +
                "n=" + n +
                ", e=" + e +
                ", username='" + username + '\'' +
                ", originalKey='" + originalKey + '\'' +
                '}';
    }
}
