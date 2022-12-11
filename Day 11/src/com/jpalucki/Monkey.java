package com.jpalucki;

import java.util.LinkedList;
import java.util.function.IntUnaryOperator;

public class Monkey {

  private int counter = 0;
  private LinkedList<Integer> items;
  private final IntUnaryOperator getDestMonkeyNoFun;
  private final IntUnaryOperator getWorryLevelFun;

  public Monkey(
    LinkedList<Integer> items,
    IntUnaryOperator getDestMonkeyNoFun,
    IntUnaryOperator getWorryLevelFun
  ) {
    this.items = items;
    this.getDestMonkeyNoFun = getDestMonkeyNoFun;
    this.getWorryLevelFun = getWorryLevelFun;
  }

  public void addItem(Integer item) {
    items.add(item);
  }

  public Integer inspectItem() {
    if (items.size() == 0) {
      return null;
    }

    this.counter++;
    Integer item = items.pop();
    return getWorryLevelFun.applyAsInt(item) / 3;
  }

  public int getDestMonkeyNo(Integer item) {
    return this.getDestMonkeyNoFun.applyAsInt(item);
  }

  public int getCounter() {
    return this.counter;
  }

  public LinkedList<Integer> getItems() {
    return this.items;
  }
}
