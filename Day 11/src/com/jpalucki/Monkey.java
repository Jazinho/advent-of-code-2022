package com.jpalucki;

import java.math.BigInteger;
import java.util.LinkedList;

public class Monkey {

  private int counter = 0;
  private LinkedList<BigInteger> items;
  BigInteger divisor;
  private final int ifMonkey;
  private final int elseMonkey;
  private final String operationSign;
  private final BigInteger operationFactor;

  public Monkey(
    LinkedList<BigInteger> items,
    BigInteger divisor,
    int ifMonkey,
    int elseMonkey,
    String operationSign,
    String operationFactor
  ) {
    this.items = items;
    this.divisor = divisor;
    this.ifMonkey = ifMonkey;
    this.elseMonkey = elseMonkey;
    this.operationSign = operationSign;
    this.operationFactor = operationFactor.equals("old")
                           ? BigInteger.ZERO
                           : BigInteger.valueOf(Long.parseLong(operationFactor));
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
    BigInteger factor = operationFactor.equals(BigInteger.ZERO) ? item : operationFactor;
    return operationSign.equals("*") ? item.multiply(factor) : item.add(factor);
  }

  public int getDestMonkeyNo(BigInteger item) {
    return item.divideAndRemainder(this.divisor)[1].equals(BigInteger.ZERO) ? ifMonkey : elseMonkey;
  }

  public int getCounter() {
    return this.counter;
  }
}
