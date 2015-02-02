/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penetrationtest;

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
        portTester first =  new portTester("localhost",6000,6249);
        portTester second = new portTester("localhost",6250,6499);
        portTester third = new portTester("localhost",30000,30249);
        portTester fourth = new portTester("localhost",6750,6999);
        portTester fifth = new portTester("localhost",20000,20249);
        portTester sixth = new portTester("localhost",7250,7499);
        portTester seven = new portTester("localhost",7500,7749);
        portTester eight = new portTester("localhost",10000,10249);
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
        //Wait for them to finish.
        while(firstThread.isAlive() & secondThread.isAlive() & thirdThread.isAlive() & fourthThread.isAlive() & fifthThread.isAlive()
                & sixthThread.isAlive() & seventhThread.isAlive() & eighthThread.isAlive())
        {
            //Place holder
        }
        
        //List firstList = first.returnList();
        //List secondList = second.returnList();
        //List thirdList = third.returnList();
        //List fourthList = fourth.returnList();
        //List fifthList = fifth.returnList();
        //List sixthList = sixth.returnList();
        //List seventhList = seven.returnList();
        //List eighthList = eight.returnList();
        System.out.println("List of Open Ports: ");
        //for (int i = 0; i < firstList.size(); i++)
        {
            //System.out.print(firstList.get(i) + " ");
        }
      
    }
    
}
