package org.finance.profitcalculator.beans;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @InjectMocks
    private CalculatorService calculatorService;

    @Test
    public void shouldFailWhenGetMaxProfitInvokedWithNullInput() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("must have atleast two stock prices");

        calculatorService.getMaxProfit(null);
    }

    @Test
    public void shouldFailWhenGetMaxProfitInvokedWithEmptyArray() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("must have atleast two stock prices");

        calculatorService.getMaxProfit(new BigDecimal[]{});
    }

    @Test
    public void shouldFailWhenGetMaxProfitInvokedWithOnlyOnePrice() {
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("must have atleast two stock prices");

        calculatorService.getMaxProfit(covertToBigDecimal(new String[] {"10.25"}));
    }

    @Test
    public void shouldReturnCorrectProfitWhenPriceFluctuateThroughoutTheDay() {
        assertThat(calculatorService.getMaxProfit(covertToBigDecimal(new String[] {"10", "7", "5", "8", "11", "9", "10"})), is(new BigDecimal("6")));
    }

    @Test
    public void shouldReturnZeroProfitWhenPriceIsUnchangedThroughoutTheDay() {
        assertThat(calculatorService.getMaxProfit(covertToBigDecimal(new String[] {"10.0", "10.0", "10.0", "10.0", "10.0", "10.0", "10.0"})), is(new BigDecimal("0.0")));
    }

    @Test
    public void shouldReturnCorrectProfitWhenPriceIncreaseThroughoutTheDay() {
        assertThat(calculatorService.getMaxProfit(covertToBigDecimal(new String[] {"1.3", "4", "5", "8.01", "9", "10", "10.59"})), is(new BigDecimal("9.29")));
    }

    @Test
    public void shouldReturnCorrectProfitWhenPriceDropThroughoutTheDay() {
        assertThat(calculatorService.getMaxProfit(covertToBigDecimal(new String[] {"11.5", "9", "7", "6.4", "5.00", "4.23", "3.800"})), is(new BigDecimal("-0.430")));
    }

    @Test
    public void shouldReturnCorrectProfitWhenPricePeakAndDropThroughoutTheDay() {
        assertThat(calculatorService.getMaxProfit(covertToBigDecimal(new String[] {"2", "3.5", "5.23", "12.0", "9.0", "7.3", "5.78"})), is(new BigDecimal("10.0")));
    }

    @Test
    public void shouldReturnCorrectProfitWhenPriceDropAndPeakThroughoutTheDay() {
        assertThat(calculatorService.getMaxProfit(covertToBigDecimal(new String[] {"10", "9", "8.11", "5", "6", "7.3", "11.56"})), is(new BigDecimal("6.56")));
    }

    private BigDecimal[] covertToBigDecimal(String[] strArray) {
        return Stream.of(strArray).map(BigDecimal::new).toArray(BigDecimal[]::new);
    }
}
