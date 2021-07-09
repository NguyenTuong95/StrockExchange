package Controller;

import Model.*;
import socket.SystemServer;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.PriorityBlockingQueue;
import Database.InsertData;
import Database.SelectData;

public class TransactionController implements Runnable{
    private static int DEFAULT_TIME_TRANSAC = 60;
    private static InsertData insertData;
    private static SelectData selectData;

    @Override
    public void run() {
        try {
            getMapStock();
            Transaction transaction = new Transaction();

            int transactId = transaction.getTransactionID();

            System.out.println("Transaction Id " + transactId);

            PriorityBlockingQueue<OrdBuy> oderBuys = null;
            PriorityBlockingQueue<OrdSell> oderSells = null;

            Set<Integer> stockLst = SystemServer.mapOrderBuy.keySet();

            for(int id: stockLst) {
                System.out.println("Id stock = " + id);

                oderBuys = SystemServer.mapOrderBuy.get(id);
                oderSells = SystemServer.mapOrderSell.get(id);

                while (!oderBuys.isEmpty() && !oderSells.isEmpty()
                        && oderBuys.peek().getTraderAccountID() != oderSells.peek().getTraderAccountID() 
                        && oderBuys.peek().getPrice() <= oderSells.peek().getPrice()) {
                    int transactionAmount = 0;
                    int matchedPrice = 0;
                    int initAmountSell = 0;
                    int initAmountBuy = 0;

                    OrdBuy oderBuy = oderBuys.peek();
                    OrdSell oderSell = oderSells.peek();

                    matchedPrice = oderSell.getPrice();
                    initAmountBuy = oderBuy.getAmount() - oderBuy.getExchangeAmount();
                    initAmountSell = oderSell.getAmount() - oderSell.getExchangeAmount();

                    if (initAmountSell < initAmountBuy) {
                        transactionAmount = initAmountSell;
                        oderSells.poll();
                    } else if (initAmountSell > initAmountBuy){
                        transactionAmount = initAmountBuy;
                        oderBuys.poll();
                    } else {
                        transactionAmount = initAmountBuy;
                        oderBuys.poll();
                        oderSells.poll();
                    }

                    System.out.println("----------------------------------------------------------");
                    System.out.println("Last OderBuy : " + oderBuy.toString());

                    System.out.println("----------------------------------------------------------");
                    System.out.println("Last OderSell : " + oderSell.toString());

                    oderSell.setExchangeAmount(initAmountBuy + transactionAmount);
                    oderBuy.setExchangeAmount(initAmountSell - transactionAmount);

                    System.out.println("transactionAmount " + transactionAmount +" : matchedPrice " + matchedPrice);
                    TransactionDetail transactionDetail = new TransactionDetail(oderBuy.getOrderBuyID(), oderSell.getOrderSellID(), transactId, transactionAmount, matchedPrice);
                    
                    insertData.insertTransactionDetail(transactionDetail);

                }
            }

            System.out.println("Waiting 60 seconds for next transaction");
            Thread.sleep(DEFAULT_TIME_TRANSAC * 1000);


        } catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        }
    }

    private static void getMapStock() throws SQLException {
        PriorityBlockingQueue<OrdSell> oderSells = new PriorityBlockingQueue<>();
        PriorityBlockingQueue<OrdBuy> oderBuys = new PriorityBlockingQueue<>();
        selectData = SelectData.getInstance();
        String sql = "SELECT * FROM Stock";
        List<Stock> stocks = (List<Stock>) selectData.selectDataStocks(sql);

        if (stocks.isEmpty()) {
            return;
        }

        for(Stock stock : stocks) {
            String queryOrdBuy = "SELECT * FROM Order_Buy WHERE stockID =" + stock.getStockID();
            List<OrdBuy> lstOrdBuy = (List<OrdBuy>) selectData.selectDataOrdBuy(queryOrdBuy);
            String queryOrdSell = "SELECT * FROM Order_Sell WHERE stockID =" + stock.getStockID();
            List<OrdSell> lstOrdSell = (List<OrdSell>) selectData.selectDataOrdSell(queryOrdSell);
            for(OrdBuy item : lstOrdBuy){
                oderBuys.add(item);
            }

            for(OrdSell item : lstOrdSell){
                oderSells.add(item);
            }

            SystemServer.mapOrderBuy.put(stock.getStockID(), oderBuys);
            SystemServer.mapOrderSell.put(stock.getStockID(), oderSells);
        }
    }
}
