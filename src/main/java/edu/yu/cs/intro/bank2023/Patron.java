package edu.yu.cs.intro.bank2023;

/**
 * A patron (i.e. customer) of the bank, who can have up to 2 accounts - 1 savings account, 1 brokerage account.
 */
public class Patron {
    private final int id;
    private SavingsAccount savings;
    private BrokerageAccount brokerage;
    private final Bank bank;
    private final String name;

    /**
     * Will be called by Bank
     * MUST be created with its SavingsAccount and BrokerageAccount being null. Accounts are to be created ONLY via the appropriate calls to the Bank class.
     * @param id account id assigned by the bank
     * @param bank
     * @see Bank#createNewPatron()
     * @see Bank#openNewSavingsAccount(Patron)
     * @see Bank#openNewBrokerageAccount(Patron)
     */
    protected Patron(int id, Bank bank, String name){
     this.id = id;
     this.bank = bank;
     this.savings = null;
     this.brokerage = null;
     this.name = name;
    }
    public int getId() {
     return this.id;
    }
    protected Bank getBank() {
        return this.bank;
    }

    /**
     *
     * @param savings
     * @throws ApplicationDeniedException thrown if the Patron already has a savings account
     * @see Bank#openNewSavingsAccount(Patron)
     */
    protected void setSavingsAccount(SavingsAccount savings) throws ApplicationDeniedException{
        if(this.savings != null){
            throw new ApplicationDeniedException("Patron already has a savings account");
        }
        this.savings = savings;
    }

    /**
     *
     * @param brokerage
     * @throws ApplicationDeniedException thrown if the Patron already has a BrokerageAccount OR if the Patron does NOT have a SavingsAccount
     * @see Bank#openNewBrokerageAccount(Patron)
     */
    protected void setBrokerageAccount(BrokerageAccount brokerage) throws ApplicationDeniedException{
        if(this.brokerage != null || this.savings == null){
            throw new ApplicationDeniedException("Patron already has a brokerage account or doesn't have a savings account");
        }
        this.brokerage = brokerage;
    }

    /**
     * @return the value of the Patron's SavingsAccount + the value of the Patron's BrokerageAccount
     * @see SavingsAccount#getValue()
     * @see BrokerageAccount#getValue()
     */
    public double getNetWorth(){
        if(this.savings == null){
            return 0;
        } else if (this.brokerage == null) {
            return this.getSavingsAccount().getValue();
        }
        return this.getSavingsAccount().getValue() + this.getBrokerageAccount().getValue();
    }
    public SavingsAccount getSavingsAccount() {
      return this.savings;
    }
    public BrokerageAccount getBrokerageAccount() {
        return this.brokerage;
    }
}