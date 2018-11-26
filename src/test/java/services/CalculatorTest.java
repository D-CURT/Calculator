package services;

import abstractions.AbstractCalculator;
import beans.Result;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest extends AbstractCalculator {
    private Result actual;

    @Before
    public void setUp() throws Exception {
        this.actual = new Result("2+2", 4.0);
    }

    @Test
    public void calculate() {
        Result expected = new Calculator().calculate("2+2");
        Assert.assertEquals(expected, actual);
    }
}