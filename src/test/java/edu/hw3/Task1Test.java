package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Checking lower case letter")
    public void atbashCipherForLowerCaseLetterTest() {
        char ch = 'a';

        assertThat(Task1.atbashCipherForSymbol(ch)).isEqualTo('z');
    }

    @Test
    @DisplayName("Checking upper case letter")
    public void atbashCipherForUpperCaseLetterTest() {
        char ch = 'B';

        assertThat(Task1.atbashCipherForSymbol(ch)).isEqualTo('Y');
    }

    @Test
    @DisplayName("Checking not letter")
    public void atbashCipherForNotLetterTest() {
        char ch = '3';

        assertThat(Task1.atbashCipherForSymbol(ch)).isEqualTo('3');
    }

    @Test
    @DisplayName("Checking first string from examples")
    public void atbashCipherTest() {
        String s = "Hello world!";

        assertThat(Task1.atbashCipher(s)).isEqualTo("Svool dliow!");
    }

    @Test
    @DisplayName("Checking second string from examples")
    public void AtbashCipherBigStringTest() {
        String s = "Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";

        assertThat(Task1.atbashCipher(s)).isEqualTo("Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
    }
}
