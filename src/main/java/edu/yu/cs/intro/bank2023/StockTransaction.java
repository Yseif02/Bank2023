package edu.yu.cs.intro.bank2023;
/**
 * A StockTransaction is immutable. Value of nanoTimeStamp must be set at time of construction to the return value of System.nanoTime().
 */
public class StockTransaction implements Transaction{
    private final StockListing listing;
    private final TxType type;
    private final int quantity;
    private final long nanoTimeStamp;

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
        this.nanoTimeStamp = System.nanoTime();
    }


    public StockListing getStock(){
        return this.listing;
    }
    public int getQuantity(){
      return this.quantity;
    }
    @Override
    public TxType getType() {
        return this.type;
    }
    @Override
    public long getNanoTimestamp() {
       return this.nanoTimeStamp;
    }
}
