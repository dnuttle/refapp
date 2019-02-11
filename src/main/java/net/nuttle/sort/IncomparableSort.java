package net.nuttle.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IncomparableSort {

  public static void main(String[] args) {
    List<IncomparableBean> beans = new ArrayList<>();
    beans.add(new IncomparableBean(42));
    beans.add(new IncomparableBean(6));
    beans.add(new IncomparableBean(2));
    beans.add(new IncomparableBean(118));
    Collections.sort(beans, new IncomparableBeanComparator());
    for (IncomparableBean bean : beans) {
      System.out.println(bean.getSize());
    }
  }
}
