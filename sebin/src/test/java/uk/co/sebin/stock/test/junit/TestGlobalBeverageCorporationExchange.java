/**
 * 
 */
package uk.co.sebin.stock.test.junit;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import uk.co.sebin.stock.GlobalBeverageCorporationExchange;
import uk.co.sebin.stock.Stock;
import uk.co.sebin.stock.StockTypeEnum;
import uk.co.sebin.stock.Trade;
import uk.co.sebin.stock.TradeTypeEnum;

/**
 * This class tests the index calculation of GBCE
 * 
 * @author sebin
 *
 */
public class TestGlobalBeverageCorporationExchange {

    private static final double TEA_TIKERPRICE = 300.1;
    private static final double JOE_TICKERPRICE = 335.1;
    private static final double ALE_TIKERPRICE = 30.23;
    private static final double GIN_TICKERPRICE = 670.8;
    private static final double POP_TIKERPRICE = 110.41;
    private static final int ALE_DVD = 23;
    private static final double GIN_FIXED_DVD = 2d;
    private static final double GIN_PAR = 100;
    private static final int POP_DVD = 8;

    private GlobalBeverageCorporationExchange gbce;
    private double expectedIndexValue = 1;

    /**
     * this method will prepare 5 stocks with 3 trades and calculate the expected value.
     * 
     * In the future, if needed, with more test cases we can change this method as setup.
     * 
     */
    private void prepare() {
        gbce = GlobalBeverageCorporationExchange.getInstance();

        Stock tea = new Stock("TEA", StockTypeEnum.COMMON, 0, null, 100, TEA_TIKERPRICE);
        Stock pop = new Stock("POP", StockTypeEnum.COMMON, POP_DVD, null, 100, POP_TIKERPRICE);
        Stock ale = new Stock("ALE", StockTypeEnum.COMMON, ALE_DVD, null, 60, ALE_TIKERPRICE);
        Stock gin = new Stock("GIN", StockTypeEnum.PREFERRED, 8, GIN_FIXED_DVD, GIN_PAR, GIN_TICKERPRICE);
        Stock joe = new Stock("JOE", StockTypeEnum.COMMON, 13, null, 250, JOE_TICKERPRICE);

        int quanity1 = 10;
        int quanity3 = 30;
        int quanity2 = 20;
        double increment1 = 0.1;
        double increment2 = 0.2;
        double increment3 = 0.3;

        prepareStockWithTrade(tea, quanity1, quanity3, quanity2, increment1, increment2, increment3, TEA_TIKERPRICE);
        prepareStockWithTrade(pop, quanity1, quanity3, quanity2, increment1, increment2, increment3, POP_TIKERPRICE);
        prepareStockWithTrade(ale, quanity1, quanity3, quanity2, increment1, increment2, increment3, ALE_TIKERPRICE);
        prepareStockWithTrade(gin, quanity1, quanity3, quanity2, increment1, increment2, increment3, GIN_TICKERPRICE);
        prepareStockWithTrade(joe, quanity1, quanity3, quanity2, increment1, increment2, increment3, JOE_TICKERPRICE);

        Set<Stock> stocks = new HashSet<Stock>();
        stocks.add(tea);
        stocks.add(pop);
        stocks.add(ale);
        stocks.add(gin);
        stocks.add(joe);

        expectedIndexValue *= tea.calculateStockPrice();
        expectedIndexValue *= pop.calculateStockPrice();
        expectedIndexValue *= ale.calculateStockPrice();
        expectedIndexValue *= gin.calculateStockPrice();
        expectedIndexValue *= joe.calculateStockPrice();

        expectedIndexValue = Math.pow(expectedIndexValue, 1.0 / stocks.size());

        gbce.addStocks(stocks);
    }

    /**
     * a utility method to update the stock with trade
     * 
     * @param stock
     * @param quanity1
     * @param quanity3
     * @param quanity2
     * @param increment1
     * @param increment2
     * @param increment3
     * @param baseValue
     */
    private void prepareStockWithTrade(Stock stock, final int quanity1, final int quanity3, final int quanity2,
                                       final double increment1, final double increment2, final double increment3,
                                       final double baseValue) {
        stock.addTrade(createTrade(stock, quanity1, increment1));
        stock.addTrade(createTrade(stock, quanity2, increment2));
        stock.addTrade(createTrade(stock, quanity3, increment3));
        assertEquals(stock.calculateStockPrice(), (((baseValue + increment1) * quanity1)
            + ((baseValue + increment2) * quanity2) + ((baseValue + increment3) * quanity3))
            / (quanity1 + quanity2 + quanity3), 0);
    }

    /**
     * This method will create a trade with the stock ticker price.
     * 
     * @param trade
     * @param quanity
     * @param increment
     * @return
     */
    private Trade createTrade(final Stock trade, final int quanity, final double increment) {
        return new Trade(Calendar.getInstance(), quanity, TradeTypeEnum.BUY, trade.getTickerPrice() + increment);
    }

    /**
     * This is the test case for testing the index value
     */
    @Test
    public void testCalculateIndex() {
        prepare();
        assertEquals(gbce.calculateIndex(), expectedIndexValue, 0);
    }

}
