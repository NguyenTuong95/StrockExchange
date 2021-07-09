package Controller;
import Model.Account;
import Model.Company;
import Model.Stock;

import java.io.Serializable;

public class Response implements Serializable {

    private int type;
    private int result;
    private String msg;
    private Account acc;
    private Company companay;
    private Stock stock;

    public Response(int requestType, int result, String msg) {
        this.type = requestType;
        this.result = result;
        this.msg = msg;
    }

    public Response(int requestType, int result, Account acc) {
        this.type = requestType;
        this.result = result;
        this.acc = acc;
    }

    public Response(int requestType, int result, String msg, Account acc) {
        this.type = requestType;
        this.result = result;
        this.msg = msg;
        this.acc = acc;
    }

    public Response(int requestType, int result, String msg, Company com) {
        this.type = requestType;
        this.result = result;
        this.msg = msg;
        this.companay = com;
    }

    public Response(int requestType, int result, String msg, Stock st) {
        this.type = requestType;
        this.result = result;
        this.msg = msg;
        this.stock = st;
    }

    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public int getRequestType() {
        return type;
    }

    public Company getCompanay() {
        return companay;
    }

    public void setCompanay(Company companay) {
        this.companay = companay;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setRequestType(int requestType) {
        this.type = requestType;
    }

    @Override
    public String toString() {
        String ret = "Request type: " + type + " - " + "result: " + result + " - Message from server: " + msg;

        return ret;
    }
}
