package edu.yu.cs.intro.bank2023;
import java.util.*;

public class StockExchange {
    private Map<String, StockListing> stocks;
    protected StockExchange(){
        stocks = new HashMap<>();
    }

    /**
     *
     * @param tickerSymbol symbol of the new stock to be created, e.g. "IBM", "GOOG", etc.
     * @param initialPrice price of a single share of the stock
     * @param availableShares how many shares of the stock are available initially
     * @throws IllegalArgumentException if there's already a listing with that tickerSymbol
     */
    public void createNewListing(String tickerSymbol, double initialPrice, int availableShares){
        if(stocks.containsKey(tickerSymbol)) {
            throw new IllegalArgumentException("Stock exchange already contains this listing");
        }
        StockListing stockListing = new StockListing(tickerSymbol, initialPrice, availableShares);
        stocks.put(tickerSymbol, stockListing);
    }

    /**
     * @param tickerSymbol the stocks tickerSymbol
     * @return the StockListing object for the given tickerSymbol, or null if there is none
     */
    public StockListing getStockListing(String tickerSymbol){
       if(stocks.containsKey(tickerSymbol)){
           return stocks.get(tickerSymbol);
       }
       return null;
    }

    /**
     * @return an unmodifiable list of all the StockListings currently found on this exchange
     * @see java.util.Collections#unmodifiableList(List)
     */
    public List<StockListing> getAllCurrentListings(){
        return List.copyOf(stocks.values());
    }
}