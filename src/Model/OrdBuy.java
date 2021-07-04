package Model;



import java.sql.Date;
import java.util.Random;

public class OrdBuy implements Comparable<OrdBuy>{
    private int orderBuyID;
    private int stockID;
    private Date createTime;
    private int initAmount;
    private int exchangedAmount;
    private int tradeAccountID;
    private static int cnt = 0;

    public OrdBuy(){
        this.orderBuyID = cnt++;
        this.stockID = new Random(this.orderBuyID + 10).nextInt() % 1001;
        this.tradeAccountID = new Random(this.stockID + 10).nextInt() % 1001;
        //this.createTime = new Date();
    }

    public OrdBuy(int orderBuyID, int stockID,  int tradeAccountID, int initAmount, int exchangedAmount){
        this.orderBuyID = orderBuyID;
        this.stockID = stockID;
        //this.createTime = new Date();
        this.initAmount = exchangedAmount;
        this.tradeAccountID = tradeAccountID;
    }

    public int getOrderBuyID() {
        return this.orderBuyID;
    }

    public void setOrderBuyID(int orderBuyID) {
        this.orderBuyID = orderBuyID;
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
        return " --[ORDSELL] ID: " + this.orderBuyID + " stockID: " + this.stockID + " tradeAccountID: " + this.tradeAccountID + " time: " 
        + createTime + " amount: " + this.initAmount + " exchangeAmount: " + this.exchangedAmount;
    }

}
