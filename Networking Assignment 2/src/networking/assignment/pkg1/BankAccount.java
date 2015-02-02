/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networking.assignment.pkg1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Aaron
 */
public class BankAccount {
    
    private double totalAmount;
    private Lock timeLock;
    private Condition acceptableDeposit;
    
    public BankAccount()
    {
        totalAmount = 0;
        timeLock = new ReentrantLock();
        acceptableDeposit = timeLock.newCondition();
    }
    
    public boolean deposit(double amount) throws InterruptedException
    {
        timeLock.lock();
        if (amount >= 100000)
        {
            //Too big for account
            return false;
        }
        while ((totalAmount + amount) >= 100000)
        {
            //Start thread to wait for change in amount to enable deposit
            acceptableDeposit.await();
        }
        totalAmount = totalAmount + amount;
        timeLock.unlock();
        return true;
    }
    
    public boolean withdraw(double amount)
    {
        timeLock.lock();
        if (totalAmount - amount > 0)
        {
            totalAmount = totalAmount - amount;
            if (totalAmount < 100000)
            {
                acceptableDeposit.signalAll();
            }
            timeLock.unlock();
            return true;
        }
        else
        {
            timeLock.unlock();
            return false;
        }
    }
    
    public double returnBalance()
    {
        return totalAmount;
    }
}
