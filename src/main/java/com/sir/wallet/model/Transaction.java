package com.sir.wallet.model;

import jakarta.persistence.*;


@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Wallet wallet;

    private long amount;
    private String type;

    // Constructors, getters, and setters

    public Transaction() {
    }

    public Transaction(long id, Wallet wallet, long amount, String type) {
        this.id = id;
        this.wallet = wallet;
        this.amount = amount;
        this.type = type;
    }

    public Transaction(Wallet wallet, long amount, String type) {
        this.wallet = wallet;
        this.amount = amount;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
