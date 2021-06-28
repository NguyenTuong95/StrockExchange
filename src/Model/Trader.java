package Model;

import java.util.Date;

import Util.Utility;

public class Trader {
    private int traderID;
    private int tradeType;
    private int tradeStatus;
    private Date tradeDate;
    private User user;
    private Stock stock;
    private static int cnt = 1;


    public Trader(){
        this.traderID = cnt++;
        this.user = Utility.generateUser();
        this.stock = new Stock();
    }
    public Trader(User user, Stock stock, int type, int status, Date date){
        this.user = user;
        this.stock = stock;
        this.tradeType = type;
        this.tradeStatus = status;
        this.tradeDate = date; 
    }

    public int getTraderID() {
        return this.traderID;
    }

    public void setTraderId(int traderID) {
        this.traderID = traderID;
    }

    public int getTradeType() {
        return this.tradeType;
    }

    public void setTradeType(int tradeType) {
        this.tradeType = tradeType;
    }

    public int getTradeStatus() {
        return this.tradeStatus;
    }

    public void setTradeStatus(int tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Date getTradeDate() {
        return this.tradeDate;
    }

    public void setTrdeDate(Date tradeDate) {
        this.tradeDate = tradeDate;
    }

    @Override
    public String toString() {
        
        return user.toString();
    }

}
