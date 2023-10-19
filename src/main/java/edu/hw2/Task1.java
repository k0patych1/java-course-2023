package edu.hw2;

public final class Task1 {
    private Task1() {}

    public sealed interface Expr {
        double evaluate();

        record Constant(double n) implements Expr {
            @Override
            public double evaluate() {
                return n;
            }

            @Override
            public String toString() {
                return Double.toString(n);
            }
        }

        record Negate(Expr expr) implements Expr {
            public Negate(double n) {
                this(new Constant(n));
            }

            @Override
            public double evaluate() {
                return -expr.evaluate();
            }

            @Override
            public String toString() {
                return Double.toString(evaluate());
            }
        }

        record Exponent(Expr expr1, Expr expr2) implements Expr {
            public Exponent(Expr expr, double c) {
                this(expr, new Constant(c));
            }

            public Exponent(double c, Expr expr) {
                this(new Constant(c), expr);
            }

            @Override
            public double evaluate() {
                return Math.pow(expr1.evaluate(), expr2.evaluate());
            }

            @Override
            public String toString() {
                return expr1 + " ^ " + expr2;
            }
        }

        record Addition(Expr expr1, Expr expr2) implements Expr {

            public Addition(Expr expr, double c) {
                this(expr, new Constant(c));
            }

            public Addition(double c, Expr expr) {
                this(new Constant(c), expr);
            }

            @Override
            public double evaluate() {
                return expr1.evaluate() + expr2.evaluate();
            }

            @Override
            public String toString() {
                return expr1 + " + " + expr2;
            }
        }

        record Multiplication(Expr expr1, Expr expr2) implements Expr {
            public Multiplication(Expr expr, double c) {
                this(expr, new Constant(c));
            }

            public Multiplication(double c, Expr expr) {
                this(new Constant(c), expr);
            }

            @Override
            public double evaluate() {
                return expr1.evaluate() * expr2.evaluate();
            }

            @Override
            public String toString() {
                return expr1 + " * " + expr2;
            }
        }
    }
}
