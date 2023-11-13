package edu.hw5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task4Test {
    @Test
    public void strongPasswordTest() {
        String password = "12345!";
        assertTrue(Task4.isStrongPassword(password));
    }

    @Test
    public void strongPasswordOneLetterTest() {
        String password = "!";
        assertTrue(Task4.isStrongPassword(password));
    }

    @Test
    public void passwordTwoStrongLettersTest() {
        String password = "@!2%";
        assertTrue(Task4.isStrongPassword(password));
    }

    @Test
    public void weakPasswordTest() {
        String password = "12345678";
        assertFalse(Task4.isStrongPassword(password));
    }
}
