package com.pluhin.solving.producerconsumer.thread;

import com.pluhin.solving.producerconsumer.datastore.ReadOnlyDataStore;

public class Consumer implements Runnable {

  private final ReadOnlyDataStore dataStore;
  private final Object lock;

  public Consumer(ReadOnlyDataStore dataStore, Object lock) {
    this.dataStore = dataStore;
    this.lock = lock;
  }

  @Override
  public void run() {
    synchronized (lock) {
      while (true) {
        try {
          if (dataStore.isEmpty()) {
            lock.wait();
          } else {
            System.out.println(Thread.currentThread().getName() + "[CONSUMER] Retrieve value: " + dataStore.read());
            lock.notify();
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
