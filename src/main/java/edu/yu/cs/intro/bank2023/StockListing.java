package edu.yu.cs.intro.bank2023;

/**
 * represents the stock of a single company that is listed on the StockExchange
 */
public class StockListing {
    private int availableShares;
    private double price;
    private double yesterdaysPrice;
    private String tickerSymbol;
    private int sharesBought;
    private int sharesSold;
    private int timesBought;
    private int timesSold;
    private boolean up;
    private boolean down;


    /**
     *
     * @param tickerSymbol
     * @param initialPrice
     * @param availableShares
     * @throws IllegalArgumentException if the tickerSymbol is null or empty, if the initial price is <= 0, of if availableShares <= 0
     */
    protected StockListing(String tickerSymbol, double initialPrice, int availableShares){
        if(tickerSymbol == null || tickerSymbol.length() < 1 || initialPrice < 1 || availableShares < 1){
            throw new IllegalArgumentException();
        }
        this.tickerSymbol = tickerSymbol;
        this.price = initialPrice;
        this.availableShares = availableShares;
        this.sharesBought = 0;
        this.sharesSold = 0;
        this.timesBought = 0;
        this.timesSold = 0;
        this.up = false;
        this.down = false;
    }

    public String getTickerSymbol() {
        return this.tickerSymbol;
    }
    public double getPrice() {
      return this.price;
    }
    public int getAvailableShares() {
       return this.availableShares;
    }

    /**
     * set the price for a single share of this stock
     * @param price
     */
    protected void setPrice(double price) {
        this.price = price;
    }
    /**
     * increase the number of shares available
     * @param availableShares
     * @return the total number of shares after adding availableShares
     * @throws IllegalArgumentException if availableShares <= 0
     */
    protected int addAvailableShares(int availableShares) {
        if(availableShares > 0) {
            this.availableShares += availableShares;
            return this.availableShares;
        }
        throw new IllegalArgumentException();
    }
    /**
     * reduce the number of shares available
     * @param quantityToSubtract
     * @return the total number of shares after reducing availableShares
     * @throws IllegalArgumentException if quantityToSubtract > the number of available shares
     */
    protected int reduceAvailableShares(int quantityToSubtract){
        if(quantityToSubtract < this.availableShares) {
            //System.out.println("Stock Symbol " + this.tickerSymbol + " Amount of shares " + this.availableShares);
            this.availableShares -= quantityToSubtract;
            //System.out.println("Stock Symbol " + this.tickerSymbol + " Amount of shares " + this.availableShares);
            return this.availableShares;
        }
        throw new IllegalArgumentException();
    }

    protected void executeSale(StockTransaction tx){
       if(tx == null){
           throw new NullPointerException();
       }
       if(tx.getType().equals(StockTransaction.TxType.BUY)){
           //System.out.println("bought");
           timesBought++;
           sharesBought += tx.getQuantity();
           //System.out.println(tickerSymbol + "   " + timesBought);
       }else if(tx.getType().equals(StockTransaction.TxType.SELL)){
           //System.out.println("sold");
           timesSold++;
           sharesSold += tx.getQuantity();
           //System.out.println(tickerSymbol + "   " + timesSold);
       }
    }

    public int getSharesBought() {
        return sharesBought;
    }

    public int getSharesSold() {
        return sharesSold;
    }

    protected void resetDailyTransactions() {
        this.sharesBought = 0;
        this.sharesSold = 0;
        this.timesSold = 0;
        this.timesBought = 0;
    }

    public double getYesterdaysPrice() {
        return yesterdaysPrice;
    }

    protected void changePrice(){
        if (sharesBought > sharesSold) {
            up = true;
            down = false;
            //yesterdaysPrice = this.price;
            double newPrice =  getPrice()/100 * (sharesBought - sharesSold);
            this.price += newPrice; //+ (newPrice/10);
        } else if (sharesSold > sharesBought) {
            //yesterdaysPrice = this.price;
            //System.out.println("price before change: $" + this.price);
            double newPrice = getPrice()/100 * (sharesSold - sharesBought);
            //System.out.println("price to subtract: $" + newPrice);
            this.price -= newPrice;
            //System.out.println("price after change: $" + this.price);
            down = true;
            up = false;
        } else{
            down = up = false;
        }
        /*if(this.price > this.yesterdaysPrice) {

        } else if (this.price < yesterdaysPrice) {
            if (sharesBought > sharesSold) {
                up = true;
                yesterdaysPrice = this.price;
                double newPrice = getPrice()/100 * (sharesBought - sharesSold);
                this.price += newPrice;
            } else if (sharesBought < sharesSold) {
                yesterdaysPrice = this.price;
                double newPrice = getPrice()/100 * (sharesSold - sharesBought);
                this.price -= newPrice + (newPrice/10);
                down = true;
                up = false;
            } else{
                if((timesBought != 0 && timesSold != 0) && sharesBought/timesBought > sharesSold/timesSold){
                    up = false;
                    down = false;
                } else if (timesBought == 0 && timesSold != 0) {
                    down = true;
                    up = false;
                }else {
                    down = false;
                    up = false;
                }
            }
        }else {
            if(sharesBought > sharesSold){
                yesterdaysPrice = this.price;
                double newPrice = getPrice()/100 * (sharesBought - sharesSold);
                this.price += newPrice;
                up = true;
                down = false;
            } else if (sharesBought < sharesSold) {
                yesterdaysPrice = this.price;
                double newPrice = getPrice() / 100 * (sharesSold - sharesBought);
                this.price -= newPrice;
                down = true;
                up = false;
            }
        }*/
        //resetDailyTransactions();
    }

    public boolean isDown() {
        return down;
    }

    public boolean isUp() {
        return up;
    }
}