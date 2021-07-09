package Model;

import java.sql.Timestamp;

public class Transaction {
    private int transactionID;
    private Timestamp createTime;
    private static int cnt = 0;

    public Transaction(){
        this.transactionID = cnt++;
        this.createTime = new Timestamp(System.currentTimeMillis());      
    }

    public Transaction(int transactionID){
        this.transactionID = transactionID;
        this.createTime = new Timestamp(System.currentTimeMillis());
    }
    
    public int getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {  
        this.createTime = createTime;

    }
    
}
