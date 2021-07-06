package Controller;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

import Model.OrdSell;

public class OrdSellController implements Runnable{
    private PriorityBlockingQueue<OrdSell> sell;
    private int cnt = 0;

   public OrdSellController(PriorityBlockingQueue<OrdSell> sell){
       this.sell = sell;
   }

    @Override
    public void run() {
        while(cnt < 600){
            OrdSell item = genDataOrdSell();
            //System.out.println(item);         
            sell.add(item);       
            cnt++;
        }
        //OrdSellInterface.loadDataOrdSell(sell);
    }

    private OrdSell genDataOrdSell(){
        int traderAccountID = new Random().nextInt(100);
        int amount = 100 + new Random().nextInt(100);
        int price = 200 + new Random().nextInt(100); 
        int stockID = new Random().nextInt(100);;
        OrdSell ordSell = new OrdSell(stockID, traderAccountID, amount, price);
        try{
            Thread.sleep(150);
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }

        return ordSell;
    }
}
