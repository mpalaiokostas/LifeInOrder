package life.web.controller;

import life.database.dao.BankTransactionDao;
import life.database.dao.MonthStatDao;
import life.database.model.BankTransaction;
import life.database.model.MonthStat;
import life.util.BankTransactionUtil;
import org.joda.time.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Named;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Named
public class BankService implements BankInterface {

  private static final Logger log = LoggerFactory.getLogger(BankService.class);

  private MonthStatDao monthStatDao;
  private BankTransactionDao bankTransactionDao;
  private BankTransactionUtil bankTransactionUtil;

  public BankService() {
  }

  @Autowired
  public BankService(MonthStatDao monthStatDao, BankTransactionDao bankTransactionDao) {
    this.monthStatDao = monthStatDao;
    this.bankTransactionDao = bankTransactionDao;
    bankTransactionUtil = new BankTransactionUtil();
  }

  @Override
  public List<TableObject> getTableObjects() {
    List<BankTransaction> allBankTransactions = bankTransactionDao.getAllTransactions();
    return bankTransactionUtil.getTableObjectList(allBankTransactions);
  }

//  @Override
//  public void populateDatabase() {
//    DirectoryReader directoryReader = new DirectoryReader();
//    BankTransactionUtil bankTransactionUtil = new BankTransactionUtil();
//
//    if(!directoryReader.isDirectoryEmpty(getInputDirectory())) {
//      List<BankTransaction> bankTransactionsFromDirectory = getBankTransactionsFromDirectory(bankTransactionUtil);
//      for(BankTransaction bankTransaction : bankTransactionsFromDirectory) {
//        bankTransactionDao.save(bankTransaction);
//        setMonthStat(bankTransaction);
//      }
//    }
//  }

  @Override
  public List<TableObject> getMonthlyExpensesList(int monthNumber, int yearNumber) {
    List<BankTransaction> bankTransactionList = new ArrayList<BankTransaction>();
    for (BankTransaction bankTransaction : bankTransactionDao.getAllTransactions()) {
      if ((bankTransaction.getTransactiondate().getMonthOfYear() == monthNumber) &&
            (bankTransaction.getTransactiondate().getYear() == yearNumber) &&
              (bankTransaction.getCost() < 0)) {
        bankTransactionList.add(bankTransaction);
      }
    }
    return bankTransactionUtil.getTableObjectList(bankTransactionList);
  }

  @Override
  public List<TableObject> getMonthlyIncomeList(int monthNumber, int yearNumber) {
    List<BankTransaction> bankTransactionList = new ArrayList<BankTransaction>();
    for (BankTransaction bankTransaction : bankTransactionDao.getAllTransactions()) {
      if ((bankTransaction.getTransactiondate().getMonthOfYear() == monthNumber) &&
              (bankTransaction.getTransactiondate().getYear() == yearNumber) &&
              (bankTransaction.getCost() > 0)) {
        bankTransactionList.add(bankTransaction);
      }
    }
    return bankTransactionUtil.getTableObjectList(bankTransactionList);
  }

//  @Override
//  public void populateDatabase(List<BankTransaction> bankTransactionList) {
//    for(BankTransaction bankTransaction : bankTransactionList) {
//      if(!bankTransactionDao.findByTransactiondateAndDescriptionAndCost(
//        bankTransaction.getTransactiondate(),
//        bankTransaction.getDescription(),
//        bankTransaction.getCost()
//      ).equals(bankTransaction)) {
//        bankTransactionDao.save(bankTransaction);
//      } else {
//        log.info("Bank transaction existed in database: " + bankTransaction.toString());
//      }
//      setMonthStat(bankTransaction);
//    }
//  }

  @Override
  public List<BankTransaction> getDbBankTransactions() {
    return bankTransactionDao.getAllTransactions();
  }

  public void setMonthStat(BankTransaction bankTransaction) {
    DecimalFormat decimalFormat = new DecimalFormat("#.00");
    double income = 0;
    double expense = 0;
    double profit;
    YearMonth yearMonth = new YearMonth(bankTransaction.getTransactiondate().getYear(),
                                         bankTransaction.getTransactiondate().getMonthOfYear());
    if(bankTransaction.getCost() > 0) {
      income = bankTransaction.getCost();
    } else {
      expense = bankTransaction.getCost();
    }
    profit = income + expense;

    if(monthStatDao.findAll().isEmpty()) {
//      monthStatDao.save(new MonthStat(yearMonth, income, expense, profit));
    } else {
      MonthStat monthStatForUpdate = monthStatDao.findByYearMonth(yearMonth);
      if(monthStatForUpdate == null){
//        monthStatDao.save(new MonthStat(yearMonth, income, expense, profit));
      } else {
        // TODO increase the accuracy of the below calculations, avoid double -> string -> double
        monthStatForUpdate.setIncome(Double.parseDouble(decimalFormat.format(income + monthStatForUpdate.getIncome())));
        monthStatForUpdate.setExpense(Double.parseDouble(decimalFormat.format(expense + monthStatForUpdate.getExpense())));
        monthStatForUpdate.setProfit(Double.parseDouble(decimalFormat.format(profit + monthStatForUpdate.getProfit())));
//        monthStatDao.save(monthStatForUpdate);
      }
    }
  }

}
