package uk.co.jpmorgan.testingpeople.sebin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This class represents the Stock implementation.
 * 
 * @author sebin
 *
 */
public class Stock {

    /**
     * the stock symbol
     */
    private String stockSymbol;

    /**
     * the stock type
     */
    private StockTypeEnum StockType;

    /**
     * the stock last dividend.
     */
    private double lastDividend;

    /**
     * the stock fixed dividend.
     */
    private double fixedDividend;

    /**
     * the stock par value.
     */
    private double parValue;

    /**
     * the stock ticker price.
     * This is the price of the stock in that moment.
     * An alternate way to store is the difference from the stock price.
     * In this implementation the difference is calculated outside this class.
     */
    private double tickerPrice;

    /**
     * This variable will be recording the trades happening for the stock.
     */
    private List<Trade> trades = new ArrayList<Trade>();

    /**
     * A default constructor.
     */
    public Stock() {
        super();
    }

    /**
     * A public constructor to create the  object with values.
     * 
     * @param stockSymbol
     * @param stockType
     * @param lastDividend
     * @param fixedDividend
     * @param parValue
     */
    public Stock(final String stockSymbol, final StockTypeEnum stockType, final double lastDividend,
                 final Double fixedDividend, final double parValue, final double tikerprice) {
        super();
        this.stockSymbol = stockSymbol;
        StockType = stockType;
        this.lastDividend = lastDividend;
        if (fixedDividend != null) { // From the example, I assume the fixedDividend can be blank. But at this moment, I
                                     // am not storing them.
            this.fixedDividend = fixedDividend;
        }
        this.parValue = parValue;
        this.tickerPrice = tikerprice;
    }

    /**
     * @return the stockSymbol
     */
    public String getStockSymbol() {
        return stockSymbol;
    }

    /**
     * @param stockSymbol the stockSymbol to set
     */
    public void setStockSymbol(final String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    /**
     * @return the stockType
     */
    public StockTypeEnum getStockType() {
        return StockType;
    }

    /**
     * @param stockType the stockType to set
     */
    public void setStockType(final StockTypeEnum stockType) {
        StockType = stockType;
    }

    /**
     * @return the lastDividend
     */
    public double getLastDividend() {
        return lastDividend;
    }

    /**
     * @param lastDividend the lastDividend to set
     */
    public void setLastDividend(final double lastDividend) {
        this.lastDividend = lastDividend;
    }

    /**
     * @return the fixedDividend
     */
    public double getFixedDividend() {
        return fixedDividend;
    }

    /**
     * @param fixedDividend the fixedDividend to set
     */
    public void setFixedDividend(final double fixedDividend) {
        this.fixedDividend = fixedDividend;
    }

    /**
     * @return the parValue
     */
    public double getParValue() {
        return parValue;
    }

    /**
     * @param parValue the parValue to set
     */
    public void setParValue(final double parValue) {
        this.parValue = parValue;
    }

    /**
     * @return the tickerPrice
     */
    public double getTickerPrice() {
        return tickerPrice;
    }

    /**
     * @param tickerPrice the tickerPrice to set
     */
    public void setTickerPrice(final double tickerPrice) {
        this.tickerPrice = tickerPrice;
    }

    /**
     * This method will be calculating the Dividend Yield for this stock
     * 
     * @return
     */
    public double calculateDividendYield() {
        if (StockTypeEnum.COMMON.equals(StockType)) { // Different formula for common and preffered type of stocks
            return lastDividend / tickerPrice;
        } else if (StockTypeEnum.PREFERRED.equals(StockType)) {
            return fixedDividend * parValue / tickerPrice;
        }
        return 0; // Not possible in the current scenario.
    }

    /**
     * This method will be calculating the P E Ratio
     * 
     * @return
     */
    public double calculateP_ERatio() {
        if (lastDividend > 0) {
            return tickerPrice / lastDividend; // It is not clear for me from the problem whether I have to add the
                                               // fixed or the last
                                               // dividend for the preferred.
        } else {
            return 0;
        }
    }

    /**
     * This method will record a new trade.
     * 
     * @param trade
     */
    public void addTrade(final Trade trade) {
        trades.add(trade);
    }

    /**
     * This method will calculate the Stock price.
     * 
     * @return
     */
    public double calculateStockPrice() {
        double totalTradePrice = 0;
        int totalQuantity = 0;
        Calendar time = Calendar.getInstance(); // current time.

        time.add(Calendar.MINUTE, -15); // 15 min ago
        for (Trade trade : trades) {
            if (time.before(trade.getTimestamp())) { // within last 15min
                totalTradePrice += (trade.getPrice() * trade.getQuanity());
                totalQuantity += trade.getQuanity();
            }
        }

        if (totalQuantity > 0) {
            return totalTradePrice / totalQuantity;
        } else {
            return 0;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Stock [stockSymbol=" + stockSymbol + ", StockType=" + StockType + ", lastDividend=" + lastDividend
            + ", fixedDividend=" + fixedDividend + ", parValue=" + parValue + ", tickerPrice=" + tickerPrice
            + ", trades=" + trades + "]";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((StockType == null) ? 0 : StockType.hashCode());
        long temp;
        temp = Double.doubleToLongBits(fixedDividend);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lastDividend);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(parValue);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((stockSymbol == null) ? 0 : stockSymbol.hashCode());
        temp = Double.doubleToLongBits(tickerPrice);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((trades == null) ? 0 : trades.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Stock other = (Stock) obj;
        if (StockType != other.StockType)
            return false;
        if (Double.doubleToLongBits(fixedDividend) != Double.doubleToLongBits(other.fixedDividend))
            return false;
        if (Double.doubleToLongBits(lastDividend) != Double.doubleToLongBits(other.lastDividend))
            return false;
        if (Double.doubleToLongBits(parValue) != Double.doubleToLongBits(other.parValue))
            return false;
        if (stockSymbol == null) {
            if (other.stockSymbol != null)
                return false;
        } else if (!stockSymbol.equals(other.stockSymbol))
            return false;
        if (Double.doubleToLongBits(tickerPrice) != Double.doubleToLongBits(other.tickerPrice))
            return false;
        if (trades == null) {
            if (other.trades != null)
                return false;
        } else if (!trades.equals(other.trades))
            return false;
        return true;
    }

}
