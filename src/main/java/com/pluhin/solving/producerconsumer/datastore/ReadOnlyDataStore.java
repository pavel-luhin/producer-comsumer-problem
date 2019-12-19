package com.pluhin.solving.producerconsumer.datastore;

public interface ReadOnlyDataStore {

  Integer read();

  boolean isEmpty();
}
