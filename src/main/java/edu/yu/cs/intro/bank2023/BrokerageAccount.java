package edu.yu.cs.intro.bank2023;

import java.util.*;

/**
 * Models a brokerage account, i.e. an account used to buy, sell, and own stocks
 */
public class BrokerageAccount extends Account{
    private Map<String, StockShares> sharesMap;



    /**
     * This will be called by the Bank class.
     * @param accountNumber the account number assigned by the bank to this new account
     * @param patron the Patron who owns this account
     * @see Bank#openNewBrokerageAccount(Patron)
     */
    protected BrokerageAccount(int accountNumber, Patron patron) {
        super(accountNumber, patron);
        sharesMap = new HashMap<>();
    }

    /**
     * @return an unmodifiable list of all the shares of stock currently owned by this account
     * @see java.util.Collections#unmodifiableList(List)
     */
    public List<StockShares> getListOfShares(){
        List<StockShares> stockShares = new ArrayList<>(sharesMap.values());
        return Collections.unmodifiableList(stockShares);
    }

    /**
     * If the transaction is not an instanceof StockTransaction, throw an InvalidTransactionException.
     *
     * If tx.getType() is BUY, do the following:
     *         If there aren't enough shares of the stock available for purchase, throw an InvalidTransactionException.
     *         The total amount of cash needed for the tx  = tx.getQuantity() * tx.getStock().getPrice(). If the patron doesn't have enough cash in his SavingsAccount for this transaction, throw InsufficientAssetsException.
     *         If he does have enough cash, do the following:
     *         1) reduce available share of StockListing by tx.getQuantity()
     *         2) reduce cash in patron's savings account by tx.getQuantity() * StockListing.getPrice()
     *         3) create a new StockShare for this stock with the quantity set to tx.getQuantity() and listing set to tx.getStock() (or increase StockShare quantity, if there already is a StockShare instance in this account, by tx.getQuantity())
     *         4) add this to the set of transactions recorded in this account
     *
     * If tx.getType() is SELL, do the following:
     *          //If this account doesn't have the specified number of shares in the given stock, throw an InsufficientAssetsException.
     *          //Reduce the patron's shares in the stock by the tx.getQuantity()
     *          //The revenue from the sale = the current price per share of the stock * number of shares to be sold. Use a DEPOSIT transaction to add the revenue to the Patron's savings account.
     *
     * @param tx the transaction to execute on this account
     * @see StockTransaction
     */
    @Override
    public void executeTransaction(Transaction tx) throws InsufficientAssetsException,InvalidTransactionException {
        if(!(tx instanceof StockTransaction)){
            throw new InvalidTransactionException("Wrong type of transaction", tx.getType());
        }
        StockTransaction stockTx = (StockTransaction) tx;
        if(tx.getType().equals(Transaction.TxType.BUY)){
            double totalCashNeeded = stockTx.getQuantity() * stockTx.getStock().getPrice();
            if(stockTx.getStock().getAvailableShares() < stockTx.getQuantity()){
                throw new InvalidTransactionException("Not enough available shares for purchase", tx.getType());
            }
            if(totalCashNeeded > getPatron().getSavingsAccount().getValue()){
                throw new InsufficientAssetsException(tx, getPatron());
            }
            stockTx.getStock().reduceAvailableShares(stockTx.getQuantity());
            Transaction withdrawAmount = new CashTransaction(Transaction.TxType.WITHDRAW, totalCashNeeded);
            getPatron().getSavingsAccount().executeTransaction(withdrawAmount);
            if(sharesMap.containsKey(stockTx.getStock().getTickerSymbol())){
                for (StockShares stockShare:getListOfShares()) {
                    if(stockShare.getListing().getTickerSymbol().equals(stockTx.getStock().getTickerSymbol())){
                        //stockShare.setQuantity(stockTx.getQuantity());
                        stockShare = sharesMap.get(stockTx.getStock().getTickerSymbol());
                        stockShare.setQuantity(stockShare.getQuantity() + stockTx.getQuantity());
                    }
                }
            }else{
                StockShares stockShares = new StockShares(stockTx.getStock());
                stockShares.setQuantity(stockTx.getQuantity());
                sharesMap.put(stockShares.getListing().getTickerSymbol(), stockShares);
            }
            this.transactions.add(stockTx);
        }else {
            if(!(sharesMap.containsKey(stockTx.getStock().getTickerSymbol()))){
                throw new InsufficientAssetsException(tx, getPatron());
            }
            for (StockShares stockShare:getListOfShares()) {
                if(stockShare.getListing().getTickerSymbol().equals(stockTx.getStock().getTickerSymbol())){
                    if(stockTx.getQuantity() > stockShare.getListing().getAvailableShares()){
                        throw new InsufficientAssetsException(tx, getPatron());
                    }
                    stockShare.getListing().reduceAvailableShares(stockTx.getQuantity());
                    double revenueFromSale = stockTx.getStock().getPrice() * stockTx.getQuantity();
                    Transaction depositRevenue = new CashTransaction(Transaction.TxType.DEPOSIT, revenueFromSale);
                    sharesMap.get(stockTx.getStock().getTickerSymbol()).setQuantity(-stockTx.getQuantity());
                    getPatron().getSavingsAccount().executeTransaction(depositRevenue);
                    this.transactions.add(stockTx);
                    return;
                }
            }
            throw new InsufficientAssetsException(tx, getPatron());
        }
        for(Transaction transaction:transactions){
            System.out.println(transaction.getType());
            System.out.println(transactions);
        }
    }


    /**
     * the value of a BrokerageAccount is calculated by adding up the values of each StockShare.
     * The value of a StockShare is calculated by multiplying the StockShare quantity by its listing's price.
     * @return the total value
     */
    @Override
    public double getValue() {
        double totalValue = 0;
        for (StockShares stockShare:getListOfShares()) {
            totalValue += stockShare.getValue();
        }
        return totalValue;
    }
}
