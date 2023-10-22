package edu.hw2;

public class Task1 {
    public sealed interface Expr {
        double evaluate();

        record Constant(double value) implements Expr {
            @Override
            public double evaluate() {
                return value;
            }
        }

        record Negate(Expr expr) implements Expr {
            Negate(double value) {
                this(new Constant(value));
            }

            @Override
            public double evaluate() {
                return expr.evaluate() * (-1);
            }
        }

        record Exponent(Expr expr1, Expr expr2) implements Expr {
            Exponent(Expr expr, double value) {
                this(expr, new Constant(value));
            }

            Exponent(double value, Expr expr) {
                this(new Constant(value), expr);
            }

            Exponent(double value1, double value2) {
                this(new Constant(value1), new Constant(value2));
            }

            @Override
            public double evaluate() throws IllegalArgumentException {
                if (expr1.evaluate() < 0 && expr2.evaluate() < 0
                    && expr2.evaluate() % 1 != 0) {
                    throw new IllegalArgumentException();
                }
                return Math.pow(expr1.evaluate(), expr2.evaluate());
            }
        }

        record Addition(Expr expr1, Expr expr2) implements Expr {
            Addition(Expr expr, double value) {
                this(expr, new Constant(value));
            }

            Addition(double value, Expr expr) {
                this(new Constant(value), expr);
            }

            Addition(double value1, double value2) {
                this(new Constant(value1), new Constant(value2));
            }

            @Override
            public double evaluate() {
                return expr1.evaluate() + expr2.evaluate();
            }
        }

        record Multiplication(Expr expr1, Expr expr2) implements Expr {

            Multiplication(Expr expr, double value) {
                this(expr, new Constant(value));
            }

            Multiplication(double value, Expr expr) {
                this(new Constant(value), expr);
            }

            Multiplication(double value1, double value2) {
                this(new Constant(value1), new Constant(value2));
            }

            @Override
            public double evaluate() {
                return expr1.evaluate() * expr2.evaluate();
            }
        }
    }

}
