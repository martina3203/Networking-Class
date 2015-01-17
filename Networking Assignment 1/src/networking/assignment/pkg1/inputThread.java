/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package networking.assignment.pkg1;
import java.util.Scanner;

/**
 *
 * @author blackie
 */
public class inputThread extends Thread {
    public Scanner inputRead;
    private String userInput;
    void inputThread()
    {
        inputRead = new Scanner(System.in);
    }
    public String checkInput()
    {
        userInput = inputRead.nextLine();
        return userInput;
    }
}
