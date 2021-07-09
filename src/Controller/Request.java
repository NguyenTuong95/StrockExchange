package Controller;

import Model.Account;
import Model.Company;
import Model.Stock;

import java.io.Serializable;

public class Request implements Serializable {
    private int type;
    private Account acc;
    private Company company;
    private Stock stock;

    public Request(int type, Account acc, Company company, Stock stock) {
        this.type = type;
        this.acc = acc;
        this.company = company;
        this.stock = stock;
    }

    public Request(int type, Account acc, Company company) {
        super();
        this.type = type;
        this.acc = acc;
        this.company = company;
    }

    public Request(int requestType, Account acc) {
        this.type = requestType;
        this.acc = acc;
    }

    public Request(int type, Account acc, Stock st) {
        this.type = type;
        this.acc = acc;
        this.stock = st;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String ret = "Type = " + this.type + " - " + this.acc.toString();
        return ret;
    }
}
