package com.jpalucki;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.function.Function;

public class Monkey {

  private int counter = 0;
  private LinkedList<BigInteger> items;
  private final Function<BigInteger, Integer> getDestMonkeyNoFun;
  private final Function<BigInteger, BigInteger> getWorryLevelFun;

  public Monkey(
    LinkedList<BigInteger> items,
    Function<BigInteger, Integer> getDestMonkeyNoFun,
    Function<BigInteger, BigInteger> getWorryLevelFun
  ) {
    this.items = items;
    this.getDestMonkeyNoFun = getDestMonkeyNoFun;
    this.getWorryLevelFun = getWorryLevelFun;
  }

  public void addItem(BigInteger item) {
    items.add(item);
  }

  public BigInteger inspectItem() {
    if (items.size() == 0) {
      return null;
    }

    this.counter++;
    BigInteger item = items.pop();
    return getWorryLevelFun.apply(item);
  }

  public int getDestMonkeyNo(BigInteger item) {
    return this.getDestMonkeyNoFun.apply(item);
  }

  public int getCounter() {
    return this.counter;
  }
}
