package Model;

public class Own {
    private int traderAccountID;
    private int stockID;
    private int amount;

    public Own(int traderAccountID, int stockID, int amount){
        this.traderAccountID = traderAccountID;
        this.stockID = stockID;
        this.amount = amount;
    }
    
    public int getTraderAccountID() {
        return this.traderAccountID;
    }

    public void setTraderAccountID(int traderAccountID) {
        this.traderAccountID = traderAccountID;
    }

    public int getStockID() {
        return this.stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    

}
