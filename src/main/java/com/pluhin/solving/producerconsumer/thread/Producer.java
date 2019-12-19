package com.pluhin.solving.producerconsumer.thread;

import com.pluhin.solving.producerconsumer.datastore.WriteOnlyDataStore;

public class Producer implements Runnable {

  private final WriteOnlyDataStore dataStore;
  private int counter = 0;

  public Producer(WriteOnlyDataStore dataStore) {
    this.dataStore = dataStore;
  }

  @Override
  public void run() {
    synchronized (dataStore) {
      while (true) {
        try {
          if (dataStore.isFull()) {
            dataStore.wait();
          } else {
            System.out.println(Thread.currentThread().getName() + "[PRODUCER] Producing value: " + counter);
            dataStore.write(counter++);
            dataStore.notify();
          }
          Thread.sleep(500);
        } catch (InterruptedException ex) {
          System.err.println(Thread.currentThread().getName() + "Thread was interrupted while sleeping");
          throw new RuntimeException(ex);
        }
      }
    }
  }
}
