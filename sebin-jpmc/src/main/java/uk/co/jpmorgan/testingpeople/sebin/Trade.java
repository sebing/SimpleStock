package uk.co.jpmorgan.testingpeople.sebin;

import java.util.Calendar;

/**
 * This class represents a basic trade.
 * 
 * @author Sebin
 *
 */
public class Trade {

    /**
     * A public constructor to create the object with values;
     * @param timestamp
     * @param quantity
     * @param tradeType
     * @param price
     */
    public Trade(final Calendar timestamp, final int quantity, final TradeTypeEnum tradeType, final double price) {
        super();
        this.timestamp = timestamp;
        this.quanity = quantity;
        this.tradeType = tradeType;
        this.price = price;
    }

    /**
     * A default constructor.
     */
    public Trade() {
        super();
    }

    /**
     * The timestamp of the trade.
     */
    private Calendar timestamp;

    /**
     * The quantity of the trade
     */
    private int quanity;

    /**
     * Trade type either buy or sell
     */
    private TradeTypeEnum tradeType;

    /**
     * The price of this trade
     */
    private double price;

    /**
     * @return the timestamp
     */
    public Calendar getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(final Calendar timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the quantity
     */
    public int getQuanity() {
        return quanity;
    }

    /**
     * @param quanity the quantity to set
     */
    public void setQuanity(final int quanity) {
        this.quanity = quanity;
    }

    /**
     * @return the tradeType
     */
    public TradeTypeEnum getTradeType() {
        return tradeType;
    }

    /**
     * @param tradeType the tradeType to set
     */
    public void setTradeType(final TradeTypeEnum tradeType) {
        this.tradeType = tradeType;
    }

    /**
     * @return the price
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(final double price) {
        this.price = price;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Trade [" + "timestamp=" + timestamp + ", quanity=" + quanity + ", tradeType=" + tradeType + ", price="
            + price + "]";
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
        long temp;
        temp = Double.doubleToLongBits(price);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + quanity;
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((tradeType == null) ? 0 : tradeType.hashCode());
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
        Trade other = (Trade) obj;
        if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
            return false;
        if (quanity != other.quanity)
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (tradeType != other.tradeType)
            return false;
        return true;
    }

}
