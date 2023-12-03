package edu.hw8.task3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class PasswordCracker {
    private static final String HASHING_ALGORITHM = "MD5";

    private final int passwordLength;

    public PasswordCracker(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public Map<String, String> crack(Map<String, String> hashedPasswords) throws NoSuchAlgorithmException {
        Map<String, String> out = new HashMap<>();
        for (Map.Entry<String, String> entry : hashedPasswords.entrySet()) {
            int i = 0;
            String password = generatePassword(i);

            while (!checkPassword(password, entry.getValue())) {
                ++i;
                password = generatePassword(i);
            }

            out.put(entry.getKey(), password);
        }
        return out;
    }

    private boolean checkPassword(String password, String hash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(HASHING_ALGORITHM);
        byte[] digest = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString().equals(hash);
    }
}
