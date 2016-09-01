package uk.co.sebin.stock;

import java.util.HashSet;
import java.util.Set;

/**
 * This class represents the basic implementation of the Global Beverage Corporation Exchange.
 * It holds a set of stocks. This implementation calculates the index using geometric means.
 * 
 * This class only allows one instance (Singleton pattern) of the object.
 * 
 * @author sebin
 *
 */
public class GlobalBeverageCorporationExchange {

    /**
     * This variable holds the list of stocks.
     */
    private Set<Stock> stocks = new HashSet<Stock>();

    /**
     * A singleton variable.
     */
    private static GlobalBeverageCorporationExchange gbce;

    /**
     * Private constructor.
     */
    private GlobalBeverageCorporationExchange() {
    }

    /**
     * 
     * @return an instance of the exchange.
     */
    public static GlobalBeverageCorporationExchange getInstance() {
        if (gbce == null) {
            gbce = new GlobalBeverageCorporationExchange();
        }
        return gbce;
    }

    /**
     * A bulk addition of stocks to the exchange.
     * 
     * @param stocks
     */
    public void addStocks(final Set<Stock> stocks) {
        this.stocks.addAll(stocks);
    }

    /**
     * Add a single stock to the Exchange
     * 
     * @param stock
     */
    public void addStock(final Stock stock) {
        this.stocks.add(stock);
    }

    /**
     * Calculate the index on the moment using the Geometric mean.
     * 
     * @return
     */
    public double calculateIndex() {
        double product = 1.0;
        for (Stock stock : stocks) {
            product = product * stock.calculateStockPrice();
        }
        if (stocks.size() > 0) {
            return Math.pow(product, 1.0 / stocks.size());
        } else {
            return 0;
        }

    }

}
