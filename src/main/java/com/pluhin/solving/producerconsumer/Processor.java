package com.pluhin.solving.producerconsumer;

import com.pluhin.solving.producerconsumer.datastore.DefaultDataStore;
import com.pluhin.solving.producerconsumer.datastore.ReadOnlyDataStore;
import com.pluhin.solving.producerconsumer.datastore.WriteOnlyDataStore;
import com.pluhin.solving.producerconsumer.thread.Consumer;
import com.pluhin.solving.producerconsumer.thread.Producer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Processor {

  public static void main(String[] args) throws InterruptedException {
    DefaultDataStore dataStore = new DefaultDataStore(10);

    final int consumers = 5;
    final int producers = 5;

    startProducer(dataStore, producers);
    Thread.sleep(2000);
    startConsumer(dataStore, consumers);
  }

  private static void startConsumer(ReadOnlyDataStore dataStore, int count) {
    ExecutorService executorService = Executors.newFixedThreadPool(count);
    for (int i = 0; i < count; i++) {
      executorService.submit(new Consumer(dataStore));
    }
  }

  private static void startProducer(WriteOnlyDataStore dataStore, int count) {
    ExecutorService executorService = Executors.newFixedThreadPool(count);
    for (int i = 0; i < count; i++) {
      executorService.submit(new Producer(dataStore));
    }
  }
}
