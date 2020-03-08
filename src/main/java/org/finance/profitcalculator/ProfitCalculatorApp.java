package org.finance.profitcalculator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;
import org.finance.profitcalculator.beans.CalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProfitCalculatorApp {

    private static final Logger LOG = LoggerFactory.getLogger(ProfitCalculatorApp.class);
    private static Injector injector = Guice.createInjector(new DependencyInjector());

    /**
     * This initialise the app with passed in program args. Convenience main class to test the profit-calculator algorithm.
     *
     * @param args array of stock prices
     */
    public static void main(String[] args) {
        CalculatorService calculatorService = injector.getInstance(CalculatorService.class);
        //int[] stockPrices = new int[] {10, 7, 5, 8, 11, 9};

        //convert string input array into BigDecimal array. This is to prepare the input to send into processing function. This isnt part the algorithm itself,
        // thus not count towards time complexity.
        BigDecimal[] prices = Stream.of(args)
                .map(String::trim)
                .filter(Objects::nonNull)
                .map(BigDecimal::new)
                .toArray(BigDecimal[]::new);

        LOG.info(String.format("calculated max profit: %s", calculatorService.getMaxProfit(prices).toPlainString()));
    }
}
