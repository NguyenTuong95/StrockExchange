-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema stockexchange
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema stockexchange
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `stockexchange` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `stockexchange` ;

-- -----------------------------------------------------
-- Table `stockexchange`.`INFOR_ACCOUNT`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`INFOR_ACCOUNT` (
  `accountID` INT NOT NULL AUTO_INCREMENT,
  `fName` VARCHAR(20) NOT NULL,
  `lName` VARCHAR(20) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(15) NULL,
  `email` VARCHAR(30) NULL DEFAULT NULL,
  `username` VARCHAR(30) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  `isAdmin` INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`accountID`),
  UNIQUE INDEX `username` (`username` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockexchange`.`COMPANY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`COMPANY` (
  `companyID` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(15) NOT NULL,
  `email` VARCHAR(30) NOT NULL,
  `code` VARCHAR(10) NULL,
  PRIMARY KEY (`companyID`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockexchange`.`STOCK`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`STOCK` (
  `stockID` INT NOT NULL AUTO_INCREMENT,
  `companyID` INT NOT NULL,
  `code` VARCHAR(4) NULL,
  `lastPrice` DECIMAL(10,2) NULL,
  `minPrice` FLOAT NULL,
  `maxPrice` FLOAT NULL,
  `totalAmount` INT NOT NULL,
  PRIMARY KEY (`stockID`, `companyID`),
  INDEX `comany_id_idx` (`companyID` ASC) VISIBLE,
  CONSTRAINT `comany_id`
    FOREIGN KEY (`companyID`)
    REFERENCES `stockexchange`.`COMPANY` (`companyID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockexchange`.`STOCK_HISTORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`STOCK_HISTORY` (
  `stock_id` INT NOT NULL,
  `updateTime` DATETIME NOT NULL,
  `price` DECIMAL(10,2) NULL DEFAULT NULL,
  PRIMARY KEY (`stock_id`, `updateTime`),
  INDEX `fk_stockhistory_stock1_idx` (`stock_id` ASC) VISIBLE,
  CONSTRAINT `fk_stockhistory_stock1`
    FOREIGN KEY (`stock_id`)
    REFERENCES `stockexchange`.`STOCK` (`stockID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockexchange`.`TRADER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`TRADER` (
  `traderAccountID` INT NOT NULL,
  `balance` FLOAT NULL,
  PRIMARY KEY (`traderAccountID`),
  INDEX `fk_trader_account1_idx` (`traderAccountID` ASC) VISIBLE,
  CONSTRAINT `fk_trader_account`
    FOREIGN KEY (`traderAccountID`)
    REFERENCES `stockexchange`.`INFOR_ACCOUNT` (`accountID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT);


-- -----------------------------------------------------
-- Table `stockexchange`.`OWN`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`OWN` (
  `traderAccountID` INT NOT NULL COMMENT 'Một  trader có thể có nhiều cổ phiếu khác nhau\nMột loại cổ phiếu thì có thể sở hữu bởi nhiều trader',
  `stockID` INT NOT NULL COMMENT 'Một  trader có thể có nhiều cổ phiếu khác nhau\nMột loại cổ phiếu thì có thể sở hữu bởi nhiều trader',
  `amount` INT NULL DEFAULT 0,
  PRIMARY KEY (`stockID`, `traderAccountID`),
  INDEX `fk_own_stock1_idx` (`stockID` ASC) VISIBLE,
  INDEX `fk_own_trader2_idx` (`traderAccountID` ASC) VISIBLE,
  CONSTRAINT `fk_own_stock1`
    FOREIGN KEY (`stockID`)
    REFERENCES `stockexchange`.`STOCK` (`stockID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_own_trader2`
    FOREIGN KEY (`traderAccountID`)
    REFERENCES `stockexchange`.`TRADER` (`traderAccountID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockexchange`.`ORDER_BUY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`ORDER_BUY` (
  `orderBuyID` INT NOT NULL AUTO_INCREMENT,
  `tradeAccountID` INT NOT NULL,
  `stockID` INT NOT NULL,
  `amount` INT NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`orderBuyID`, `tradeAccountID`, `stockID`),
  INDEX `fk_Order_Buy_stock1_idx` (`stockID` ASC) VISIBLE,
  INDEX `fk_order_buy_trader1_idx` (`tradeAccountID` ASC) VISIBLE,
  CONSTRAINT `fk_order_Buy_stock`
    FOREIGN KEY (`stockID`)
    REFERENCES `stockexchange`.`STOCK` (`stockID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_order_buy_trader`
    FOREIGN KEY (`tradeAccountID`)
    REFERENCES `stockexchange`.`TRADER` (`traderAccountID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockexchange`.`ORDER_SELL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`ORDER_SELL` (
  `orderSellID` INT NOT NULL AUTO_INCREMENT,
  `stockID` INT NOT NULL,
  `traderAccountID` INT NOT NULL,
  `amount` INT NOT NULL,
  `price` FLOAT NOT NULL,
  PRIMARY KEY (`orderSellID`, `stockID`, `traderAccountID`),
  INDEX `fk_Order_Sell_stock1_idx` (`stockID` ASC) VISIBLE,
  INDEX `fk_order_sell_trader1_idx` (`traderAccountID` ASC) VISIBLE,
  CONSTRAINT `fk_order_sell_stock`
    FOREIGN KEY (`stockID`)
    REFERENCES `stockexchange`.`STOCK` (`stockID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_order_sell_trader`
    FOREIGN KEY (`traderAccountID`)
    REFERENCES `stockexchange`.`TRADER` (`traderAccountID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockexchange`.`TRANSACTION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`TRANSACTION` (
  `transactioID` INT NOT NULL AUTO_INCREMENT,
  `createTime` DATETIME NOT NULL,
  PRIMARY KEY (`transactioID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockexchange`.`TRANSACTION_DETAIL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`TRANSACTION_DETAIL` (
  `orderBuyID` INT NOT NULL,
  `transactionID` INT NOT NULL,
  `orderSellID` INT NOT NULL,
  `amount` INT NOT NULL,
  `price` DECIMAL(10,2) NULL,
  PRIMARY KEY (`orderBuyID`, `transactionID`, `orderSellID`),
  INDEX `fk_Order_Buy_has_Order_Sell_Order_Sell1_idx` (`orderSellID` ASC) VISIBLE,
  INDEX `fk_Order_Buy_has_Order_Sell_Order_Buy1_idx` (`orderBuyID` ASC) VISIBLE,
  INDEX `fk_transaction_detail_transaction1_idx` (`transactionID` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Buy_has_Order_Sell_Order_Buy1`
    FOREIGN KEY (`orderBuyID`)
    REFERENCES `stockexchange`.`ORDER_BUY` (`orderBuyID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_Order_Buy_has_Order_Sell_Order_Sell1`
    FOREIGN KEY (`orderSellID`)
    REFERENCES `stockexchange`.`ORDER_SELL` (`orderSellID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT,
  CONSTRAINT `fk_transaction_detail_transaction1`
    FOREIGN KEY (`transactionID`)
    REFERENCES `stockexchange`.`TRANSACTION` (`transactioID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `stockexchange`.`MIN_MAX_PRICE_HISTORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `stockexchange`.`MIN_MAX_PRICE_HISTORY` (
  `stockID` INT NOT NULL,
  `timeUpdate` DATETIME NOT NULL,
  `minPrice` DECIMAL(10,2) NULL DEFAULT NULL,
  `maxPrice` DECIMAL(10,2) NULL,
  PRIMARY KEY (`stockID`, `timeUpdate`),
  INDEX `fk_stockhistory_stock1_idx` (`stockID` ASC) VISIBLE,
  CONSTRAINT `fk_stockhistory_stock10`
    FOREIGN KEY (`stockID`)
    REFERENCES `stockexchange`.`STOCK` (`stockID`)
    ON DELETE CASCADE
    ON UPDATE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
