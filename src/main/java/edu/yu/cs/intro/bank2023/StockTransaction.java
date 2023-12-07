package edu.yu.cs.intro.bank2023;
/**
 * A StockTransaction is immutable. Value of nanoTimeStamp must be set at time of construction to the return value of System.nanoTime().
 */
public class StockTransaction implements Transaction{
    private  StockListing listing;
    private TxType type;
    private int quantity;

    /**
     *
     * @param listing
     * @param type
     * @param quantity
     * @throws InvalidTransactionException thrown if TxType is neither BUY nor SELL, or if quantity <= 0, or if listing == null
     */
    public StockTransaction(StockListing listing, TxType type, int quantity) throws InvalidTransactionException{
        if((type.equals(TxType.BUY) || type.equals(TxType.SELL)) && quantity > 0 && listing != null){
            this.type = type;
            this.listing = listing;
            this.quantity = quantity;
        }else{
            throw new InvalidTransactionException("Message", type);
        }
    }
    public StockListing getStock(){
        return listing;
    }
    public int getQuantity(){
      return quantity;
    }
    @Override
    public TxType getType() {
        return type;
    }
    @Override
    public long getNanoTimestamp() {
       return System.nanoTime();
    }
}
