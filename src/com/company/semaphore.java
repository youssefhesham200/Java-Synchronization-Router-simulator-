package com.company;

import java.io.IOException;

public class semaphore {
    public int value = 0;
    Network n = new Network();
    public  semaphore(int init){
       value = init;
   }
    public void Acquire(String nameDevice) throws InterruptedException, IOException {
        value--;
        if(value >= 0){
            for(int i=0; i<Network.TotalDevices.size(); i++){
                if(Network.TotalDevices.get(i).getDevice().equalsIgnoreCase(nameDevice)){
                    Network.arraived.add(nameDevice);
                    System.out.println("(" + nameDevice + " )" + "(" + Network.TotalDevices.get(i).getType() + ") arrived");
                    n.writer("(" + nameDevice + " )" + "(" + Network.TotalDevices.get(i).getType() + ") arrived");
                    break;
                }
            }
        }
        else{
            for(int i=0; i<Network.TotalDevices.size(); i++){
                if(Network.TotalDevices.get(i).getDevice().equalsIgnoreCase(nameDevice)){
                    System.out.println("(" + nameDevice + " )" + "(" + Network.TotalDevices.get(i).getType() + ") arrived and waiting");
                    n.writer("(" + nameDevice + " )" + "(" + Network.TotalDevices.get(i).getType() + ") arrived and waiting");
                    break;
                }}
                synchronized(this) {
                    this.wait();
                }
        }
    }

    public void release(String nameDevice) throws IOException {
       value++;
       if(value <= 0){
           synchronized(this) {
               this.notify();
               System.out.println("connection " + (Network.logout(nameDevice)) + " : " +  nameDevice + " logged out");
               n.writer("connection " + (Network.logout(nameDevice)) + ":" +  nameDevice + " logged out");
               for(int i=0; i<Network.arraived.size(); i++){
                   if(Network.arraived.get(i).equalsIgnoreCase(nameDevice)){
                       Network.arraived.set(i,null);
                       break;
                   }}
           }
       }
    }
}
