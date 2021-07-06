package Model;

import java.util.Random;

public class OrdSell implements Comparable<OrdSell>{
    private int orderSellID;
    private int stockID;
    private int traderAccountID;
    private int price;
    private int amount;

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    private static int cnt = 0;

    public OrdSell(){
        this.orderSellID = cnt++;
        this.stockID = new Random(this.orderSellID + 10).nextInt() % 1001;
        this.traderAccountID = new Random(this.stockID + 10).nextInt() % 1001;
    }

    public OrdSell(int stockID, int traderAccountID, int amount, int price){
        this.orderSellID = cnt++;
        this.stockID = stockID;
        this.traderAccountID = traderAccountID;
        this.amount = amount;
        this.price = price;
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

   
    public int getTraderAccountID() {
        return this.traderAccountID;
    }

    public void setTraderAccountID(int tradeAccountID) {
        this.traderAccountID = tradeAccountID;
    }
    
    @Override
    public int compareTo(OrdSell o) {
        if(this.price != o.price){

            return this.price - o.price;
        }

        return this.amount - o.amount;
    }

    @Override
    public String toString() {   
        return " --[ORDSELL] ID: " + this.orderSellID + " stockID: " + this.stockID + " traderAccountID: " + this.traderAccountID 
       + " amount: " + this.amount + " price: " + this.price;
    }

    
}
