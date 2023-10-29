package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public final class Task1 {
    @SuppressWarnings("AnonInnerLength")
    private static final Map<Character, Character> ATBASH_CIPHER_MAP = new HashMap<>() {
        {
            put('a', 'z');
            put('z', 'a');
            put('b', 'y');
            put('y', 'b');
            put('c', 'x');
            put('x', 'c');
            put('d', 'w');
            put('w', 'd');
            put('e', 'v');
            put('v', 'e');
            put('f', 'u');
            put('u', 'f');
            put('g', 't');
            put('t', 'g');
            put('h', 's');
            put('s', 'h');
            put('i', 'r');
            put('r', 'i');
            put('j', 'q');
            put('q', 'j');
            put('k', 'p');
            put('p', 'k');
            put('l', 'o');
            put('o', 'l');
            put('m', 'n');
            put('n', 'm');
            put('A', 'Z');
            put('Z', 'A');
            put('B', 'Y');
            put('Y', 'B');
            put('C', 'X');
            put('X', 'C');
            put('D', 'W');
            put('W', 'D');
            put('E', 'V');
            put('V', 'E');
            put('F', 'U');
            put('U', 'F');
            put('G', 'T');
            put('T', 'G');
            put('H', 'S');
            put('S', 'H');
            put('I', 'R');
            put('R', 'I');
            put('J', 'Q');
            put('Q', 'J');
            put('K', 'P');
            put('P', 'K');
            put('L', 'O');
            put('O', 'L');
            put('M', 'N');
            put('N', 'M');
        }};

    private Task1() {}


    public static char atbashCipherForSymbol(char ch) {
        return ATBASH_CIPHER_MAP.getOrDefault(ch, ch);
    }

    public static String atbashCipher(String s) {
        char[] chars = s.toCharArray();

        int len = chars.length;

        for (int i = 0; i < len; ++i) {
            chars[i] = atbashCipherForSymbol(chars[i]);
        }

        return new String(chars);
    }
}
