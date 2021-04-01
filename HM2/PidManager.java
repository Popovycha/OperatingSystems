package com.gmail.mybmcc22;

import java.util.HashMap;

//Author: Artem Popovych
//I did change my hm1, in order to make it easier to modify my code.
public class PidManager {
    public static final Integer MIN_PID = 300;
    public static final Integer MAX_PID = 5000;

    public Integer available = 0;
    public Integer notAvailable = 1;


    public HashMap<Integer, Integer> PID_map;

    public int allocateMAP(){
        //allocated map for certain capacity
        PID_map = new HashMap<Integer, Integer>(MAX_PID - MIN_PID + 1);
        //checks if system has enough resources to allocate a map of the capacity mentioned above
        for (int i = MIN_PID; i <= MAX_PID; i++) {
            PID_map.put(i, available);

        }
        return 1;
        //if returns integer "1" means hash map created, initialized and allocated.
    }

    public synchronized int allocatePID(){
        for (Integer i = MIN_PID; i <= MAX_PID; i++){      //traverses through the map to find available pid
            if (PID_map.get(i).equals(available)){        //once the available process identifier is found
                PID_map.put(i,notAvailable);             //the process identifier is updated from avialeble to unavialable
                return i;                               //returns the "new unavailable pid"
            }
        }
        return -1;
        //returs -1 if all process identifiers are in use.
    }

    public synchronized void releasePID(Integer i){
        if(i > MAX_PID || i < MIN_PID){
            //double checks if Pid is valid
            System.out.println("Error, invalid identifier");
        }
        PID_map.put(i,available);
        //if it is valid pid, it becomes released and the pid can be used by another process.
    }

}
