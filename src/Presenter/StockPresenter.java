package Presenter;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

import Model.OrdBuy;
import Model.OrdSell;

public class StockPresenter implements Runnable{
    private Queue<OrdSell> sell;
    private Queue<OrdBuy> buy;
    private StockInterface stockInterface;

    StockPresenter(){
        this.sell = new PriorityBlockingQueue<>();
        this.buy = new PriorityBlockingQueue<>();
    }

    @Override
    public void run() {
       
        
    }
    


}
