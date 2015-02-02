/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.assignment.pkg1;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Aaron
 */
public class BankServer {
    public static Bank bank;
    private static int clientConnections = 0;
    public static void run() throws IOException
    {
        final int ACCOUNTS_LENGTH = 10;
        bank = new Bank(ACCOUNTS_LENGTH);
        System.out.println("Waiting for clients to connect...");
        connectionHandler clientHandler = new connectionHandler();
        connectionHandler adminHandler = new connectionHandler();
        clientHandler.setPort(8888);
        adminHandler.setPort(8889);
        Thread client = new Thread(clientHandler);
        Thread admin = new Thread(adminHandler);
        client.start();
        admin.start();
        Scanner newScanner = new Scanner(System.in);
        while (!newScanner.nextLine().contentEquals("stop"))
        {
           //System.out.print("poop");
        }
        clientHandler.closePort();
        adminHandler.closePort();
        client.interrupt();
        admin.interrupt();
        showTotalConnections();
        System.exit(clientConnections);
    }
    
    public static void incrementClientConnections()
    {
        clientConnections = clientConnections + 1;
    }
    
    public static void showTotalConnections()
    {
        System.out.print("Connections Made since start: ");
        System.out.println(clientConnections);
    }
    
    public static int returnClientConnections()
    {
        return clientConnections;
    }
}
