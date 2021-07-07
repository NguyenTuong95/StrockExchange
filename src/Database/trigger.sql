delimiter $$
Create trigger updateExchangeAmount
	after insert on transaction_detail
    for each row
    begin
		declare ordBuyID int;
        declare ordSellID int;
        declare amount int;
        set ordBuyID = new.orderBuyID;
        set ordSellID = new.orderSellID;
        set amount = new.amount;
        update order_buy
        set exchangeAmount = exchangeAmount + amount
        where 
			orderBuyID = ordBuyID;
		update order_sell
        set  exchangeAmount = exchangeAmount + amount
         where 
			orderSellID = ordSellID;
    end$$
    delimiter ;