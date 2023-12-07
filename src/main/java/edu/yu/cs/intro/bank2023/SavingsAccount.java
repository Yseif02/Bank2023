package edu.yu.cs.intro.bank2023;

public class SavingsAccount extends Account implements Transaction{
   
    protected SavingsAccount(int accountNumber, Patron patron) {
        super(accountNumber, patron);

    }

    /**
     * for a DEPOSIT transaction: increase the balance by transaction amount
     * for a WITHDRAW transaction: decrease the balance by transaction amount
     * add the transaction to the transaction history of this account
     * @param tx
     * @return
     * @throws IllegalArgumentException thrown if tx is not a CashTransaction
     */
    @Override
    public void executeTransaction(Transaction tx) {

    }

    /**
     * @return the account's balance
     */
    @Override
    public double getValue() {

    }

    /**
     * @return which type of transaction is this?
     */
    @Override
    public TxType getType() {
        return null;
    }

    /**
     * @return timestamp of transaction. Value of nanoTimeStamp must be set at time of construction to the return value of System.nanoTime()
     * @see System#nanoTime()
     */
    @Override
    public long getNanoTimestamp() {
        return 0;
    }
}