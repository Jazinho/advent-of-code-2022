package com.jpalucki;

import java.util.ArrayList;

public class Day20Main {

  public static void main(String[] args) {
    String INPUT = InputProvider.getInput();
    String[] inputs = INPUT.split("\n");
    ArrayList<Element> numbers = new ArrayList<>();

    for (String numberStr : inputs) {
      Integer val = Integer.valueOf(numberStr);
      numbers.add(new Element(val));
    }

    int listSize = numbers.size();
    int sourceIndex = 0;
    for (int i = 0; i + sourceIndex < listSize; i++) {
      if (numbers.get(sourceIndex).isVisited()) {
        i = i - 2;
        sourceIndex++;
        continue;
      }

      Element el = numbers.remove(sourceIndex);
      el.setVisited(true);

      Integer shift = el.getValue() % listSize;
      int newIndex = sourceIndex + shift;
      if (newIndex >= listSize) {
        newIndex = newIndex - listSize + 1;//exp +1
      }
      if (newIndex <= 0) {
        newIndex = newIndex + listSize - 1;//exp -1 because list size is temporarily 6 after popping, not 7
      }

      numbers.add(newIndex, el);
    }

    // sum of 1000th, 2000th and 3000th elements after '0' element
    for (int i = 0; i < numbers.size(); i++) {
      if (numbers.get(i).getValue() == 0) {
        System.out.println(i);
        Integer sum = numbers.get((i + 1000) % numbers.size()).getValue() +
          numbers.get((i + 2000) % numbers.size()).getValue() + numbers.get((i + 3000) % numbers.size()).getValue();
        System.out.println(sum);
        break;
      }
    }
  }

  public static class Element {
    private Integer value;
    private boolean visited = false;

    public Element(Integer value) {
      this.value = value;
    }
    public Integer getValue() {
      return value;
    }
    public void setValue(Integer value) {
      this.value = value;
    }
    public boolean isVisited() {
      return visited;
    }
    public void setVisited(boolean visited) {
      this.visited = visited;
    }
  }

}
