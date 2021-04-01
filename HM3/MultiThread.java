package com.gmail.mybmcc22;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.currentThread;
//Author: Artem Popovych
public class MultiThread implements Runnable {
    public int threadID;
    private int threadSleepTime;
    private PidManager pids;

    MultiThread(int threadID, int threadSleepTime, PidManager pids) {
        this.threadID = threadID;
        this.threadSleepTime = threadSleepTime;
        this.pids = pids;
        System.out.println("Creating Thread: " + threadID);
    }

    @Override
    public void run() {

        int new_pid;
        System.out.println("Running Thread: " + currentThread().getName());
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        //We in try catch block to ensure that lock released in cases of any exceptions to occur
        lock1.lock();
        try {
            //show running thread
            new_pid = pids.allocatePID();
            //assigned our pid object from the Main class
            while (new_pid == -1) {
                System.out.println("All PIDs are in use");
                //checks if each process get own PID.
                new_pid = pids.allocatePID();
            }
        }
        finally {
            lock1.unlock(); //lock #1 unlocks
        }


        currentThread().setName(Integer.toString(new_pid));
        //PID  was assigned to the thread
        System.out.println("PID-" + new_pid + " was successfully allocated");


        try {
            Thread.sleep(threadSleepTime);

        } catch (InterruptedException e) {
            System.out.println("Thread " + currentThread().getName() + " interrupted.");
        }

        Integer pid_to_release = Integer.valueOf(currentThread().getName());
        System.out.println("pid " + pid_to_release);
        //current thread is assigned to the new variable pid_to_release


        //Sets up the same process for lock2
        lock2.lock();
        try {
            pids.releasePID(pid_to_release);
            //calls the release_pid method from the Main class to get released
        }
        finally { // finally used if we want to use unlock after return statement
            lock2.unlock();
        }
        System.out.println("PID-" + pid_to_release + " was successfully released");
        System.out.println("Thread " + currentThread().getName() + " exiting.");
        //prints out the existing thread
    }


}
