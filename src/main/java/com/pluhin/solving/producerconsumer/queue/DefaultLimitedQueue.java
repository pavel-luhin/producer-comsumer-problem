package com.pluhin.solving.producerconsumer.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultLimitedQueue<T> extends LinkedList<T> implements Queue<T> {

  private int limit;
  private AtomicInteger count = new AtomicInteger(0);

  public DefaultLimitedQueue(int limit) {
    this.limit = limit;
  }

  @Override
  public boolean add(T t) {
    if (count.get() == limit) {
      throw new IllegalStateException("Queue is full");
    }
    count.incrementAndGet();
    return super.add(t);
  }

  @Override
  public T poll() {
    if (count.get() == 0) {
      throw new IllegalStateException("Queue is empty");
    }
    count.decrementAndGet();
    return super.poll();
  }

  @Override
  public int size() {
    return count.get();
  }
}
