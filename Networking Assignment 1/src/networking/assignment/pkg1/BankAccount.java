/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.assignment.pkg1;

/**
 *
 * @author Aaron
 */
public class BankAccount {
    
    private double totalAmount;
    
    public BankAccount()
    {
        totalAmount = 0;
    }
    
    public void deposit(double amount)
    {
        totalAmount = totalAmount + amount;
    }
    public void withdraw(double amount)
    {
        totalAmount = totalAmount - amount;
    }
    public double returnBalance()
    {
        return totalAmount;
    }
}
