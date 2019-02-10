package net.nuttle.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SQLiteUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(SQLiteUtil.class);
  static final String DB_URL = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/db.sqlite"; 

  private SQLiteUtil() {}
  
  public static void main(String[] args) {
    SQLiteUtil.createTable();
  }
  
  public static void createTable() {
    try (Connection conn = DriverManager.getConnection(DB_URL);
      Statement stmt = conn.createStatement()) {
      if (conn != null) {
        DatabaseMetaData meta = conn.getMetaData();
        String sql = "CREATE TABLE IF NOT EXISTS customer (\n"
            + "id integer PRIMARY KEY AUTOINCREMENT, \n"
            + "name text NOT NULL, \n"
            + "city text NOT NULL, \n"
            + "state text NOT NULL, \n"
            + "postal_code text NOT NULL"
            + ");";
        stmt.execute(sql);
      }
    } catch (SQLException e) {
      LOGGER.error("Error creating table", e);
    }
  }
  
  public static void addCustomer(int id, String name, String city, String state, String postalCode) {
    String sql = "INSERT INTO customer (id, name, city, state, postal_code) \n"
        + " VALUES (?, ?, ?, ?, ?);";
    try (Connection conn = DriverManager.getConnection(DB_URL);
        PreparedStatement ps = conn.prepareStatement(sql)) {
      ps.setInt(CustomerFields.ID.ordinal(), id);
      ps.setString(CustomerFields.NAME.ordinal(), name);
      ps.setString(CustomerFields.CITY.ordinal(), city);
      ps.setString(CustomerFields.STATE.ordinal(), state);
      ps.setString(CustomerFields.POSTAL_CODE.ordinal(), postalCode);
      ps.executeUpdate();
    } catch (SQLException e) {
      LOGGER.error("Error adding customer");
    }
  }
  
  
  public static enum CustomerFields {
    ID,
    NAME,
    CITY,
    STATE,
    POSTAL_CODE
  }
}
