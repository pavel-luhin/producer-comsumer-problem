package com.pluhin.solving.producerconsumer.datastore;

import com.pluhin.solving.producerconsumer.queue.DefaultLimitedQueue;
import java.util.Queue;

public class DefaultDataStore implements ReadOnlyDataStore, WriteOnlyDataStore {

  private final Queue<Integer> store;
  private final int limit;

  public DefaultDataStore(int limit) {
    this.limit = limit;
    this.store = new DefaultLimitedQueue<>(limit);
  }

  @Override
  public Integer read() {
    return this.store.poll();
  }

  @Override
  public boolean isEmpty() {
    return store.isEmpty();
  }

  @Override
  public void write(Integer value) {
    this.store.add(value);
  }

  @Override
  public boolean isFull() {
    return store.size() == limit;
  }
}
