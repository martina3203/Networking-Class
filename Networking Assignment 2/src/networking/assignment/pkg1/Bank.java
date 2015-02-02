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
public class Bank {

    private BankAccount[] accounts;
    public Bank(int size)
    {
        accounts = new BankAccount[size];
        for (int i = 0; i < accounts.length; i++)
        {
            accounts[i] = new BankAccount();
        }
    }
    
    public void deposit(int accountNumber, double amount) throws InterruptedException
    {
        BankAccount account = accounts[accountNumber];
        account.deposit(amount);
    }
    
    public boolean withdraw(int accountNumber, double amount)
    {
        BankAccount account = accounts[accountNumber];
        if (account.withdraw(amount) == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    public double getBalance(int accountNumber)
    {
        BankAccount account = accounts[accountNumber];
        return account.returnBalance();
    }
}
