package com.gmail.mybmcc22;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Author: Artem Popovych
public class Main {

    public static void main(String[] args) {

        PidManager pids = new PidManager();

        if (pids.allocateMAP() == 1) {
            System.out.println("Successfully allocated and initialized a map of PIDs");
        } else {
            System.out.println("Failed to allocate and initialize a map of PIDs");
        }
        //ExecutorService that creates a thread pool of fixed number of threads.
        //newFixedThreadPool() method is used where we specify the number of threads in the pool.

        //Creating 2 pools with 10 threads for each.
        ExecutorService pool1 = Executors.newFixedThreadPool(10);
        //takes an argument and use it to add a new
        //object to the queue
        for (int i = 1; i < 11; i++) {
            MultiThread task1 = new MultiThread(i, (int) (Math.random() * 10 + 1), pids);
            pool1.execute(task1);
        }

        ExecutorService pool2 = Executors.newFixedThreadPool(10);
        for (int i = 1; i < 10; i++) {
            MultiThread task2 = new MultiThread(i, (int) (Math.random() * 10 + 1), pids);
            pool2.execute(task2);
        }

        //Closing down the ExecutorService
        pool1.shutdown();
        pool2.shutdown();


        while (!pool1.isTerminated()) {
        }
        System.out.println("Finished threads in Pool1");

        while (!pool2.isTerminated()) {
        }
        System.out.println("Finished threads in Pool2");
    }

}



