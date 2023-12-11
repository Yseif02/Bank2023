package edu.yu.cs.intro.bank2023;

public class InsufficientAssetsException extends Exception{
    final Transaction tx;
    final Patron p;

    public InsufficientAssetsException(Transaction tx, Patron p){
        this.tx = tx;
        this.p = p;
    }

    public Transaction getTx() {
        return tx;
    }

    public Patron getPatron() {
        return p;
    }
}