package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
public class router implements Runnable{
    Random random = new Random();
    semaphore s;
    Network n = new Network();
    public ArrayList<Thread> threads = new ArrayList<>();

public router(){
    s = new semaphore(Network.N);
}
public void connection() throws InterruptedException {
    for(int j=0; j<Network.TotalDevices.size(); j++){
        Thread th = new Thread(this, Network.TotalDevices.get(j).getDevice());
        th.start();
        threads.add(th);
    }
    for (Thread thread : threads) {
        thread.join();
    }
}
@Override
public void run(){
    try {
        String currentDevice = Thread.currentThread().getName();
        s.Acquire(currentDevice);
        String k = "connection "+ (Network.order(currentDevice)) + ": " + currentDevice;
        System.out.println(k + " occupied");
        n.writer(k + " occupied");
        Thread.sleep(1200);
        System.out.println(k + " login");
        n.writer(k + " login");
        System.out.println(k + " performs online activity");
        n.writer(k + " performs online activity");
        Thread.sleep((random.nextInt(5)*1200));
        s.release(currentDevice);

    } catch (InterruptedException | IOException e) {
        e.printStackTrace();
    }
}
}
