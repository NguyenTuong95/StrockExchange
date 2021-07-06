package Model;


public class Trader {
    
    private int traderAccountID;
    private int balance;

    private static int cnt = 0;

    public Trader(){
        this.traderAccountID = cnt++;
    }

    public Trader(int id, int balance){
        this.traderAccountID = id;
        this.balance = balance;
    }

    public int getTraderAccountID() {
        return this.traderAccountID;
    }

    public void setTraderAccountID(int traderAccountID) {
        this.traderAccountID = traderAccountID;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }


   

}
