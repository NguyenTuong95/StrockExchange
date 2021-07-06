package Controller;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

import Model.OrdBuy;

public class OrdBuyController implements Runnable{
    private PriorityBlockingQueue<OrdBuy> buy;
    private int cnt = 0;

   public OrdBuyController(PriorityBlockingQueue<OrdBuy> buy){
    this.buy = buy;
   }

    @Override
    public void run() {
        while(cnt < 600){
            OrdBuy item = genDataOrdBuy();
            //System.out.println(item);
            buy.add(item);
            cnt++;
        }

    }

    private OrdBuy genDataOrdBuy(){
        int traderAccountID = new Random().nextInt(100);
        int amount = 100 + new Random().nextInt(100);
        int price = 200 + new Random().nextInt(100); 
        int stockID = new Random().nextInt(100);;
        OrdBuy ordBuy = new OrdBuy(stockID, traderAccountID, amount, price);
        try{
            Thread.sleep(150);
        }catch(InterruptedException ie){
            ie.printStackTrace();
    }

        return ordBuy;
    }
}
