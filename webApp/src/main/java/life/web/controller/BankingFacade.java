package life.web.controller;

import life.database.dao.BankTransactionDao;

import javax.inject.Named;

@Named
public class BankingFacade {

  BankTransactionDao bankTransactionDao;

  public void saveToDB(String result) {
    bankTransactionDao.save(result);
  }

}
