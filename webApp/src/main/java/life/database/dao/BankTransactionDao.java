package life.database.dao;

import java.util.List;

import life.database.model.BankTransaction;

public interface BankTransactionDao{

  List<BankTransaction> getAllTransactions();

  void save(List<BankTransaction> result);
}
