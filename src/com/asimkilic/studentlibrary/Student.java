package com.asimkilic.studentlibrary;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Student implements Runnable {
    private int id;
    private Book[] books; // referans tutucu kullanacağız
    private Random random;


    public Student(int id, Book[] books) {
        this.id = id;
        this.books = books;
        this.random = new Random();
    }


    @Override
    public void run() {
        while (true) {
            try {
                //Thread.sleep(10);
                int bookId = ThreadLocalRandom.current().nextInt(0, Constants.NUM_OF_BOOKS);
                //int bookId = random.nextInt(Constants.NUM_OF_BOOKS);
                books[bookId].read(this);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Student #" + id;
    }
}
