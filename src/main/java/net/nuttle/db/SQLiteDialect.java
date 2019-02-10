package net.nuttle.db;

import java.sql.Types;

import org.hibernate.dialect.Dialect;
import org.hibernate.dialect.identity.IdentityColumnSupport;

public class SQLiteDialect extends Dialect {
  
  public SQLiteDialect() {
    registerColumnType(Types.INTEGER, "integer");
    registerColumnType(Types.VARCHAR, "varchar");
    
  }
  
  @Override
  public IdentityColumnSupport getIdentityColumnSupport() {
    return new SQLiteIdentityColumnSupport(this);
  }
  
  @Override
  public boolean hasAlterTable() {
    return false;
  }
  
  @Override
  public boolean dropConstraints() {
    return false;
  }
  
  @Override
  public String getDropForeignKeyString() {
    return "";
  }
  
  @Override
  public String getAddForeignKeyConstraintString(String cn, String[] fk, String t, String[] pk, boolean rpk) {
    return "";
  }
  
  @Override
  public String getAddPrimaryKeyConstraintString(String constraintName) {
    return "";
  }
}
