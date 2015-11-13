package com.fragmanos.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import com.fragmanos.database.model.BankTransaction;
import com.fragmanos.directory.DirectoryReader;
import com.fragmanos.file.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author fragkakise on 28/10/2015.
 */
public class TransactionController {

  private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
  CSVReader csvReader = new CSVReader();
  DirectoryReader directoryReader = new DirectoryReader();

  public List<BankTransaction> getBankTransactionsFromDirectory(String inputDirectory) throws ParseException, IOException {
    List<BankTransaction> totalBankTransactions = new ArrayList<BankTransaction>();
    for (String file : directoryReader.csvScanner(inputDirectory)) {
      List<BankTransaction> fileBankTransactionList = csvReader.readCSV(inputDirectory + file);
//      filterUniqueBankTransactions(totalBankTransactions, fileBankTransactionList);
      totalBankTransactions.addAll(fileBankTransactionList);
    }
    return totalBankTransactions;
  }

  //TODO FIX filtering to keep ONLY unique bankTransactions.
  private List<BankTransaction> filterUniqueBankTransactions(List<BankTransaction> totalBankTransactionList, List<BankTransaction> fileBankTransactionList) {
    if (!totalBankTransactionList.isEmpty()) {
      List<BankTransaction> localBankTransactionList = new ArrayList<BankTransaction>();
      for (BankTransaction bankTransactionFromTotal : totalBankTransactionList) {
        for (BankTransaction bankTransactionFromFile : fileBankTransactionList) {
          if (!bankTransactionFromFile.equals(bankTransactionFromTotal)) {
            localBankTransactionList.add(bankTransactionFromFile);
          } else { log.warn("Duplicate Bank Transaction: ", bankTransactionFromFile); }
        }
      }
      return localBankTransactionList;
    } else {
      totalBankTransactionList.addAll(fileBankTransactionList);
      return totalBankTransactionList;
    }
  }

}