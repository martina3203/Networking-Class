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
    private final Bank bank;
    private boolean loggedIn;
    
    public BankService(Socket aSocket, Bank aBank)
    {
        s = aSocket;
        bank = aBank;
        loggedIn = true;
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
                return;
            }
            else executeCommand(command);
        }
    }
    public void executeCommand(String command)
    {
        switch (command) {
            case "DEPOSIT":
                {
                    int account = in.nextInt();
                    double amount = in.nextDouble();
                    bank.deposit(account, amount);
                    out.print("DEPOSIT COMPLETE: ");
                    out.print(bank.getBalance(account));
                    out.println(" CODE 200");
                    out.flush();
                    break;
                }
            case "WITHDRAW":
                {
                    int account = in.nextInt();
                    double amount = in.nextDouble();
                    if (bank.withdraw(account,amount) == true)
                    {
                        out.print("WITHDRAW COMPLETE: ");
                        out.print(bank.getBalance(account));
                        out.println(" CODE 200");
                        out.flush();
                    }
                    else
                    {
                        out.print("WITHDRAW CANCELLED: ACCOUNT OVERDRAWN");
                        out.flush();
                    }
                    break;
                }
            case "QUIT":
                loggedIn = false;
                return;
            case "BALANCE":
                {
                    int account = in.nextInt();
                    out.println("BALANCE: ");
                    out.println(account + " " + bank.getBalance(account));
                    out.flush();
                    break;
                }
            default:
                if (s.getLocalPort() == 8889)
                {
                    //Possible Admin Commands
                    switch (command) {
                        case "LOGIN":
                            String loginCredentials = in.next();
                            if ("ADMIN".equals(loginCredentials))
                            {
                                out.println("PASSWORD:" );
                                out.flush();
                                loginCredentials = in.next();
                                if ("PASSWORD".equals(loginCredentials))
                                {
                                    out.println("LOGIN SUCCESSFUL. WELCOME CAPTAIN KIRK.");
                                    out.flush();
                                }
                                else
                                {
                                    out.println("UNKNOWN PASSWORD. TRY AGAIN. ERROR 296");
                                    out.flush();
                                }
                            }
                            else
                            {
                                out.println("UNKNOWN ACCOUNT. TRY AGAIN. ERROR 295");
                                out.flush();
                            }
                            break;
                        case "STATUS":
                            out.println("SERVER ONLINE");
                            out.print("Connections Made: ");
                            out.print(BankServer.returnClientConnections());
                            out.flush();
                            break;
                        case "LOGOUT":
                            loggedIn = false;
                            break;
                        case "SHUTDOWN":
                            out.println("SERVER SHUTTING DOWN");
                            out.flush();
                            System.exit(1);
                        default:
                            out.println("Invalid Command");
                            out.flush();
                            break;
                    }
                }
                else
                {
                    out.println("Invalid Command");
                    out.flush();
                    return;
                }
        }
    }
}
