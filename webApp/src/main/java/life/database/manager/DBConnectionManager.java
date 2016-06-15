package life.database.manager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Component
public class DBConnectionManager {

  @Value("${db.name}")
  private final String dbms;
  @Value("${db.server}")
  private final String server;
  @Value("${db.port}")
  private final String port;
  @Value("${db.user}")
  private final String user;
  @Value("${db.pass}")
  private final String pass;

  public DBConnectionManager() {
    dbms = null;
    server = null;
    port = null;
    user = null;
    pass = null;
  }

  public DBConnectionManager(String dbms, String server, String port, String user, String pass) {
    this.dbms = dbms;
    this.server = server;
    this.port = port;
    this.user = user;
    this.pass = pass;
  }

  public Connection getConnection() {
    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", user);
    connectionProps.put("password", pass);

    try {
      if (dbms.equals("mysql")) {
        conn = DriverManager.getConnection("jdbc:" + dbms + "://" + server + ":" + port + "/", connectionProps);
      } else if (dbms.equals("derby")) {
        conn = DriverManager.getConnection("jdbc:" + dbms + ":" + dbms + ";create=true", connectionProps);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }

  public String getDbms() {
    return dbms;
  }

  public String getServer() {
    return server;
  }

  public String getPort() {
    return port;
  }

  public String getUser() {
    return user;
  }

  public String getPass() {
    return pass;
  }
}
