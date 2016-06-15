package life.database.dao;

import life.database.model.BankTransaction;

import java.util.List;

public interface BankTransactionDao{

  List<BankTransaction> getAllTransactions();

  void save(List<BankTransaction> result);
}
