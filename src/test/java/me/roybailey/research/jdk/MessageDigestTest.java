package me.roybailey.research.jdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Testing MessageDigest SHA_256 hashing algorithm
 */
public class MessageDigestTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String toconvert = "test";
        if (args.length >= 1) {
            toconvert = args[0];
        }
        System.out.println("encoding " + toconvert);
        MessageDigest md = MessageDigest.getInstance("SHA_256");
        byte[] digest = md.digest(toconvert.getBytes());
        String hash = new String(digest);
        System.out.println("encoded  " + hash);
    }
}
