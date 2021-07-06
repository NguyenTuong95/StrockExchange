package Model;

public class TransactionDetail {
    private int orderBuyID;
    private int orderSellID;
    private int transactionID;
    private int amount;
    private int price;

    public TransactionDetail(int orderBuyID, int orderSellID, int transactionID,int amount, int price){
        this.orderBuyID = orderBuyID;
        this.orderSellID = orderSellID;
        this.transactionID = transactionID;
        this.amount = amount;
        this.price = price;
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
