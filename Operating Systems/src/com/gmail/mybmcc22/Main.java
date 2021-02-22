package com.gmail.mybmcc22;

public class Main {

    public static void main(String[] args) {
	// Calling each method to make sure it's working.
        PidManager.allocateMap();
    //Allocation of the pid
        PidManager.allocatePID();
        PidManager.allocatePID();
     //Releasing the pid
        PidManager.releasePID(300);

        PidManager.allocatePID();
        PidManager.allocatePID();

    }
}
