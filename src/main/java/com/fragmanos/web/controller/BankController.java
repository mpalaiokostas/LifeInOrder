package com.fragmanos.web.controller;

import com.fragmanos.controller.TransactionController;
import com.fragmanos.database.dao.BankTransactionDao;
import com.fragmanos.database.model.BankTransaction;
import com.fragmanos.directory.DirectoryReader;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class BankController {

    @Value("${transactions.directory}")
    String TRANSACTIONS_DIRECTORY;

    @Autowired
    BankTransactionDao bankTransactionDao;

    private static final Logger log = LoggerFactory.getLogger(BankController.class);

    @PostConstruct
    public void populateDatabase(){
        DirectoryReader directoryReader = new DirectoryReader();
        TransactionController transactionController = new TransactionController();
        String input_directory = System.getProperty("user.dir") + "\\input_files\\";

        if (!directoryReader.isDirectoryEmpty(input_directory)) {
            List<BankTransaction> bankTransactionsFromDirectory = new ArrayList<BankTransaction>();
            try {
                bankTransactionsFromDirectory = transactionController.getBankTransactionsFromDirectory(input_directory);
            } catch (ParseException e) {
                log.error("ParseException while parsing directory",e);
            } catch (IOException e) {
                log.error("IO exception while parsing directory", e);
            }
            for (BankTransaction bt : bankTransactionsFromDirectory) {
                bankTransactionDao.saveBankTransaction(bt);
            }
        }

    }

    @RequestMapping("/api/bank/allTransactions")
    public List<TableObject> fillTable() {
        List<TableObject> dataForTable = new ArrayList<TableObject>();
        List<BankTransaction> allBankTransactions = bankTransactionDao.findAllBankTransactions();
        DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
        for (BankTransaction bankTransaction : allBankTransactions) {
            dataForTable.add(new TableObject(
                    dtf.print(bankTransaction.getTransactiondate()),
                    bankTransaction.getDescription(),
                    bankTransaction.getCost()
            ));
        }
        return dataForTable;
    }

    @RequestMapping("/transaction")
    public List<BankTransaction> transaction() {
        return getBankTransactions();
    }

    public List<BankTransaction> getBankTransactions() {
        List<BankTransaction> transactionList = new ArrayList<BankTransaction>();
        List<BankTransaction> allBankTransactions = bankTransactionDao.findAllBankTransactions();
        for(BankTransaction myTransaction : allBankTransactions){
//            printTransactionStatement(myTransaction);
            transactionList.add(myTransaction);
        }
        return transactionList;
    }

    private static void printTransactionStatement(BankTransaction myTransaction) {
        StringBuilder sb = new StringBuilder("");
        String line = "\n===========================";
        sb.append("\nTransaction")
          .append(line)
          .append("\nDate: ").append(myTransaction.getTransactiondate())
          .append("\nDescription: ").append(myTransaction.getDescription())
          .append("\nCost: ").append(myTransaction.getCost())
          .append(line);
        System.out.println(sb);
    }

    class TableObject {
        String date;
        String description;
        Double cost;

        public TableObject(String date, String description, Double cost) {
            this.date = date;
            this.description = description;
            this.cost = cost;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Double getCost() {
            return cost;
        }

        public void setCost(Double cost) {
            this.cost = cost;
        }
    }

}