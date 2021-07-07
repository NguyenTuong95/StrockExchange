package Controller;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import Database.InsertData;
import Database.SelectData;
import Model.OrdSell;

public class OrdSellController implements Runnable{
    private PriorityBlockingQueue<OrdSell> sell;

    private InsertData insertData;
    private SelectData selectData;
    private Queue<OrdSell> lstDataOrdSell;

   public OrdSellController(PriorityBlockingQueue<OrdSell> sell){
       this.sell = sell;

       this.insertData = InsertData.getInstance();
       this.selectData = SelectData.getInstance();
       lstDataOrdSell = selectData.selectDataOrdSell();
   }

    @Override
    public void run() {
        while(lstDataOrdSell.size() > 0){
            OrdSell item = lstDataOrdSell.poll();
            insertData.insertOrdSell(item);
            //System.out.println(item);         
            sell.add(item);       
            
        }
    }

    
}
