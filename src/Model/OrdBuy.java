package Model;


import java.sql.Date;
import java.util.Random;

public class OrdBuy implements Comparable<OrdBuy>{
    private int orderSellID;
    private int stockID;
    private Date createTime;
    private int initAmount;
  
    private int tradeAccountID;
    private static int cnt = 0;

    public OrdBuy(){
        this.orderSellID = cnt++;
        this.stockID = new Random(this.orderSellID + 10).nextInt() % 1001;
        this.tradeAccountID = new Random(this.stockID + 10).nextInt() % 1001;
        //this.createTime = new Date(exchangedAmount);
    }

    public OrdBuy(int orderSellID, int stockID,  int tradeAccountID, int initAmount, int exchangedAmount){
        this.orderSellID = orderSellID;
        this.stockID = stockID;
        //this.createTime = new Date();
        this.initAmount = exchangedAmount;
        this.tradeAccountID = tradeAccountID;
    }

    public int getOrderSellID() {
        return this.orderSellID;
    }

    public void setOrderSellID(int orderSellID) {
        this.orderSellID = orderSellID;
    }

    public int getStockID() {
        return this.stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getInitAmount() {
        return this.initAmount;
    }

    public void setInitAmount(int initAmount) {
        this.initAmount = initAmount;
    }

    public int getExchangedAmount() {
        return this.exchangedAmount;
    }

    public void setExchangedAmount(int exchangedAmount) {
        this.exchangedAmount = exchangedAmount;
    }

    public int getTradeAccountID() {
        return this.tradeAccountID;
    }

    public void setTradeAccountID(int tradeAccountID) {
        this.tradeAccountID = tradeAccountID;
    }
    
    @Override
    public int compareTo(OrdBuy o) {
        return this.exchangedAmount - o.exchangedAmount;
    }

    @Override
    public String toString() {   
        return " --[ORDSELL] ID: " + this.orderSellID + " stockID: " + this.stockID + " tradeAccountID: " + this.tradeAccountID + " time: " 
        + createTime + " amount: " + this.initAmount + " exchangeAmount: " + this.exchangedAmount;
    }

}
