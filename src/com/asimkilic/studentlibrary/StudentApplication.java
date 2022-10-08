package com.asimkilic.studentlibrary;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StudentApplication {
    public static void main(String[] args) {
        Student[] students = null;
        Book[] books = null;
        ExecutorService executor = Executors.newFixedThreadPool(Constants.NUM_OF_STUDENTS);
        try {
            books = new Book[Constants.NUM_OF_BOOKS];
            students = new Student[Constants.NUM_OF_STUDENTS];

            // initialize books
            for (int i = 0; i < Constants.NUM_OF_BOOKS; ++i) {
                books[i] = new Book(i + 1);
            }
            // initialize students
            for (int i = 0; i < Constants.NUM_OF_STUDENTS; ++i) {
                students[i] = new Student(i + 1, books);
                executor.execute(students[i]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }
}
