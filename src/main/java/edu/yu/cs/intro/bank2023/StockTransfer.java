package edu.yu.cs.intro.bank2023;

public class StockTransfer implements Transaction{
    private final StockListing listing;
    private final TxType type;
    private final int quantity;
    private final long nanoTimeStamp;
    private final int destinationAccountID;

    public StockTransfer(StockListing listing, TxType type, int quantity, int destinationAccountID) throws InvalidTransactionException {
        if(type.equals(TxType.STOCK_TRANSFER) && quantity > 0 && listing != null && destinationAccountID > 0){
            this.type = type;
            this.listing = listing;
            this.quantity = quantity;
            this.destinationAccountID = destinationAccountID;
        }else{
            throw new InvalidTransactionException("Message", type);
        }
        this.nanoTimeStamp = System.nanoTime();

    }

    public int getDestinationAccountID() {
        return destinationAccountID;
    }

    public int getQuantity() {
        return quantity;
    }

    public StockListing getListing() {
        return listing;
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
