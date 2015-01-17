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
    public static void run() throws IOException
    {
        final int ACCOUNTS_LENGTH = 10;
        Bank bank = new Bank(ACCOUNTS_LENGTH);
        final int SBAP_PORT = 8888;
        ServerSocket server = new ServerSocket(SBAP_PORT);
        System.out.println("Waiting for clients to connect...");
        inputThread input = new inputThread();
        input.start();
        boolean runServer = true;
        while (runServer == true)
        {
            if ("QUIT".equals(input.checkInput()))
            {
                break;
            }
            Socket s = server.accept();
            System.out.println("Client Connected.");
            BankService service = new BankService(s,bank);
            Thread t = new Thread(service);
            t.start(); 
           
        }
    }
}
