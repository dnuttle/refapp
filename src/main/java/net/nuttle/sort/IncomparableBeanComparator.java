package net.nuttle.sort;

import java.util.Comparator;

public class IncomparableBeanComparator implements Comparator<IncomparableBean> {

  public int compare(IncomparableBean one, IncomparableBean two) {
    if (one.getSize() == two.getSize()) {
      return 0;
    }
    return one.getSize() < two.getSize() ? -1 : 1;
  }
}
