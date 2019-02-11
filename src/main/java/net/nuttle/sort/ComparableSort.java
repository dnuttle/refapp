package net.nuttle.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ComparableSort {

  public static void main(String[] args) {
    List<ComparableBean> beans = new ArrayList<>();
    beans.add(new ComparableBean(12));
    beans.add(new ComparableBean(1));
    beans.add(new ComparableBean(-8));
    beans.add(new ComparableBean(2));
    Collections.sort(beans);
    for (ComparableBean bean : beans) {
      System.out.println(bean.getSize());
    }
  }
}
