package edu.yu.cs.intro.bank2023;

import java.util.*;

public class Bank {
    private StockExchange exchange;
    private Set<Account> accounts = new HashSet<>();
    private HashSet<Patron> patrons = new HashSet<>();
    private Map<Patron, String> accountNames = new HashMap<>();
    private int nextId;


    /**
     * @param exchange the stock exchange on which all stock are listed
     * @throws IllegalArgumentException if exchange is null
     */
    protected Bank(StockExchange exchange){
        if(exchange == null){
            throw new IllegalArgumentException();
        }
        this.exchange = exchange;
        this.nextId = 1;
    }
    /**
     * Create a new Patron whose ID is the next unique available Patron ID and whose Bank is set to this bank.
     * Add the new Patron to the Bank's Set of Patrons.
     * No two Patrons can have the same ID. Each ID which is assigned should be greater than the previous ID.
     * @return a new Patron with a unique ID, but no accounts
     */
    public Patron createNewPatron(String name){
        Patron patron = new Patron(nextId++, this, name);
        patrons.add(patron);
        accountNames.put(patron, name);
        return patron;
    }

    /**
     * Create a new SavingsAccount for the Patron.
     * The SavingsAccount's id must be the next unique account ID available.
     * No two accounts of ANY KIND can have the same ID. Each ID which is assigned should be greater than the previous ID.
     * Add the new SavingsAccount to the Bank's Set of Accounts.
     * @param p the Patron for whom the account is being created
     * @return the SavingsAccount's id
     * @throws ApplicationDeniedException thrown if Patron already has a SavingsAccount
     * @throws IllegalArgumentException if p is null
     */
    public int openNewSavingsAccount(Patron p) throws ApplicationDeniedException{
        int id = nextId++;
        if(p == null){
            throw new IllegalArgumentException();
        }
        if(p.getSavingsAccount() != null){
            throw new ApplicationDeniedException("Patron already has a savings account");
        }
        SavingsAccount savings = new SavingsAccount(id, p);
        p.setSavingsAccount(savings);
        accounts.add(savings);
        return id;
    }

    /**
     * Create a new BrokerageAccount for the Patron.
     * The BrokerageAccount's id must be the next unique account ID available.
     * No two accounts of ANY KIND can have the same ID. Each ID which is assigned should be greater than the previous ID.
     * Add the new BrokerageAccount to the Bank's Set of Accounts.
     * @param p the Patron for whom the account is being created
     * @return the BrokerageAccount's id
     * @throws ApplicationDeniedException thrown if the Patron doesn't have a SavingsAccount or DOES already have a BorkerageAccount
     * @throws IllegalArgumentException if p is null
     */
    public int openNewBrokerageAccount(Patron p)throws ApplicationDeniedException{
        int id = nextId++;
        if(p == null){
            throw new IllegalArgumentException();
        }
        if(p.getBrokerageAccount() != null){
            throw new ApplicationDeniedException("Patron already has a brokerage account or doesn't have a savings account");
        }
        BrokerageAccount brokerage = new BrokerageAccount(id, p);
        p.setBrokerageAccount(brokerage);
        accounts.add(brokerage);
        return id;
    }

    /**
     *
     * @return an unmodifiable set of all the accounts (both Savings and Brokerage)
     * @see java.util.Collections#unmodifiableSet(Set)
     */
    protected Set<Account> getAllAccounts() {
        return Collections.unmodifiableSet(accounts);
    }

    /**
     *
     * @return an unmodifiable set of all the Patrons
     * @see java.util.Collections#unmodifiableSet(Set)
     */
    protected Set<Patron> getAllPatrons() {
        return Collections.unmodifiableSet(patrons);
    }

    /**
     * @return the exchange used by this Bank
     */
    protected StockExchange getExchange() {
        return this.exchange;
    }
}