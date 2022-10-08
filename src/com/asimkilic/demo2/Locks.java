package com.asimkilic.demo2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {
    /*
    ReentrantLock
    - "synchronized" yaklaşımı ile aynı davranışı sergiler, ek olarak bir kaç ekstra özelliği vardır.
    - new ReentrantLock(boolean fairness);
    -- fairness parametresi true olarak set'lenirse en uzun süre 'wait' statement'ında olan thread lock'u alır.
    -- eğer fairness false setlenirse lock erişiminde bir sıralama yapılmaz.

    Note: kritik birşey yaparken unlock() metodunun çağırımı için en iyi yaklaşım try-catch-finally blok'u kullanmaktır.

     */

    private static int counter = 0; // main'den erişieceğim için static
    private static Lock lock = new ReentrantLock();  // main'den erişieceğim için static

    private static void increment() {
        lock.lock();   // t1   +
        try {
            for (int i = 0; i < 100_000; ++i) {
                counter++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(counter);

    }
}
