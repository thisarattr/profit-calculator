package org.finance.profitcalculator.beans;

import java.math.BigDecimal;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The {@code CalculatorService} class represents all processing related to profit calculator.
 */
public class CalculatorService {

    private static final Logger LOG = LoggerFactory.getLogger(CalculatorService.class);

    /**
     * Returns the maximum profit based on the stock prices passed in.
     * Time complexity O(n).
     *
     * @param stockPrices array of stock prices for every minute in the order of occurrence
     * @return maximum profit calculated
     */
    public BigDecimal getMaxProfit(BigDecimal[] stockPrices) {
        if (stockPrices == null || stockPrices.length < 2) {
            throw new IllegalArgumentException("must have atleast two stock prices");
        }

        LOG.debug(String.format("input values: %s", Arrays.toString(stockPrices)));
        BigDecimal currentMaxProfit = stockPrices[1].subtract(stockPrices[0]);
        BigDecimal currentCheapestStockPrice = stockPrices[0];

        for (int i = 1; i < stockPrices.length; i++) {
            //check and set, if a max profit than current, can be achieved using the i th stock price
            if ((stockPrices[i].subtract(currentCheapestStockPrice)).compareTo(currentMaxProfit) == 1) {
                currentMaxProfit = stockPrices[i].subtract(currentCheapestStockPrice);
                LOG.debug(String.format("currentMaxProfit updated to: %s", currentMaxProfit.toPlainString()));
            }

            //check and set, if the i th value is the cheapest
            if (stockPrices[i].compareTo(currentCheapestStockPrice) == -1) {
                currentCheapestStockPrice = stockPrices[i];
                LOG.debug(String.format("currentCheapestStockPrice updated to: %s", currentMaxProfit.toPlainString()));
            }
        }
        LOG.debug(String.format("max profit found: %s", currentMaxProfit.toPlainString()));

        return currentMaxProfit;
    }

}
