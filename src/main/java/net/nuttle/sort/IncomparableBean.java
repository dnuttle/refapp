package net.nuttle.sort;

/**
 * A class that does not implement the Comparable interface. Comparator to the rescue!
 * @author Dan
 *
 */
public final class IncomparableBean {

  private int size;
  
  public IncomparableBean(int size) {
    this.size = size;
  }
  
  public int getSize() {
    return size;
  }
}
