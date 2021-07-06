package Model;


public class Trader {
    
    private int traderAccountID;
    private float balance;

    private static int cnt = 0;

    public Trader(){
        this.traderAccountID = cnt++;
    }

    public Trader(int id, float balance){
        this.traderAccountID = id;
        this.balance = balance;
    }

    public int getTraderAccountID() {
        return this.traderAccountID;
    }

    public void setTraderAccountID(int traderAccountID) {
        this.traderAccountID = traderAccountID;
    }

    public float getBalance() {
        return this.balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }


   

}
