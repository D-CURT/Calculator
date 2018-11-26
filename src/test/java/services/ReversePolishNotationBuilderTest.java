package services;

import org.junit.Assert;
import org.junit.Test;


public class ReversePolishNotationBuilderTest {

    @Test
    public void simpleExpressionToRPN() {
        String expression = "2+2";
        String actual = "2 2 +";
        ReversePolishNotationBuilder builder = new ReversePolishNotationBuilder();

        String expected = builder.toRPN(expression);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void commonCompositeExpressionToRPN() {
        String expression = "2+2*3";
        String actual = "2 2 3 * +";
        ReversePolishNotationBuilder builder = new ReversePolishNotationBuilder();

        String expected = builder.toRPN(expression);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void commonBracketsToRPN() {
        String expression = "2+(2+3)";
        String actual = "2 2 3 + +";
        ReversePolishNotationBuilder builder = new ReversePolishNotationBuilder();

        String expected = builder.toRPN(expression);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void commonSinToRPN() {
        String expression = "sin30";
        String actual = "30 sin ";
        ReversePolishNotationBuilder builder = new ReversePolishNotationBuilder();

        String expected = builder.toRPN(expression);
        Assert.assertEquals(expected, actual);
    }
}