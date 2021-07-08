package Controller;


import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import Database.InsertData;
import Database.SelectData;
import Model.OrdBuy;

public class OrdBuyController implements Runnable{
    private PriorityBlockingQueue<OrdBuy> buy;

    private InsertData insertData;
    private SelectData selectData;
    private  Queue<OrdBuy> lstDataOrdBuy;

   public OrdBuyController(PriorityBlockingQueue<OrdBuy> buy, int stockID){
    this.buy = buy;
 
    insertData = InsertData.getInstance();
    selectData = SelectData.getInstance();
    String sql = "SELECT * FROM Order_Buy WHERE exchangeAmount < amount AND stockID = " + stockID;
    lstDataOrdBuy = selectData.selectDataOrdBuy(sql);
   }

    @Override
    public void run() {
        while(lstDataOrdBuy.size() > 0){
            OrdBuy item = lstDataOrdBuy.poll();
            insertData.insertOrdBuy(item);
            buy.add(item);
        }

    }

    
}
