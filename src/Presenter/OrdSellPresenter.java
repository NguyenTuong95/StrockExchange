package Presenter;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

import Model.OrdSell;

public class OrdSellPresenter implements Runnable {


    private PriorityBlockingQueue<OrdSell> sell;
    private int cnt = 0;

   public OrdSellPresenter(){
       //this.ordBuyInterface = ordBuyInterface;
       sell = new PriorityBlockingQueue<>();
   }

    @Override
    public void run() {
        while(cnt < 100){
            OrdSell item = genDataOrdSell();
            System.out.println(item);         
            sell.add(item);
            loadData(sell);
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
    public void loadData(PriorityBlockingQueue<OrdSell> sell){

    }
    
}
