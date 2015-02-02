/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knockknockserver;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aaron
 */
public class jokeSession implements Runnable {

    Socket currentSocket;
    ServerSocket currentServer;
    Scanner input;
    PrintWriter output;
    String firstLineOfJoke;
    String punchLine;
    String CorrectAnswer;

    jokeSession(Socket theSocket) throws IOException {
        currentSocket = theSocket;
        firstLineOfJoke = "";
        punchLine = "";
        CorrectAnswer = "";
        
        input = new Scanner(currentSocket.getInputStream());
        output = new PrintWriter(currentSocket.getOutputStream());
        
        //Select joke
        Random randomNumber = new Random();
        int random = randomNumber.nextInt(3);
        if (random == 1)
        {
            firstLineOfJoke = "Adore";
            punchLine = "Adore is between us. Open up!";
        }
        else if (random == 2)
        {
            firstLineOfJoke = "King Tut";
            punchLine = "King Tut-key Fried Chicken!";
        }
        else
        {
            firstLineOfJoke = "Monkey";
            punchLine = "This game is stupid. Make it stop.";
        }
        CorrectAnswer = firstLineOfJoke + " who?";
        
    }
    
    @Override
    public void run()
    {
        try {
            //Supply Joke
            output.println("Knock Knock");
            output.flush();
            String stringStorage = "";
            while (!"Who's there?".equals(stringStorage))
            {
                stringStorage = input.nextLine();
                if ("Who's there?".equals(stringStorage))
                {
                    output.println(firstLineOfJoke);
                    output.flush();
                }
                else
                {
                    output.println("You gotta say 'Who's there?'!");
                    output.flush();
                }
            }
            while (!CorrectAnswer.equals(stringStorage))
            {
                stringStorage = input.nextLine();
                if (CorrectAnswer.equals(stringStorage))
                {
                    output.println(punchLine);
                    output.flush();
                }
                else
                {
                    output.println("Don't be a noob. You gotta say ask '_____ Who?'");
                    output.flush();
                }
            }
            currentSocket.close();
            
        } catch (IOException ex) {
            Logger.getLogger(jokeSession.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
