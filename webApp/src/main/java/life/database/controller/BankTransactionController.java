package life.database.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

import life.database.dao.BankTransactionDao;
import life.database.manager.DBConnectionManager;
import life.database.model.BankTransaction;

@Named
public class BankTransactionController implements BankTransactionDao {

  private DBConnectionManager connectionManager;
  private Connection connection;

  @Inject
  public BankTransactionController(DBConnectionManager connectionManager) {
    this.connectionManager = connectionManager;
    connection = connectionManager.getConnection();
  }

  @Override
  public List<BankTransaction> getAllTransactions() {
    return null;
  }

  @Override
  public void save(List<BankTransaction> bankTransactions) {
    try {
      connection.setAutoCommit(false);
      PreparedStatement statement = connection.prepareStatement(
        "insert into BANKTRANSACTION (TRANSACTIONDATE, DESCRIPTION, COST) " + "VALUES (?, ?, ?)"
      );
      for(BankTransaction bankTransaction : bankTransactions) {
        statement.setDate(1, new java.sql.Date(bankTransaction.getTransactiondate().toDateTimeAtStartOfDay().getMillis()));
        statement.setString(2, bankTransaction.getDescription());
        statement.setDouble(3, bankTransaction.getCost());
        statement.executeUpdate();
        connection.commit();
      }
    } catch(SQLException e) {
      e.printStackTrace();
    }
  }

}
