package Controller;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import Database.InsertData;
import Database.SelectData;
import Model.OrdSell;

public class OrdSellController implements Runnable{
    private PriorityBlockingQueue<OrdSell> sell;

    private InsertData insertData;
    private SelectData selectData;
    private List<OrdSell> lstDataOrdSell;

   public OrdSellController(PriorityBlockingQueue<OrdSell> sell, int stockID){
       this.sell = sell;

       this.insertData = InsertData.getInstance();
       this.selectData = SelectData.getInstance();
       String sql = "SELECT * FROM Order_Sell WHERE exchangeAmount > 0 AND stockID = " + stockID;
       lstDataOrdSell = selectData.selectDataOrdSell(sql);
   }

    @Override
    public void run() {

        for(OrdSell item : lstDataOrdSell){
            insertData.insertOrdSell(item);      
            sell.add(item);      
        }

    }

    
}
