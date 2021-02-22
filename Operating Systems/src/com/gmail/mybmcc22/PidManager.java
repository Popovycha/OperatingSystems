package com.gmail.mybmcc22;

import java.util.Arrays;
//Author: Artem Popovych
public class PidManager {
    //Range
    private static final int MIN_PID = 300;
    private static final int MAX_PID = 5000;

    //Declaration of a memory location
    private static int[] pids;

    //Method allocate the memory where to store pids
    public static void allocateMap() {
        pids = new int[MAX_PID - MIN_PID];
        Arrays.fill(pids, 0);
        System.out.println("Memory allocated successfully.");
    }

    //Method to allocate and return a pid.
    public static void allocatePID() {
        if (pids == null) {
            System.out.println("PID Manager is not initialized");
            return;
        }
        int pidNum = -1;

        for (int i = 0; i < pids.length; i++) {
            if (pids[i] == 0) {
                pids[i] = 1;
                pidNum = i + MIN_PID;
                break;
            }
        }
        if (pidNum == -1) {
            System.out.println("Unable to allocate PID");
            return;
        }
        System.out.println("Allocate PID: " + pidNum + ".");
    }

    //Method to release a pid.
    public static void releasePID(int pidNum) {
        if (pids == null) {
            System.out.println("PID Manager is not initialized");
            return;
        }
        if (pidNum < MIN_PID || pidNum > MAX_PID) {
            System.out.println("Given PID is out ");
        }
        int newPid = pidNum - MIN_PID;
        if (pids[newPid] == 0) {
            System.out.println("PID " + pidNum + " has been released ");
            return;
        }
        System.out.println("Releasing PID: " + pidNum);
        pids[newPid] = 0;
    }
}
