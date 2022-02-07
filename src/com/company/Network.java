package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class Network{
    static Scanner input = new Scanner(System.in);
    public static ArrayList<device> TotalDevices = new ArrayList<>();
    public static ArrayList<String> arraived = new ArrayList<>();
    public static int N;

    public void writer(String s) throws IOException {
        File file = new File("log");
        FileOutputStream fos = new FileOutputStream(file, true);
        fos.write((s + "\n").getBytes());
        fos.close();
    }

    public static void main(String[] args) throws InterruptedException {
        String nt;
         int TC ;
        System.out.println("What is the number of WI-FI Connections?");
        N = input.nextInt();
        System.out.println("What is the number of devices Clients want to connect?");
        TC = input.nextInt();
        input.nextLine();

        for (int i = 0; i < TC; i++) {
            nt = input.nextLine();
            String [] k  = nt.split("\\s+");
            TotalDevices.add(new device(k[0], k[1]));
        }

        router r = new router();
        r.connection();
    }

    public static int order(String currentDevice){
        if(!Network.arraived.contains(currentDevice)){
            for(int i=0; i<Network.arraived.size(); i++){
                if(Network.arraived.get(i) == null){
                    Network.arraived.set(i,currentDevice);
                    break;
                }
            }
        }
        return Network.arraived.indexOf(currentDevice) + 1;
    }

    public static int logout(String currentDevice){
        return Network.arraived.indexOf(currentDevice) + 1;
    }
}