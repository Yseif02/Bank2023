package edu.yu.cs.intro.bank2023;

public class SavingsAccount extends Account{
    private double balance;

    protected SavingsAccount(int accountNumber, Patron patron) {
        super(accountNumber, patron);
    }

    /**
     * for a DEPOSIT transaction: increase the balance by transaction amount
     * for a WITHDRAW transaction: decrease the balance by transaction amount
     * add the transaction to the transaction history of this account
     * @param tx
     * @return
     * @throws InvalidTransactionException thrown if tx is not a CashTransaction
     */
    @Override
    public void executeTransaction(Transaction tx) throws InsufficientAssetsException,InvalidTransactionException {
        CashTransaction cashTx = (CashTransaction) tx;
        if(tx.getType().equals(Transaction.TxType.DEPOSIT)){
            balance += cashTx.getAmount();
        }else if(tx.getType().equals(Transaction.TxType.WITHDRAW)){
            balance -= cashTx.getAmount();
        }else{
            throw new InvalidTransactionException("Wrong type of transaction", tx.getType());
        }
        transactions.add(cashTx);
    }

    /**
     * @return the account's balance
     */
    @Override
    public double getValue() {
        return this.balance;
    }
}