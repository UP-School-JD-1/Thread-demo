package com.asimkilic.demo3;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Worker {
    private Lock myLock = new ReentrantLock();
    private Condition condition = myLock.newCondition();

    public void produce() throws InterruptedException {
        myLock.lock();
        System.out.println("Producer method...");
        condition.await(); // wait'e benzer bir yapısı var.
        System.out.println("Again the producer method..");
        myLock.unlock();
    }

    public void consume() throws InterruptedException {
        Thread.sleep(2000); // lock,'ı produce methodnun almasından emin olmak için bekletiyoruz.
        myLock.lock();
        System.out.println("Consumer method..");
        Thread.sleep(3000);
        condition.signal();  // notify'a benzer bir yapısı var.
        myLock.unlock();
    }
}

public class Application2 {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.produce();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.consume();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


/*
- Locks and synchronized blocks
- Reentrant lock daha önce gördüğümüz synchronized blok'ları ile temel olarak aynı davranışı sergiler. (Bir kaç ekstra özellikleri ile beraber)
- lock fair olarak kullanabiliriz. synchronized blokl'larda thread'ler default olarak unfair'dir.( beklemesi süresi en uzun olan thread gibi sıralaması yok)
- Reentrant lock'lar ile bırakılan lock'u bekleyen threadlerin listesini alabiliriz.
- synchronized blok'ların daha basit bir yazımı vardır, try catch yapısı kurmamız gerekmez
 */
