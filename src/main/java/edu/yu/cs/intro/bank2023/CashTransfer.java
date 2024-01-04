package edu.yu.cs.intro.bank2023;

public class CashTransfer implements Transaction{
    private final TxType type;
    private final double amount;
    private final int destinationAccountID;
    private final long nanoTimeStamp;

    public CashTransfer(TxType type, double amount, int destinationAccountID) throws InvalidTransactionException{
        if(type.equals(TxType.CASH_TRANSFER) && amount > 0 && destinationAccountID > 0){
            this.type = type;
            this.amount = amount;
            this.destinationAccountID = destinationAccountID;
            this.nanoTimeStamp = System.nanoTime();
        }else{
            throw new InvalidTransactionException("Message", type);
        }
    }

    public double getAmount() {
        return amount;
    }

    public int getDestinationAccountID() {
        return destinationAccountID;
    }

    @Override
    public TxType getType() {
        return type;
    }

    @Override
    public long getNanoTimestamp() {
        return nanoTimeStamp;
    }
}
