package bankPackage;

import java.util.ArrayList;

/* BankAccount is a class that represents a single bank account in a 
 * branch (instance) of the NBOC. 
 * Each bankAccount is a separate instance of this class.
 * This class has 5 fields, an Integer acctNumber
 * that holds the account number for the bank account, 
 * a String for the customer name, as well as 3 ArrayLists that hold the 
 * deposits/withdraw amounts and dates of each transaction
 * in the form of doubles and Strings respectively. 
 * This class has certain public methods that allow other classes to get 
 * information about the bank account like its balance, transactions,
 * identifying information, as well as depositing and withdrawing. 
 */

class BankAccount 
{
    private int acctNumber;
    private String custName;
    private ArrayList <String> transactions = new ArrayList <String>();  
    private ArrayList <Double> deposits = new ArrayList <Double>(); 
    private ArrayList <Double> withdraws = new ArrayList <Double>(); 
    
   
    public BankAccount(String custName, int acctNumber)
    {
        this.acctNumber = acctNumber; 
        this.custName = custName; 
        deposits.add(0.0);
        withdraws.add(0.0);
    }
    
    // getter that returns the Integer containing the account number
    public int getAcctNumber()
    {
        return acctNumber;        
    }
    
    // getter that returns the String containing the customer name
    public String getCustName()
    {
        return custName;        
    }
    
    // This method takes in a double parameter with the amount to be deposited
    // and adds it to the ArrayList that contains the List of deposits 
    // that have been made.
    public void deposit(double amount)
    {
        deposits.add(amount);
    }
    
    // This method takes in a double parameter with the amount to be withdrawn
    // and adds it to the ArrayList that contains the List of withdrawals
    // that have been made.
    public void withdraw(double amount)
    {
        withdraws.add(amount);
    }
    
    // This method has no parameters and returns a double containing the 
    // current total balance of the Bank Account.
    public double getBalance()
    {
        double total = 0;
        // Iterates through the ArrayList containing the amounts of each deposit
        // and adds the value to the local variable that holds the total balance 
        for(int i = 0; i < deposits.size(); i ++)
        {
            total += deposits.get(i); 
        }
        // Iterates through the ArrayList containing the amounts of each
        // withdrawal and subtracts the value to the local variable that 
        // holds the total balance 
        for(int i = 0; i < withdraws.size(); i ++)
        {
            total -= withdraws.get(i); 
        } 
        return total; 
    }
   
    // This method has 3 Integer parameters that make up the components of a 
    // date that a transaction takes place on. 
    // It returns nothing but concatenates the components into a single String 
    // and stores it in an ArrayList with all dates of all other transactions
    // that have been conducted on the bank account. 
    public void transaction(int month, int day, int year)
    {
        transactions.add(("" + year + " " + month + " " + day)); 
    }
    
    // This method has 3 Integer parameters that make up the components of a 
    // date that a transaction takes place on and uses that to search for and 
    // return an Integer containing the number of transactions that were 
    // completed on the bank account on that date. 
    public int numTransactions(int month, int day, int year)
    {
        // concatenates the parameters into a String 
        String date = "" + year + " " + month + " " + day; 
        int num = 0;
        // searches for transactions that occurred on the date specified 
        // and increments the local variable each time one is found 
        for(int i = 0; i < transactions.size(); i ++)
        {
             if(transactions.get(i).equals(date))
             {
                 num ++; 
             }
        }
        return num; 
    }
    
    // This method has no parameters but returns the total number of 
    // transactions that have occurred on the bank account. 
    public int numTransactions()
    {
        return transactions.size();
    }

}
