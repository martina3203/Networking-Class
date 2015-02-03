/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penetrationtest;

import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class portTester implements Runnable {

    String host;
    int start;
    int end;
    List portList;
    boolean portIsOpen;
    
    portTester(String hostName,int startingPort, int endingPort)
    {
        host = hostName;
        start = startingPort;
        end = endingPort;
        portIsOpen = true;
    }
    @Override
    public void run() {
        //For every port
        for (int i = start; i <= end; i++)
        {
            Socket newSocket = null;
            //Test to see if it's open
            try {
                newSocket = new Socket(host,i);
                System.out.println(i + " is open.");
                portIsOpen = true;
            } catch (IOException ex) {
                //Logger.getLogger(portTester.class.getName()).log(Level.SEVERE, null, ex);
                portIsOpen = false;
            }
            finally
            {
                if (portIsOpen == true)
                {
                    //Add to list if Open
                }
                else
                {
                }
                if (newSocket != null)
                {
                    try {
                        newSocket.close();
                    } catch (IOException ex) {
                        //Logger.getLogger(portTester.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    public List returnList()
    {
        return portList;
    }
}
