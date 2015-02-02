/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package networking.assignment.pkg1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author blackie
 */
public class connectionHandler implements Runnable {
    private int PORT_NUMBER;
    private boolean portOpen = true;
    @Override
    public void run()
    {
        try {
            ServerSocket server = new ServerSocket(PORT_NUMBER);
            while (portOpen == true)
            {
                Socket theSocket = server.accept();
                System.out.println("Client connected.");
                BankServer.incrementClientConnections();
                BankService newService = new BankService(theSocket,BankServer.bank);
                Thread newSession = new Thread(newService);
                newSession.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(connectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean isOpen()
    {
        return portOpen;
    }
    
    public void closePort()
    {
        portOpen = false;
    }
    
    public void setPort(int newPort)
    {
        PORT_NUMBER = newPort;
    }
}
