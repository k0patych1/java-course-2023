package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static edu.hw2.Task1.Expr.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public final class Task1Test {
    @Test
    @DisplayName("Constant test")
    public void constantTest() {
        var pi = new Constant(3.14);

        assertThat(pi.evaluate()).isEqualTo(3.14);
    }

    @Test
    @DisplayName("Negate test with Constants")
    public void negateConstantsTest() {
        var pi = new Constant(3.14);
        var negPi = new Negate(pi);

        assertThat(negPi.evaluate()).isEqualTo(-3.14);
    }

    @Test
    @DisplayName("Negate test with number")
    public void negateNumberTest() {
        var negPi = new Negate(3.14);

        assertThat(negPi.evaluate()).isEqualTo(-3.14);
    }

    @Test
    @DisplayName("Exponent test with Constants")
    public void exponentConstantsTest() {
        var c = new Constant(12);
        var pow = new Constant(5);
        var res = new Exponent(c, pow);

        assertThat(res.evaluate()).isEqualTo(Math.pow(12, 5));
    }

    @Test
    @DisplayName("Constant (raised) to the (power of) number")
    public void exponentConstantAndNumberTest() {
        var c = new Constant(12);
        var res = new Exponent(c, 5);

        assertThat(res.evaluate()).isEqualTo(Math.pow(12, 5));
    }

    @Test
    @DisplayName("Number (raised) to the (power of) Constant")
    public void exponentNumberAndConstantTest() {
        var pow = new Constant(5);
        var res = new Exponent(12, pow);

        assertThat(res.evaluate()).isEqualTo(Math.pow(12, 5));
    }

    @Test
    @DisplayName("Addition test with Constants")
    public void additionConstantsTest() {
        var c1 = new Constant(12);
        var c2 = new Constant(5);
        var res = new Addition(c1, c2);

        assertThat(res.evaluate()).isEqualTo(12 + 5);
    }

    @Test
    @DisplayName("Addition test with Constant and number")
    public void additionConstantAndNumberTest() {
        var c = new Constant(12);
        var res = new Addition(c, 5);

        assertThat(res.evaluate()).isEqualTo(12 + 5);
    }

    @Test
    @DisplayName("Addition test with number and Constant")
    public void additionNumberAndConstantTest() {
        var c = new Constant(5);
        var res = new Addition(12, c);

        assertThat(res.evaluate()).isEqualTo(12 + 5);
    }

    @Test
    @DisplayName("Multiplication test with Constants")
    public void multiplicationConstantsTest() {
        var c1 = new Constant(12);
        var c2 = new Constant(5);
        var res = new Multiplication(c1, c2);

        assertThat(res.evaluate()).isEqualTo(12 * 5);
    }

    @Test
    @DisplayName("Multiplication test with Constant and number")
    public void multiplicationConstantAndNumberTest() {
        var c = new Constant(12);
        var res = new Multiplication(c, 5);

        assertThat(res.evaluate()).isEqualTo(12 * 5);
    }

    @Test
    @DisplayName("Multiplication test with number and Constant")
    public void multiplicationNumberAndConstantTest() {
        var c = new Constant(5);
        var res = new Multiplication(12, c);

        assertThat(res.evaluate()).isEqualTo(12 * 5);
    }

    @Test
    @DisplayName("Negate to string test")
    void toStringTest() {
        var n = new Negate(new Constant(5));

        assertThat(n.toString()).isEqualTo("-5.0");
    }

    @Test
    @DisplayName("Exponent to string test")
    void exponentToStringTest() {
        var e = new Exponent(new Constant(5), 2);

        assertThat(e.toString()).isEqualTo("5.0 ^ 2.0");
    }

    @Test
    @DisplayName("Addition to string test")
    void additionToStringTest() {
        var e = new Addition(new Constant(5), 2);

        assertThat(e.toString()).isEqualTo("5.0 + 2.0");
    }

    @Test
    @DisplayName("Multiplication to string test")
    void multiplicationToStringTest() {
        var e = new Multiplication(new Constant(5), 2);

        assertThat(e.toString()).isEqualTo("5.0 * 2.0");
    }
}
