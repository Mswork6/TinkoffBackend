package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import edu.hw2.Task1.Expr.*;

public class Task1Test {
    @Test
    @DisplayName("Обработка операции отрицания с разным типом входных данных")
    void calculateNegateOperation() {
        // given
        Constant expression = new Constant(68);
        double value = 68;

        // when
        Negate expressionResult = new Negate(expression);
        Negate valueResult = new Negate(value);

        // then
        assertThat(expressionResult.evaluate()).isEqualTo(-68);
        assertThat(valueResult.evaluate()).isEqualTo(-68);
    }

    @Test
    @DisplayName("Обработка операции возведения в степень" +
        " с разным типом входных данных")
    void calculateExponentOperation() {
        // given
        Constant expression1 = new Constant(24);
        Constant expression2 = new Constant(5);

        double value1 = 24;
        double value2 = 5;

        // when
        Exponent result1 = new Exponent(expression1, expression2);
        Exponent result2 = new Exponent(expression1, value2);
        Exponent result3 = new Exponent(value1, expression2);
        Exponent result4 = new Exponent(value1, value2);

        // then
        assertThat(result1.evaluate()).isEqualTo(7962624);
        assertThat(result2.evaluate()).isEqualTo(7962624);
        assertThat(result3.evaluate()).isEqualTo(7962624);
        assertThat(result4.evaluate()).isEqualTo(7962624);
    }

    @Test
    @DisplayName("Обработка возведения в степень с некорректными даннными")
    void calculateIncorrectNegateOperation() {
        // given
        Constant expression1 = new Constant(-9);
        Constant expression2 = new Constant(-8.6);

        // when

        // then
        assertThrows(IllegalArgumentException.class, () -> new Exponent(expression1, expression2).evaluate());
    }

    @Test
    @DisplayName("Обработка операции сложения с разным типом входных данных")
    void calculateAdditionOperation() {
        // given
        Constant expression1 = new Constant(176);
        Constant expression2 = new Constant(287);

        double value1 = 176;
        double value2 = 287;

        // when
        Addition result1 = new Addition(expression1, expression2);
        Addition result2 = new Addition(expression1, value2);
        Addition result3 = new Addition(value1, expression2);
        Addition result4 = new Addition(value1, value2);

        // then
        assertThat(result1.evaluate()).isEqualTo(463);
        assertThat(result2.evaluate()).isEqualTo(463);
        assertThat(result3.evaluate()).isEqualTo(463);
        assertThat(result4.evaluate()).isEqualTo(463);
    }

    @Test
    @DisplayName("Обработка операции умножения с разным типом входных данных")
    void calculateMultiplicationOperation() {
        // given
        Constant expression1 = new Constant(34);
        Constant expression2 = new Constant(59);

        double value1 = 34;
        double value2 = 59;

        // when
        Multiplication result1 = new Multiplication(expression1, expression2);
        Multiplication result2 = new Multiplication(expression1, value2);
        Multiplication result3 = new Multiplication(value1, expression2);
        Multiplication result4 = new Multiplication(value1, value2);

        // then
        assertThat(result1.evaluate()).isEqualTo(2006);
        assertThat(result2.evaluate()).isEqualTo(2006);
        assertThat(result3.evaluate()).isEqualTo(2006);
        assertThat(result4.evaluate()).isEqualTo(2006);
    }

    @Test
    @DisplayName("Обработка разных операций с корректным ответом")
    void calculateCorrectOperation() {
        // given

        // when
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        // then
        assertThat(res.evaluate()).isEqualTo(37);
    }

}
