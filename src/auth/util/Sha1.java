package auth.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import auth.EncoderInterface;

public class Sha1 implements EncoderInterface {
    @Override
    public String encode(String value) {
        try {
            MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
            msdDigest.update(value.getBytes(StandardCharsets.UTF_8), 0, value.length());
            return DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
