package Controller;

import java.util.concurrent.PriorityBlockingQueue;

import Database.InsertData;
import Model.OrdBuy;
import Model.OrdSell;
import Model.Transaction;
import Model.TransactionDetail;


public class StockController implements Runnable{

    private PriorityBlockingQueue<OrdSell> pSells;
    private PriorityBlockingQueue<OrdBuy> pBuys;
    private InsertData insertData;
    private static int currentTransactionID = 1;
    private int cnt = 0;

    public StockController(int stockID){
        pSells = new PriorityBlockingQueue<>();
        pBuys = new PriorityBlockingQueue<>();
        insertData = InsertData.getInstance();

        Thread t1 = new Thread(new OrdBuyController(pBuys));
        Thread t2 = new Thread(new OrdSellController(pSells));
        t1.start();
        t2.start();

    }

    @Override
    public void run() {

        //while(cnt < 10){
            try {
                Thread.sleep(900);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Transaction transaction = new Transaction(currentTransactionID); 
            insertData.insertTransaction(transaction);

            System.out.println("********Khop Lenh*********");
            while(pSells.isEmpty() != true){
                OrdSell ordSell = pSells.poll();
                System.out.println(ordSell);   
                 while(pBuys.isEmpty() != true){
                    OrdBuy ordBuy = pBuys.poll();
                    if(ordBuy.getTraderAccountID() != ordSell.getTraderAccountID()
                        && ordBuy.getPrice() >= ordSell.getPrice()
                        && ordBuy.getStockID() == ordSell.getStockID()){
                        System.out.println("********************************");
                        System.out.println(ordBuy);
                        System.out.println("********************************");
                        int amount = (ordBuy.getAmount() < ordSell.getAmount()) ? ordBuy.getAmount() : ordSell.getAmount();
                        TransactionDetail transactionDetail = new TransactionDetail(ordBuy.getOrderBuyID(), ordSell.getOrderSellID(), currentTransactionID, amount, ordBuy.getPrice());
                        //Transaction transaction = new Transaction(currentTransactionID);    
                        //insertData.insertTransaction(transaction);
                        insertData.insertTransactionDetail(transactionDetail);                        
                                                         
                        break;
                    }
                }

            }
        currentTransactionID++;          
        cnt++;
   // }
    }   


}
