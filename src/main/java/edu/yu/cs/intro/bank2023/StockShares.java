package edu.yu.cs.intro.bank2023;

/**
 * represents the quantity of shares a single Patron owns of single stock/listing
 */
public class StockShares{
    private StockListing stock;
    private int quantity;
    private double value;

    /**
     * @param listing the stock listing this instance is tracking the Patron's shares of
     * @throws IllegalArgumentException if listing is null
     */
    protected StockShares(StockListing listing){
        if(listing == null){
            throw new IllegalArgumentException();
        }
        this.stock = listing;
        this.quantity = 0;
        this.value = 0;
    }
    private void setValue(){
        this.value = stock.getPrice() * quantity;
    }
;
    public int getQuantity() {
        return this.quantity;
    }

    protected void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    protected void executeTransfer(int quantity){
        this.quantity -= quantity;
    }

    public StockListing getListing() {
        return this.stock;
    }

    public double getValue(){
        setValue();
        return this.value;
    }

}