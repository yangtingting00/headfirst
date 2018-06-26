package com.example.headfirst.candyStore;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class GumballMonitorTestDrive {
    public static void main(String[] args) {
        String[] location = {"rmi://127.0.0.1/gumballMachine"};
        GumballMonitor[] monitors = new GumballMonitor[location.length];
        for (int i = 0; i < location.length; i++) {
            try {
                GumballMachineRemote machineRemote = (GumballMachineRemote)Naming.lookup(location[i]);
                monitors[i] = new GumballMonitor(machineRemote);
                System.out.println(monitors[i]);
            } catch (NotBoundException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < monitors.length; i++) {
            monitors[i].report();
        }
    }
}
