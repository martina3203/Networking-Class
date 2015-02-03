/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penetrationtest;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Aaron
 */
public class PenetrationTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        portTester first =  new portTester("192.168.2.9",6000,6249);
        portTester second = new portTester("192.168.2.9",6250,6500);
        portTester third = new portTester("192.168.2.9",30000,30249);
        portTester fourth = new portTester("192.168.2.9",6750,6999);
        portTester fifth = new portTester("192.168.2.9",20000,20249);
        portTester sixth = new portTester("192.168.2.9",7250,7499);
        portTester seven = new portTester("192.168.2.9",7500,7749);
        portTester eight = new portTester("192.168.2.9",10000,10249);
        Thread firstThread = new Thread(first);
        Thread secondThread = new Thread(second);
        Thread thirdThread = new Thread(third);
        Thread fourthThread = new Thread(fourth);
        Thread fifthThread = new Thread(fifth);
        Thread sixthThread = new Thread(sixth);
        Thread seventhThread = new Thread(seven);
        Thread eighthThread = new Thread(eight);
        //Start all threads
        firstThread.start();
        secondThread.start();
        thirdThread.start();
        fourthThread.start();
        fifthThread.start();
        sixthThread.start();
        seventhThread.start();
        eighthThread.start();
    }
    
}
