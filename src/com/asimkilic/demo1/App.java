package com.asimkilic.demo1;

// this = t2
// t1 =  wait - p1

class Process {
    public void produce() throws InterruptedException {
        synchronized (this) {     // t1   lock1 boş
            System.out.println("Running the produce method...");
            wait(); // release
            System.out.println("Again in the producer method..");
        }
    }


    public void consume() throws InterruptedException {
        Thread.sleep(1000); // t3  -lock
        synchronized (this){
            System.out.println("Consume method is executed...");
            notify();
        }
    }
}

public class App {
    public static void main(String[] args) {
        Process process = new Process();
        Thread t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 =  new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    process.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        t1.start();
        t2.start();
    }
}
