package edu.yu.cs.intro.bank2023;

/**
 * represents the stock of a single company that is listed on the StockExchange
 */
public class StockListing {
    private int availableShares;
    private double price;
    private String tickerSymbol;

    /**
     *
     * @param tickerSymbol
     * @param initialPrice
     * @param availableShares
     * @throws IllegalArgumentException if the tickerSymbol is null or empty, if the initial price is <= 0, of if availableShares <= 0
     */
    protected StockListing(String tickerSymbol, double initialPrice, int availableShares){
        if(tickerSymbol == null || tickerSymbol.length() < 1 || initialPrice < 1 || availableShares < 1){
            throw new IllegalArgumentException();
        }
        this.tickerSymbol = tickerSymbol;
        this.price = initialPrice;
        this.availableShares = availableShares;
    }

    public String getTickerSymbol() {
        return this.tickerSymbol;
    }
    public double getPrice() {
      return this.price;
    }
    public int getAvailableShares() {
       return this.availableShares;
    }

    /**
     * set the price for a single share of this stock
     * @param price
     */
    protected void setPrice(double price) {
        this.price = price;
    }
    /**
     * increase the number of shares available
     * @param availableShares
     * @return the total number of shares after adding availableShares
     * @throws IllegalArgumentException if availableShares <= 0
     */
    protected int addAvailableShares(int availableShares) {
        if(availableShares > 0) {
            this.availableShares += availableShares;
            return this.availableShares;
        }
        throw new IllegalArgumentException();
    }
    /**
     * reduce the number of shares available
     * @param quantityToSubtract
     * @return the total number of shares after reducing availableShares
     * @throws IllegalArgumentException if quantityToSubtract > the number of available shares
     */
    protected int reduceAvailableShares(int quantityToSubtract){
        if(quantityToSubtract < this.availableShares) {
            this.availableShares -= quantityToSubtract;
            return this.availableShares;
        }
        throw new IllegalArgumentException();
    }

}