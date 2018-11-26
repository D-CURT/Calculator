package services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class ReversePolishNotationBuilderTest {
    private ReversePolishNotationBuilder builder;

    @Before
    public void setUp() throws Exception {
        this.builder = new ReversePolishNotationBuilder();
    }

    @Test
    public void simpleExpressionToRPN() {
        String expression = "2+2";
        String actual = "2 2 +";

        String expected = builder.toRPN(expression);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void commonCompositeExpressionToRPN() {
        String expression = "2+2*3";
        String actual = "2 2 3 * +";

        String expected = builder.toRPN(expression);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void commonBracketsToRPN() {
        String expression = "2+(2+3)";
        String actual = "2 2 3 + +";

        String expected = builder.toRPN(expression);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void commonSinToRPN() {
        String expression = "sin30";
        String actual = "30 sin ";

        String expected = builder.toRPN(expression);
        Assert.assertEquals(expected, actual);
    }
}