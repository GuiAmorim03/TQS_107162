package com.example;

import java.util.LinkedList;

public class TqsStack {

  private LinkedList<String> stack;
  private int max_len = 5;

  @SuppressWarnings({ "rawtypes", "unchecked" })
  public TqsStack() {
    this.stack = new LinkedList();
  }

  public void push(String x) {
    if (this.stack.size() == this.max_len) {
      throw new IllegalStateException("Stack is full");
    } else {
      this.stack.addLast(x);
    }
  }

  public String pop() {
    return this.stack.removeLast();
    // return "";
  }

  public String peek() {
    return this.stack.getLast();
    // return "";
  }

  public int size() {
    return this.stack.size();
    // return 100;
  }

  public boolean isEmpty() {
    return this.stack.isEmpty();
    // return false;
  }
}
