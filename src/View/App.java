package View;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import Model.OrdBuy;
import Model.OrdSell;
import Model.Trader;

public class App {
    static final int NUMBER = 1001;

    public static void main(String[] args) throws Exception {
        List <OrdSell> sellQueue = new ArrayList<>();

        List <OrdBuy> buyQueue = new ArrayList<>();
    
        for(int i = 0; i < NUMBER; i++){
            sellQueue.add(new OrdSell(new Trader(), 
            new Random().nextInt(20)%10, new Random().nextInt(100) + 100));
            buyQueue.add(new OrdBuy(new Trader(),
            new Random().nextInt(20)%10, new Random().nextInt(100) + 100));
        }

        Queue<OrdSell> pSells = new PriorityQueue<>();
        Queue<OrdBuy> pBuys = new PriorityQueue<>();

        System.out.println("Xác định và in ra giao dịch có số lượng nhỏ nhất");
        System.out.println(sellQueue.stream().sorted((t1,t2)->t1.getAmount() - (t2.getAmount())).findFirst().toString());   

    }



}
