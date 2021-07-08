package Util;

public class PublicData {
    private int stockID;
    private static PublicData instance;
    private PublicData(){
    }

    public static PublicData getInstance(){
        if(instance == null){
            synchronized(PublicData.class){
                if(instance == null){
                    instance = new PublicData();
                }
            }
        }

        return instance;
    }

    public void setStockID(int stockID){
        this.stockID = stockID;
    }
    public int getStockID(){
        return this.stockID;
    }

}
