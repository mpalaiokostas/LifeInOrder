package com.fragmanos.util;

import com.fragmanos.database.dao.BankTransactionDao;
import com.fragmanos.database.model.BankTransaction;
import com.fragmanos.directory.DirectoryReader;
import com.fragmanos.file.CSVParser;

import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Named
public class BankTransactionUtil {

  List<BankTransaction> totalBankTransactions = new ArrayList<>();
  CSVParser csvParser = new CSVParser();
  DirectoryReader directoryReader = new DirectoryReader();
  BankTransactionDao bankTransactionDao;

  public List<BankTransaction> getBankTransactionsFromDirectory(String inputDirectory) throws ParseException, IOException {
    for (String file : directoryReader.csvScanner(inputDirectory)) {
      saveStatementToDB(inputDirectory, file);
    }
    return totalBankTransactions;
  }

  public void saveStatementToDB(String inputDirectory, String file) throws ParseException, IOException {
    List<BankTransaction> fileBankTransactionList = csvParser.getTransactions(inputDirectory + File.separator+ file);
    totalBankTransactions = filterUniqueBankTransactions(totalBankTransactions, fileBankTransactionList);
  }

  public void saveStatementToDB(List<BankTransaction> bankTransactionList){
    totalBankTransactions = filterUniqueBankTransactions(bankTransactionDao.findAllByOrderByTransactiondateDesc(),bankTransactionList);
  }

  private List<BankTransaction> filterUniqueBankTransactions(List<BankTransaction> totalBankTransactionList, List<BankTransaction> fileBankTransactionList) {
    if(!totalBankTransactionList.isEmpty()) {
      List<BankTransaction> localBankTransactionList = new ArrayList<BankTransaction>(totalBankTransactionList);
      localBankTransactionList.retainAll(fileBankTransactionList);
      for(BankTransaction bankTransaction : localBankTransactionList) {
        totalBankTransactionList.remove(bankTransaction);
      }
      totalBankTransactionList.addAll(fileBankTransactionList);
      return totalBankTransactionList;
    } else {
      totalBankTransactionList.addAll(fileBankTransactionList);
      return totalBankTransactionList;
    }
  }
}
