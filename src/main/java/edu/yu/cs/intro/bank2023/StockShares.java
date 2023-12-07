package edu.yu.cs.intro.bank2023;

/**
 * represents the quantity of shares a single Patron owns of single stock/listing
 */
public class StockShares{
    private StockListing listing;

    /**
     * @param listing the stock listing this instance is tracking the Patron's shares of
     * @throws IllegalArgumentException if listing is null
     */
    protected StockShares(StockListing listing){
        if(listing == null){
            throw new IllegalArgumentException();
        }
        this.listing = listing;
    }
;
    public int getQuantity() {
  
    }

    protected void setQuantity(int quantity) {
  
    }

    public StockListing getListing() {
        
    }
}