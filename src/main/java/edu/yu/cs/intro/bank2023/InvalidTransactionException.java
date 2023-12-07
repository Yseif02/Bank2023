package edu.yu.cs.intro.bank2023;

public class InvalidTransactionException extends Exception{
    private String message;
    private Transaction.TxType type;


    public InvalidTransactionException(String message, Transaction.TxType type){
        this.message = message;
        this.type = type;
    }

    public Transaction.TxType getType(){
        return type;
    }
}
