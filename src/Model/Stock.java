package Model;

public class Stock {

    private int stockID;
    private int companyID;
    private String code;
    private float lastPrice;
    private int minPrice;
    private int maxPrice;
    private int totalAmount;
    private static int cnt = 0;

    public Stock(){
        this.stockID = this.companyID = cnt++;
    }

    public Stock(int stockID, int companyID, String code, float lastPrice, int minPrice, int maxPrice, int totalAmount){
        this.stockID = stockID;
        this.companyID = companyID;
        this.code = code;
        this.lastPrice = lastPrice;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.totalAmount = totalAmount;
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

    public int getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

    @Override
    public String toString() { 
        return " --[STOCK] ID: " + this.stockID + " code: " + this.code+ " lastPrice: " + this.lastPrice 
        + "minPrice: " + this.minPrice + "maxPrice: " + this.maxPrice +  "totalAmount:" + this.totalAmount + "--";
    }

}
