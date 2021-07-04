package Model;

import java.util.Date;

public class Transaction {
    private int transactionID;
    private Date createTime;
    private static int cnt = 0;

    public Transaction(){
        this.createTime = new Date();
        this.transactionID = cnt++;
    }
    
    public int getTransactionID() {
        return this.transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    
}
