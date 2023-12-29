package edu.yu.cs.intro.bank2023;

/**
 * A CashTransaction is immutable. Value of nanoTimeStamp must be set at time of construction to the return value of System.nanoTime().
 */
public class CashTransaction implements Transaction{
    private final double amount;
    private final TxType type;
    private final long nanoTimeStamp;

    /**
     *
     * @param type
     * @param amount
     * @throws InvalidTransactionException thrown if type is neither DEPOSIT nor WITHDRAW, or if amount <= 0
     */
    public CashTransaction(TxType type, double amount) throws InvalidTransactionException{
        this.nanoTimeStamp = System.nanoTime();
        if((type.equals(TxType.WITHDRAW) || type.equals(TxType.DEPOSIT)) && amount > 0){
            this.type = type;
            this.amount = amount;
        }else{
            throw new InvalidTransactionException("Message", type);
        }
    }

    public double getAmount(){
        return this.amount;
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