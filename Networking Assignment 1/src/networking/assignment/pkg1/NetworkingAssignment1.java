/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.assignment.pkg1;

import java.io.IOException;

/**
 *
 * @author Aaron
 */
public class NetworkingAssignment1 {

    public static void main(String[] args) throws IOException  {
        BankServer newServer;
        newServer = new BankServer();
        newServer.run();
    }
    
}
