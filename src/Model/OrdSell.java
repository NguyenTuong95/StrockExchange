package Model;

public class OrdSell implements Comparable<OrdSell>{
    private int price;
    private int amount;
    private Trader trader;

    public OrdSell(Trader trader, int price, int amount){
        this.trader = trader;
        this.price = price;
        this.amount = amount;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public int compareTo(OrdSell o) {
        return price - o.price;
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return trader.toString() + " price:" + price + " amount: " + amount;
    }
}
