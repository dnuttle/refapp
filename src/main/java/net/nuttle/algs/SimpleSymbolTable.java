package net.nuttle.algs;

/**
 * The simplest of symbol tables
 * 1) The number of possible keys is known, and small
 * 2) They are all sequential, starting with 0
 * 3) Collisions are impossible
 * @author Dan
 *
 */
public class SimpleSymbolTable<T> {

  private Object[] values;
  
  public SimpleSymbolTable(int size) {
    values = new Object[size];
  }
  
  public static void main(String[] args) {
    SimpleSymbolTable<String> tbl = new SimpleSymbolTable<>(5);
    tbl.put(2,  "XYZ");
    tbl.put(2, "FINAL");
    System.out.println(tbl.get(2));
  }
  
  public void put(int key, T value) {
    values[key] = value;
  }
  
  public T get(int key) {
    return (T) values[key];
  }
}
