package com.pluhin.solving.producerconsumer.thread;

import com.pluhin.solving.producerconsumer.datastore.ReadOnlyDataStore;

public class Consumer implements Runnable {

  private final ReadOnlyDataStore dataStore;

  public Consumer(ReadOnlyDataStore dataStore) {
    this.dataStore = dataStore;
  }

  @Override
  public void run() {
    synchronized (dataStore) {
      while (true) {
        try {
          if (dataStore.isEmpty()) {
            dataStore.wait();
          } else {
            System.out.println(Thread.currentThread().getName() + "[CONSUMER] Retrieve value: " + dataStore.read());
            dataStore.notify();
          }
          Thread.sleep(500);
        } catch (InterruptedException ex) {
          System.err.println(Thread.currentThread().getName() + "[CONSUMER] Thread was interrupted");
          throw new RuntimeException(ex);
        }
      }
    }
  }
}
