package Controller;

import java.util.concurrent.PriorityBlockingQueue;

import Model.OrdBuy;
import Model.OrdSell;

public class StockController implements Runnable{

    private PriorityBlockingQueue<OrdSell> pSells;
    private PriorityBlockingQueue<OrdBuy> pBuys;
    private int cnt = 0;

    public StockController(){
        pSells = new PriorityBlockingQueue<>();
        pBuys = new PriorityBlockingQueue<>();

        Thread t1 = new Thread(new OrdBuyController(pBuys));
        Thread t2 = new Thread(new OrdSellController(pSells));
        t1.start();
        t2.start();

    }

    @Override
    public void run() {

        while(cnt < 30){
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("********Khop lenh*********");
            while(pSells.isEmpty() != true){
                OrdSell ordSell = pSells.poll();
                System.out.println(ordSell);

                while(pBuys.isEmpty() != true){
                    OrdBuy ordBuy = pBuys.poll();
                    if(ordBuy.getStockID() == ordSell.getStockID() && ordBuy.getPrice() >= ordSell.getPrice()){
                        System.out.println(ordBuy);
                    }else{
                        //pBuys.add(ordBuy);
                    }
                }

            }
           
        cnt++;
    }
    }   


}
