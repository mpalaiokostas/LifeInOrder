package life.database.controller;


import life.database.dao.BankTransactionDao;
import life.database.manager.DBConnectionManager;
import life.database.model.BankTransaction;

import javax.inject.Inject;
import javax.inject.Named;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

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
    PreparedStatement statement = null;
    try {
      for (BankTransaction bankTransaction : bankTransactions) {
        statement = connection.prepareStatement(
            "insert into BANKTRANSACTION (TRANSACTIONDATE, DESCRIPTION, COST) " + "VALUES (?, ?, ?)"
        );
        statement.setDate(1, (Date) bankTransaction.getTransactiondate().toDate());
        statement.setString(2, bankTransaction.getDescription());
        statement.setDouble(3, bankTransaction.getCost());
        statement.executeUpdate();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    //TODO new JDbCTemplate().query("select NAME, VALUE from account where value < 0? ASdasd??Asd asdkhbnac> IO.acs", MyObject.class);
  }
}
