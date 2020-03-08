package org.finance.profitcalculator;

import com.google.inject.Guice;
import com.google.inject.Injector;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.stream.Stream;
import org.finance.profitcalculator.beans.CalculatorService;

public class ProfitCalculatorApp {

    private static Injector injector = Guice.createInjector(new DependencyInjector());

    /**
     * This initialise the app with passed in program args.
     *
     * @param args array of stock prices
     */
    public static void main(String[] args) {
        CalculatorService calculatorService = injector.getInstance(CalculatorService.class);
        //int[] stockPrices = new int[] {10, 7, 5, 8, 11, 9};

        //convert string input array into BigBecimal array
        BigDecimal[] prices = Stream.of(args)
                .map(String::trim)
                .filter(Objects::nonNull)
                .map(BigDecimal::new)
                .toArray(BigDecimal[]::new);

        System.out.println(String.format("calculated max profit: %s", calculatorService.getMaxProfit(prices).toPlainString()));
    }
}
