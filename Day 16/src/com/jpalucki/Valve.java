package com.jpalucki;

import java.util.Set;

public class Valve {
  private final String id;
  private boolean isOpen = false;
  private final Integer rate;
  private final Set<String> pathsTo;

  public Valve(String id, Integer rate, Set<String> pathsTo) {
    this.id = id;
    this.rate = rate;
    this.pathsTo = pathsTo;
  }

  private Valve(String id, boolean isOpen, Integer rate, Set<String> pathsTo) {
    this.id = id;
    this.isOpen = isOpen;
    this.rate = rate;
    this.pathsTo = pathsTo;
  }

  public String getId() {
    return id;
  }
  public Integer getRate() {
    return rate;
  }
  public Set<String> getPathsTo() {
    return pathsTo;
  }

  public boolean isOpen() {
    return isOpen;
  }

  public void setOpen(boolean open) {
    isOpen = open;
  }

  public Valve copyOf() {
    return new Valve(this.getId(), this.isOpen(), this.getRate(), this.getPathsTo());
  }
}
