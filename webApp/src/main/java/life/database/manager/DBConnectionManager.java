package life.database.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBConnectionManager {

  @Value("${jdbc.driver}")
  private final String driver;
  @Value("${jdbc.url}")
  private final String url;
  @Value("${jdbc.user}")
  private final String user;
  @Value("${jdbc.pass}")
  private final String pass;

  private Connection conn;

  public DBConnectionManager() {
    driver = null;
    url = null;
    user = null;
    pass = null;
  }

  public Connection getConnection(){
    try {
    Class.forName(driver);
      conn = DriverManager.getConnection(url, user, pass);
    } catch(SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return conn;
  }

  public String getDriver() {
    return driver;
  }

  public String getUrl() {
    return url;
  }

  public String getUser() {
    return user;
  }

  public String getPass() {
    return pass;
  }
}
