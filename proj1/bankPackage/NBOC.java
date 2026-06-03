package bankPackage;


// project assignment.
import java.util.ArrayList;

/*This class simulates a bank, specifically the National bank of College Park, 
 *or NBOC. Each instance of this class represents a new branch of the NBOC. 
 *The NBOC can store bank accounts of the type BankAccount and has 3 fields. 
 *The first field is an ArrayList that stores a List of all BankAccounts opened 
 *at the bank. This class also has 2 more Integer fields that store the total 
 *number of transactions performed on all accounts that currently exist 
 *at the bank as well as the total number of bank accounts that currently exist. 
 * This class also has both public and private methods that can perform certain 
 * actions like creating a new account, depositing and withdrawing from the 
 * balance of a bank account and others. 
 */

public class NBOC 
{
    private ArrayList <BankAccount> bank = new ArrayList <BankAccount>();
    private int numAccounts = 0; 
    private int numTransactions = 0; 

    // This method takes in a String parameter of the customer name 
    // and an Integer parameter of the account number and creates a new bank 
    // account with the name and number, the number of accounts will 
    // increase by 1, and returns true afterward. However, if 
    // there is already a bank account with the same account number or the 
    // customer name parameter is null/empty, the method
    // does nothing and returns false. 
    public boolean addBankAccount(String custName, int acctNumber) 
    {
        boolean temp = false;
        // checks if the parameters are valid
        if(custName != null && !custName.isEmpty() 
                && !isDuplicate(bank, acctNumber))
        {
            bank.add(new BankAccount(custName, acctNumber));
            numAccounts ++; 
            temp = true;    
        }
        return temp; 
       
    }

    // This method takes in an Integer parameter and returns true if a 
    // bank account with that account number exists and false otherwise. 
    public boolean isBankAccount(int acctNumber) 
    {
        boolean temp = false;
        // calls helper method 
        if(isDuplicate(bank, acctNumber))
        {
            temp = true;
        }
        return temp; 
       
    }

    // This method has no parameters and returns the total number of accounts 
    // that currently exists in the bank.
    public int numAccounts() 
    {
        return numAccounts; 
       
    }

    // This method has 3 Integer parameters that make up the components of a 
    // date that a transaction takes place on. This method also takes in an 
    // Integer parameter for the account number of the account 
    // the deposit is being done on as well as a double with the amount 
    // to be deposited. This method will do nothing if any part of the date 
    // is invalid (not an actual date) and return false. For example, the 
    // month cannot be 13, as there are only 12 months in a year. 
    // The method will return true after the deposit has been made. 
    public boolean depositToBankAccount(int acctNumber, int month, int day,
                                        int year, double amount) 
    {
        boolean temp = false;
        // checks if the parameters are invalid
        if(isDuplicate(bank, acctNumber) && amount > 0.0 && year >= 0 && 
            isValidMonth(month))
        {
            if(isValidDay(day, month))
            {
                for(int i = 0; i < bank.size(); i ++)
                {
                    if(bank.get(i).getAcctNumber() == acctNumber)
                    {
                        bank.get(i).deposit(amount);
                        // stores the transaction date for future reference 
                        bank.get(i).transaction(month, day, year);
                        numTransactions ++; 
                        temp = true; 
                    }      
                }
            }
        }
        return temp; 
    }

    // This method has 3 Integer parameters that make up the components of a 
    // date that a transaction takes place on. This method also takes in an 
    // Integer parameter for the account number of the account 
    // the withdrawal  is being done on as well as a double with the amount 
    // to be withdrawn. This method will do nothing if any part of the date 
    // is invalid (not an actual date: for example, the 
    // month cannot be 13, as there are only 12 months in a year.) 
    // or if the current balance is less than the amount to be withdrawn. 
    // The method will then return false.  
    // The method will return true after the withdrawal has been made. 
    public boolean withdrawFromBankAccount(int acctNumber, int month,
                                           int day, int year,
                                           double amount) 
    {
        boolean temp = false;
        // checks if the parameters are invalid
        if(isDuplicate(bank, acctNumber) && amount > 0.0 && year >= 0 && 
                isValidMonth(month))
        {
            if(isValidDay(day, month))
            {
                for(int i = 0; i < bank.size(); i ++)
                {
                    if(bank.get(i).getAcctNumber() == acctNumber && 
                            bank.get(i).getBalance() >= amount)
                    {
                        bank.get(i).withdraw(amount);
                        // stores the transaction date for future reference 
                        bank.get(i).transaction(month, day, year);   
                        numTransactions ++; 
                        temp = true; 
                    }      
                }
            }
        }
        return temp;
    }

    // This method takes in an Integer parameter containing an account number 
    // and returns the current balance of the account in the form of a double. 
    // However, if the account number does not correspond to a bank account 
    // that currently exists, the method will instead return 0.0. 
    public double acctBalance(int acctNumber) 
    {
        double balance = 0.0;
        for(int i = 0; i < bank.size(); i ++)
        {
            // searching for a bank account with the specified account number
            if(bank.get(i).getAcctNumber() == acctNumber)
            {
                // getting the balance 
                balance += bank.get(i).getBalance(); 
                
            }
        }
        return balance; 
            
    }

    // This method takes in an String parameter containing a customer name 
    // and returns the total number of bank accounts of that customer in the 
    // form of a Integer.
    // However, if the parameter is null or empty, the method will instead 
    // return 0. 
    public int numAccounts(String custName) 
    {
        int numAccounts = 0;
        // checking if the parameter is invalid
        if(custName != null && !custName.isEmpty())
        {
              for(int i = 0; i < bank.size(); i ++)
              {
                  //searching for bank accounts that are under the 
                  //specified name 
                  if(bank.get(i).getCustName().equals(custName))
                  {
                      numAccounts++ ;                 
                  }
              }
              
        }
        return numAccounts; 
        
    }

    // This method takes in an String parameter containing a customer name 
    // and returns the total balance of all bank accounts that currently exists 
    // of that customer in the form of a double.
    // However, if the parameter is null or empty or the customer has 
    // no bank accounts, the method will instead return 0.0.  
    public double acctBalance(String custName) 
    {
        double balance = 0.0;
        // checking if the parameter is invalid
        if(custName != null && !custName.isEmpty())
        {
              for(int i = 0; i < bank.size(); i ++)
              {
                  if(bank.get(i).getCustName().equals(custName))
                  {
                      balance += bank.get(i).getBalance();          
                  }
              }
              
        }
        return balance; 
       
    }

    // This method has no parameters and returns the total number of 
    // transactions completed for all current bank accounts at the bank. 
    public int numTransactions() 
    {
        return numTransactions; 
    }

    // This method has 4 Integer parameters that make up the components of a 
    // date that a transaction takes place on and an account number and returns 
    // number of transactions that were completed on the specified 
    // account on the specified date. The method will return 0 if the account
    // number does not correspond to a current bank account or if any component 
    // of the date is invalid. 
    public int numTransactions(int acctNumber, int month, int day,
                               int year) 
    {
        int numTransactions = 0;
     // checking if the parameter is invalid
        if(isDuplicate(bank, acctNumber) && year >= 0 && isValidMonth(month))
        {
            if(isValidDay(day, month))
            {
                for(int i = 0; i < bank.size(); i ++)
                {
                    // searching for the bank account 
                    if(bank.get(i).getAcctNumber() == acctNumber)
                    {
                        numTransactions = 
                        bank.get(i).numTransactions(month, day, year);       
                    }
                }
            }
        }
        return numTransactions; 
    }

    // This method will attempt to close the bank account with the specified 
    // account number from the Integer parameter. Once the account has been 
    // closed, its transactions will disappear, the number of total bank 
    // accounts will decrement, and the method returns true. However, the 
    // method will return false and do nothing  if there is no current bank 
    // account with the specified account number. 
    public boolean closeBankAccount(int acctNumber) 
    {
        boolean temp = false;
        for(int i = 0; i < bank.size(); i ++)
        {
            if(bank.get(i).getAcctNumber() == acctNumber)
            {
                numAccounts --;
                numTransactions -= bank.get(i).numTransactions();
                bank.remove(i); 
                temp = true; 
            }
        }
        return temp; 
    }
    
    // This method is a helper method that takes in the ArrayList of bank 
    // accounts and an Integer of a account number and checks to see if the 
    // account number corresponds to a currently existing bank account. It will 
    // return true if the bank account exists and false otherwise. 
    private boolean isDuplicate(ArrayList <BankAccount> bank, int acctNumber)
    {
       boolean temp = false;
       for(int i = 0; i < bank.size(); i ++)
       {
           if(bank.get(i).getAcctNumber() == acctNumber)
           {
               temp = true;
           }      
       }
       return temp; 
    }
    
    // This helper method takes in an integer parameter that represents a month 
    // and checks to see if it is invalid.
    // ( Month number can't be negative or greater than 12)
    // If it is not invalid, it returns true and false otherwise. 
    private boolean isValidMonth(int month)
    {
        boolean temp = true;
        if(month > 12 || month < 1)
        {
            temp = false;
        }
        return temp;
    }
    
    // This helper method takes in two integer parameters that represents a 
    // month and  day and checks to see if it is invalid.
    // (each month of the year has a certain number of days)
    // If it is not invalid, it returns true and false otherwise. 
    private boolean isValidDay(int day, int month)
    {
        boolean temp = true;
        if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8
                || month == 10 || month == 12)
        {
            if(day < 1 || day > 31)
            {
                temp = false; 
            }
        }
        else if(month == 4 || month == 6 || month == 9 || month == 11)
        {
            if(day < 1 || day > 30)
            {
                temp = false; 
            } 
        }
        else if(month == 2 && (day < 1 || day > 28 ))
        {
            temp = false; 
        }
        return temp; 
    }
    
}
