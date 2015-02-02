/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knockknockserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Aaron
 */
public class server implements Runnable {
    @Override
    public void run()
    {
        int SERVER_PORT = 8888;
        boolean PORT_OPEN = true;
        ServerSocket server;
        try {
            server = new ServerSocket(SERVER_PORT);
            while (PORT_OPEN)
            {
                try 
                {
                   Socket theSocket = server.accept();
                   jokeSession newSession = new jokeSession(theSocket);
                   Thread newThread = new Thread(newSession);
                   newThread.start();
                } 
            catch (IOException ex) {
                Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        } catch (IOException ex) {
            Logger.getLogger(server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
