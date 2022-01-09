package utils;

import model.RSAKeyData;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.InvalidKeyException;
import java.security.InvalidParameterException;

import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("checkstyle:LineLength")
/**
 * CREDIT https://stackoverflow.com/questions/47816938/java-ssh-rsa-string-to-public-key
 */
public class ModulusExtractionUtil {
    private static final int VALUE_LENGTH = 4;
    private static final byte[] INITIAL_PREFIX =
            new byte[]{0x00, 0x00, 0x00, 0x07, 0x73, 0x73, 0x68, 0x2d, 0x72, 0x73, 0x61};
    private static final Pattern SSH_RSA_PATTERN = Pattern.compile("ssh-rsa[\\s]+([A-Za-z0-9/+]+=*)");

    /**
     * This method extracts RSA modulus and exponent from ssh-rsa key
     * @param key is the ssh-rsa key
     * @return the modulus and exponent of the key in RSAKeyData object
     * @throws InvalidKeyException is thrown if the format of the key is invalid
     */
    public static RSAKeyData parseSSHPublicKey(String key) throws InvalidKeyException {
        Matcher matcher = SSH_RSA_PATTERN.matcher(key.trim());
        if (!matcher.matches()) {
            throw new InvalidKeyException("Key format is invalid for SSH RSA.");
        }
        String keyStr = matcher.group(1);
        ByteArrayInputStream is = new ByteArrayInputStream(Base64.getDecoder().decode(keyStr));

        byte[] prefix = new byte[INITIAL_PREFIX.length];

        try {
            if (INITIAL_PREFIX.length != is.read(prefix)) {
                throw new InvalidKeyException("Initial [ssh-rsa] key prefix missed.");
            }

            BigInteger exponent = getValue(is);
            BigInteger modulus = getValue(is);
            RSAKeyData rsaKeyData = new RSAKeyData(modulus, exponent, key);
            return rsaKeyData;
        } catch (IOException e) {
            throw new InvalidKeyException("Failed to read SSH RSA certificate from string", e);
        }
    }

    /**
     *
     * @param is is the stream that contains the key
     * @return exponent of the key on first invocation and modulus on second invocation. The same is should be provided
     * @throws IOException is thrown if the key is not correctly formatted
     */
    private static BigInteger getValue(InputStream is) throws IOException {
        byte[] lenBuff = new byte[VALUE_LENGTH];
        if (VALUE_LENGTH != is.read(lenBuff)) {
            throw new InvalidParameterException("Unable to read value length.");
        }

        int len = ByteBuffer.wrap(lenBuff).getInt();
        byte[] valueArray = new byte[len];
        if (len != is.read(valueArray)) {
            throw new InvalidParameterException("Unable to read value.");
        }

        return new BigInteger(valueArray);
    }
}
