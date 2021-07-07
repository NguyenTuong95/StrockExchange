package Model;

import java.sql.Timestamp;

public class TransactionDetail {
    private int orderBuyID;
    private int orderSellID;
    private int transactionID;
    private int amount;
    private int price;
    private Timestamp createTime;
    
    public TransactionDetail(int orderBuyID, int orderSellID, int transactionID,int amount, int price){
        this.orderBuyID = orderBuyID;
        this.orderSellID = orderSellID;
        this.transactionID = transactionID;
        this.amount = amount;
        this.price = price;
        this.createTime =  new Timestamp(System.currentTimeMillis());;
    }

    public int getOrderBuyID() {
        return this.orderBuyID;
    }

    public void setOrderBuyID(int orderBuyID) {
        this.orderBuyID = orderBuyID;
    }

    public int getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public int getOrderSellID() {
        return this.orderSellID;
    }

    public void setOrderSellID(int orderSellID) {
        this.orderSellID = orderSellID;
    }

    public Timestamp getCreateTime(){
        return this.createTime;
    }
    public void setCreateTime(Timestamp createTime){
        this.createTime = createTime;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}
