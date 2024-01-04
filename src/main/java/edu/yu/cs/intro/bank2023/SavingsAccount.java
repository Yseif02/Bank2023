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
        if(tx instanceof CashTransaction) {
            CashTransaction cashTx = (CashTransaction) tx;
            if (tx.getType().equals(Transaction.TxType.DEPOSIT)) {
                balance += cashTx.getAmount();
            } else if (tx.getType().equals(Transaction.TxType.WITHDRAW)) {
                balance -= cashTx.getAmount();
            }
            transactions.add(cashTx);
        } else if(tx instanceof CashTransfer) {
            CashTransfer cashTransfer = (CashTransfer) tx;
            SavingsAccount destinationAccount = null;
            boolean foundAccount = false;
            for(Account account:getPatron().getBank().getAllAccounts()){
                if (account.getAccountNumber() == cashTransfer.getDestinationAccountID()){
                    try {
                        destinationAccount = (SavingsAccount) account;
                        foundAccount = true;
                    }catch (Exception e){
                        throw new InvalidTransactionException("Destinaton account is not a savings account", cashTransfer.getType());
                    }
                }
            }
            if(!foundAccount) {
                throw new InvalidTransactionException("Destination account not found", cashTransfer.getType());
            }
            SavingsAccount originAccount = getPatron().getSavingsAccount();
            originAccount.executeTransaction(new CashTransaction(Transaction.TxType.WITHDRAW, cashTransfer.getAmount()));
            destinationAccount.executeTransaction(new CashTransaction(Transaction.TxType.DEPOSIT, cashTransfer.getAmount()));
            this.transactions.add(cashTransfer);
        }else{
            throw new InvalidTransactionException("Wrong type of transaction", tx.getType());
        }
    }

    /**
     * @return the account's balance
     */
    @Override
    public double getValue() {
        return this.balance;
    }


}