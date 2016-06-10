package life.database;


import life.database.dao.BankTransactionDao;
import life.database.model.BankTransaction;

import javax.inject.Named;
import java.util.List;

@Named
public class BankTransactionDaoImpl implements BankTransactionDao {


  @Override
  public List<BankTransaction> findAllByOrderByTransactiondateDesc() {
    return null;
  }

  @Override
  public void save(String result) {
    //TODO new JDbCTemplate().query("select NAME, VALUE from account where value < 0? ASdasd??Asd asdkhbnac> IO.acs", MyObject.class);
  }
}
