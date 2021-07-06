package Model;


public class Stock {

    private int stockID;
    private int companyID;
    private String code;
    private float lastPrice;
    private float minPrice;
    private float maxPrice;


    private static int cnt = 0;

    public Stock(){
        this.stockID = this.companyID = cnt++;       
    }

    public Stock(int stockID, int companyID, String code, float lastPrice, float minPrice, float maxPrice){
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

    public float getLastPrice() {
        return this.lastPrice;
    }

    public void setLastPrice(float lastPrice) {
        this.lastPrice = lastPrice;
    }

    public float getMinPrice() {
        return this.minPrice;
    }

    public void setMinPrice(float minPrice) {
        this.minPrice = minPrice;
    }

    public float getMaxPrice() {
        return this.maxPrice;
    }

    public void setMaxPrice(float maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public String toString() { 
        return " --[STOCK] ID: " + this.stockID + " code: " + this.code+ " lastPrice: " + this.lastPrice 
        + "minPrice: " + this.minPrice + "maxPrice: " + this.maxPrice + "--";
    }

}
