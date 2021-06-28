package Model;

public class Stock {

    private int stockID;
    private int stockUperPrice;
    private int stockLowerPrice;
    private int stockActionPrice;
    private static int cnt = 0;

    public Stock(){
        stockID = cnt++;
    }

    public Stock(int stockUperPrice){}

    public int getStockID() {
        return this.stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getStockUperPrice() {
        return this.stockUperPrice;
    }

    public void setStockUperPrice(int stockUperPrice) {
        this.stockUperPrice = stockUperPrice;
    }

    public int getStockLowerPrice() {
        return this.stockLowerPrice;
    }

    public void setStockLowerPrice(int stockLowerPrice) {
        this.stockLowerPrice = stockLowerPrice;
    }

    public int getStockActionPrice() {
        return this.stockActionPrice;
    }

    public void setStockActionPrice(int stockActionPrice) {
        this.stockActionPrice = stockActionPrice;
    }

    

}
