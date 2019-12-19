package com.pluhin.solving.producerconsumer.datastore;

public interface WriteOnlyDataStore {

  void write(Integer value);

  boolean isFull();
}
