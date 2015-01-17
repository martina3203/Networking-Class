/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.assignment.pkg1;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Aaron
 */
public class BankService implements Runnable {
    private Socket s;
    private Scanner in;
    private PrintWriter out;
    private Bank bank;
    private boolean loggedIn = true;
    
    public BankService(Socket aSocket, Bank aBank)
    {
        s = aSocket;
        bank = aBank;
    }
    
    public void run()
    {
        try
        {
            try
            {
                in = new Scanner(s.getInputStream());
                out = new PrintWriter(s.getOutputStream());
                doService();
            }
            finally
            {
                s.close();
            }
        }
        catch (IOException exception)
        {
            exception.printStackTrace();
        }
    }
    
    public void doService() throws IOException
    {
        while (loggedIn == true)
        {
            if (!in.hasNext())
            {
                return;
            }
            String command = in.next();
            if (command.equals("QUIT")){
                loggedIn = false;
            }
            else executeCommand(command);
        }
    }
    public void executeCommand(String command)
    {
        if (command.equals ("DEPOSIT"))
        {
            int account = in.nextInt();
            double amount = in.nextDouble();
            bank.deposit(account,amount);
        }
        else if (command.equals("WITHDRAW"))
        {
            int account = in.nextInt();
            double amount = in.nextDouble();
            bank.withdraw(account,amount);
        }
        else if (command.equals("QUIT"))
        {
            loggedIn = false;
            return;
        }
        else if (command.equals("BALANCE"))
        {
            int account = in.nextInt();
            out.println(account + " " + bank.getBalance(account));
            out.flush();
        }
        else
        {
            if (s.getLocalPort() == 8889)
            {
                //Possible Admin Commands
                switch (command) {
                    case "LOGIN":
                        break;
                    case "STATUS":
                        System.out.println("SERVER ONLINE");
                        break;
                    case "PASSWORD":
                        
                        break;
                    case "LOGOUT":
                        loggedIn = false;
                        break;
                    case "SHUTDOWN":
                        System.exit(1);
                    default:
                        out.println("Invalid Command");
                        break;
                }
            }
            else
            {
                int account = in.nextInt();
                out.println("Invalid Command");
                out.flush();
                return;
            }
        }
    }
}
