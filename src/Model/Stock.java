package Model;


public class Stock {

    private int stockID;
    private int companyID;
    private String code;
    private int lastPrice;
    private int minPrice;
    private int maxPrice;


    private static int cnt = 0;

    public Stock(){
        this.stockID = this.companyID = cnt++;       
    }

    public Stock(int stockID, int companyID, String code, int lastPrice, int minPrice, int maxPrice){
        this.stockID = stockID;
        this.companyID = companyID;
        this.code = code;
        this.lastPrice = lastPrice;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public int getStockID() {
        return this.stockID;
    }

    public void setStockID(int stockID) {
        this.stockID = stockID;
    }

    public int getCompanyID() {
        return this.companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLastPrice() {
        return this.lastPrice;
    }

    public void setLastPrice(int lastPrice) {
        this.lastPrice = lastPrice;
    }

    public int getMinPrice() {
        return this.minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return this.maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() { 
        return " --[STOCK] ID: " + this.stockID + " code: " + this.code+ " lastPrice: " + this.lastPrice 
        + "minPrice: " + this.minPrice + "maxPrice: " + this.maxPrice + "--";
    }

}
