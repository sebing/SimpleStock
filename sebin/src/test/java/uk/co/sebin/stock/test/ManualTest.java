package uk.co.sebin.stock.test;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import uk.co.sebin.stock.GlobalBeverageCorporationExchange;
import uk.co.sebin.stock.Stock;
import uk.co.sebin.stock.StockTypeEnum;
import uk.co.sebin.stock.Trade;
import uk.co.sebin.stock.TradeTypeEnum;


/**
 * This is a test class to demonstrate the super simple stocks application.
 * 
 * @author sebin
 *
 */
public class ManualTest {

	static final int MAX_NO_OF_TRADES = 100;

	public static void main( String[] args ) {
		GlobalBeverageCorporationExchange gbce = GlobalBeverageCorporationExchange.getInstance();
		
		Set<Stock> stocks = getSampleStocks();
    
    gbce.addStocks( stocks );
    
		Random random = new Random();

    for ( Stock stock : stocks ) {
			int ranTrades = MAX_NO_OF_TRADES - random.nextInt( MAX_NO_OF_TRADES - 10 ); // minimum 10 trades :) 
			for ( int i = 0 ; i < ranTrades ; i++ ) { // create random value trades
				Trade trade = getRandomTrade( random, stock );
				stock.addTrade( trade ); // record a trade to stock
			}
    }

		for ( Stock stock : stocks ) { // Iterate over the the list of stocks and print the details.
			printStockDetails( stock );
		}

		System.out.println( "GBCE index: " + gbce.calculateIndex() ); // print the GBCE exchange index.
		

	}

	/**
	 * A utility method for printing the details of a Stock.
	 * 
	 * @param stock
	 */
	private static void printStockDetails( final Stock stock ) {
		System.out.println( stock.getStockSymbol() + "\t"
		    + stock.calculateDividendYield() + "\t"
		    + stock.calculateP_ERatio() + "\t"
		    + stock.calculateStockPrice() );
		System.out.println( "----------------------------" );
	}

	/**
	 * create a new trade with random price, quantity, type and timestamp.
	 * 
	 * @param random
	 * @param stock
	 * @return a trade with random value
	 */
	private static final Trade getRandomTrade( final Random random, final Stock stock ) {
		
		Trade trade = new Trade( getRandomTimestamp( random ), // timestamp
		    random.nextInt( 100 ) + 1, // minimum quanity is 1
		    random.nextBoolean() ? TradeTypeEnum.BUY : TradeTypeEnum.SELL, // trade type either buy or sell
		    getPrice( random, stock ) // new ticker price
		    );

		return trade;
	}

	/**
	 * Find a random price from the current stock tick price
	 * 
	 * @param random
	 * @param stock
	 * @return new price
	 */
	private static final double getPrice( final Random random, final Stock stock ) {
		if ( random.nextBoolean() ) {
			return stock.getTickerPrice() + random.nextDouble(); // Get a random incremented	
		}
		else {
			return stock.getTickerPrice() - random.nextDouble(); // Get a random decremented price
		}

	}

	/**
	 * generate a random timestamp within 30 min
	 * 
	 * @param random
	 * @return
	 */
	private static final Calendar getRandomTimestamp( final Random random ) {
		Calendar cal = Calendar.getInstance();
		cal.add( Calendar.MINUTE, -random.nextInt( 30 ) ); // Get a random time in last 30min
		return cal;
	}

	/**
	 * create a sample stocks(get the names from the example.
	 * 
	 * @return
	 */
	private static final Set<Stock> getSampleStocks() {
		Stock tea = new Stock( "TEA", StockTypeEnum.COMMON, 0, null, 100, 300.1 );
		Stock pop = new Stock( "POP", StockTypeEnum.COMMON, 8, null, 100, 110.41 );
		Stock ale = new Stock( "ALE", StockTypeEnum.COMMON, 23, null, 60, 30.23 );
		Stock gin = new Stock( "GIN", StockTypeEnum.PREFERRED, 8, 2d, 100, 670.8 );
		Stock joe = new Stock( "JOE", StockTypeEnum.COMMON, 13, null, 250, 335.1 );

		Set<Stock> stocks = new HashSet<Stock>();
		stocks.add( tea );
		stocks.add( pop );
		stocks.add( ale );
		stocks.add( gin );
		stocks.add( joe );
		return stocks;
	}


}
