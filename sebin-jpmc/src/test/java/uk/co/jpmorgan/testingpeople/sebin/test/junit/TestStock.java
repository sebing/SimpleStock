/**
* 
*/
package uk.co.jpmorgan.testingpeople.sebin.test.junit;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import uk.co.jpmorgan.testingpeople.sebin.Stock;
import uk.co.jpmorgan.testingpeople.sebin.StockTypeEnum;
import uk.co.jpmorgan.testingpeople.sebin.Trade;
import uk.co.jpmorgan.testingpeople.sebin.TradeTypeEnum;

/**
 * This test case will be testing the features of Stock class.
 * 
 * @author Sebin
 *
 */
public class TestStock {

    private static final double JOE_TICKER_VALUE = 335.1;
    private static final double ALE_TIKERPRICE = 30.23;
    private static final int ALE_DVD = 23;
    private static final double GIN_TICKER_VALUE = 670.8;
    private static final double GIN_FIXED_DVD = 2d;
    private static final double GIN_PAR = 100;
    private static final int POP_DVD = 8;
    private static final double POP_TIKER_VALUE = 110.41;

    private Stock tea;
    private Stock pop;
    private Stock ale;
    private Stock gin;
    private Stock joe;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        tea = new Stock("TEA", StockTypeEnum.COMMON, 0, null, 100, 300.1);
        pop = new Stock("POP", StockTypeEnum.COMMON, POP_DVD, null, 100, POP_TIKER_VALUE);
        ale = new Stock("ALE", StockTypeEnum.COMMON, ALE_DVD, null, 60, ALE_TIKERPRICE);
        gin = new Stock("GIN", StockTypeEnum.PREFERRED, 8, GIN_FIXED_DVD, GIN_PAR, GIN_TICKER_VALUE);
        joe = new Stock("JOE", StockTypeEnum.COMMON, 13, null, 250, JOE_TICKER_VALUE);

    }

    /**
     * This test will test the common stocktype's Dividend Yield calculations
     */
    @Test
    public void testCalculateDividendYieldCommon() {
        if (StockTypeEnum.COMMON.equals(tea.getStockType())) {
            assertEquals(tea.calculateDividendYield(), 0, 0); // Division by zero tested
        }
        if (StockTypeEnum.COMMON.equals(pop.getStockType())) {
            assertEquals(pop.calculateDividendYield(), POP_DVD / POP_TIKER_VALUE, 0);
        }
    }

    /**
     * This test will test the Preferred stocktype's Dividend Yield calculations
     */
    @Test
    public void testCalculateDividendYieldPreferred() {
        if (StockTypeEnum.PREFERRED.equals(gin.getStockType())) {
            assertEquals(gin.calculateDividendYield(), (GIN_FIXED_DVD * GIN_PAR / GIN_TICKER_VALUE), 0);
        }
    }

    /**
     * This test will test stocktype's P E ratio calculations
     */
    @Test
    public void testCalculateP_ERatio() {
        assertEquals(ale.calculateP_ERatio(), (ALE_TIKERPRICE / ALE_DVD), 0);
    }

    /**
     * This test will test the stocktype's Stock Price calculations
     * 
     * It adds three different trades.
     * 
     */
    @Test
    public void testCalculateStockPrice() {
        int quanity1 = 10;
        int quanity3 = 30;
        int quanity2 = 20;
        double increment1 = 0.1;
        double increment2 = 0.2;
        double increment3 = 0.3;

        joe.addTrade(new Trade(Calendar.getInstance(), quanity1, TradeTypeEnum.BUY, joe.getTickerPrice() + increment1));
        joe.addTrade(new Trade(Calendar.getInstance(), quanity2, TradeTypeEnum.BUY, joe.getTickerPrice() + increment2));
        joe.addTrade(new Trade(Calendar.getInstance(), quanity3, TradeTypeEnum.BUY, joe.getTickerPrice() + increment3));
        assertEquals(joe.calculateStockPrice(), (((JOE_TICKER_VALUE + increment1) * quanity1)
            + ((JOE_TICKER_VALUE + increment2) * quanity2) + ((JOE_TICKER_VALUE + increment3) * quanity3))
            / (quanity1 + quanity2 + quanity3), 0);
    }

}
