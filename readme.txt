This are the notes for the test "Super Simple Stock" impemented in Java.

The java package under (src\main\java): uk.co.sebin.stock contains the implementations for the exchange and the stock.
-	Stock.java : the stock behavior implementations.
-	Trade.java : This class represents a trade.
-	GlobalBeverageCorporationExchange : this class represents the Exchange(which holds the stocks and calculates the index)



Testing:
The test files are under this location(src/test/java)
1)	uk.co.sebin.stock.test.ManualTest.java : 
	- This test creates the stocks with random value trades. Then it calculates both the stock price and the exchange index.
	- This class is written alongside with the implementation, to test manually.

2)	Junit tests:
- uk.co.sebin.stock.test.junit.TestStock.java : This file tests the behavior of Stocks
- uk.co.sebin.stock.test.junit.TestGlobalBeverageCorporationExchange.java - This file tests the behavior of GBCE.
