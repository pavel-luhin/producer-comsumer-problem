package com.pluhin.solving.producerconsumer.thread;

import com.pluhin.solving.producerconsumer.datastore.WriteOnlyDataStore;

public class Producer implements Runnable {

  private int counter = 0;

  private final WriteOnlyDataStore dataStore;
  private final Object lock;

  public Producer(WriteOnlyDataStore dataStore, Object lock) {
    this.dataStore = dataStore;
    this.lock = lock;
  }

  @Override
  public void run() {
    synchronized (lock) {
      while (true) {
        try {
          if (dataStore.isFull()) {
            lock.wait();
          } else {
            System.out.println(Thread.currentThread().getName() + "[PRODUCER] Producing value: " + counter);
            dataStore.write(counter++);
            lock.notify();
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
