package Model;


import java.util.Random;

public class OrdBuy implements Comparable<OrdBuy>{
    private int orderBuyID;
    private int stockID;
    private int traderAccountID;
    private int amount;
    private int price;

    

    private static int cnt = 0;

    public OrdBuy(){
        this.orderBuyID = cnt++;
        this.stockID = new Random(this.orderBuyID + 10).nextInt() % 1001;
        this.traderAccountID = new Random(this.stockID + 10).nextInt() % 1001;
    }


    public OrdBuy(int stockID, int traderAccountID, int amount, int price){
        this.orderBuyID = cnt++;
        this.stockID = stockID;
        this.traderAccountID = traderAccountID;
        this.amount = amount;
        this.price = price;
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

   
    public int getTraderAccountID() {
        return this.traderAccountID;
    }

    public void setTraderAccountID(int tradeAccountID) {
        this.traderAccountID = tradeAccountID;
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
    
    @Override
    public int compareTo(OrdBuy o) {

        if(this.price != o.price){

            return this.price - o.price;
        }

        return this.amount - o.amount;
        
    }

    @Override
    public String toString() {   
        return " --[ORDBUY] ID: " + this.orderBuyID + " stockID: " + this.stockID + " traderAccountID: " + this.traderAccountID 
       + " amount: " + this.amount + " price: " + this.price;
    }

}
