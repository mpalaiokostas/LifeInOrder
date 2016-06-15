package life.web.controller;

import life.database.dao.BankTransactionDao;
import life.database.model.BankTransaction;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class BankingFacade {

  BankTransactionDao bankTransactionDao;

  @Inject
  public BankingFacade(BankTransactionDao bankTransactionDao) {
    this.bankTransactionDao = bankTransactionDao;
  }

  public void save(List<BankTransaction> bankTransactions) {
    bankTransactionDao.save(bankTransactions);
  }

}
