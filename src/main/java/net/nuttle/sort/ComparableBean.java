package net.nuttle.sort;

public class ComparableBean implements Comparable<ComparableBean> {

  private int size;
  
  public ComparableBean(int size) {
    this.size = size;
  }
  
  public int getSize() {
    return size;
  }
  
  @Override
  public int compareTo(ComparableBean other) {
    if (this.size == other.size) {
      return 0;
    }
    return this.size < other.size ? -1 : 1;
  }
  
  
}
