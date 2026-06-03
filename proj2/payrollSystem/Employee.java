
package payrollSystem;

import arrayBackedList.ArrayBackedList;

/* Employee is a class that represents a generic employee in a 
 * instance of the payroll software. It is the superclass of both SalariedEmp 
 * and CommissionEmp. 
 * This class has 3 fields, a String empName that holds the name of the 
 * employee, and 2 arrayBackedLists that hold the sales and hours the 
 * employee has worked, with each element being a number of hours worked at 
 * a time or a sale amount for a single sale. 
 * This class has certain public methods that allow other classes to get 
 * information about the employee like getting its pay, total sales, 
 * total hours worked, as well as adding hours and sales. 
 */
public class Employee 
{
    private ArrayBackedList hoursWorked = new ArrayBackedList(1); 
    private String empName; 
    private ArrayBackedList sales = new ArrayBackedList(1);
    
    public Employee(String empName)
    {
        this.empName = empName; 
        hoursWorked.add(0);
        sales.add(0.0);
    }
    
    // This method returns true of the String parameter matches the name of 
    // the employee and false if not 
    public boolean isEmpName(String empName)
    {
        return this.empName.equals(empName); 
    }
    
    // this method returns the total hours the employee has worked in the 
    // current pay period in the form of an Integer. It has no parameters. 
    public int getHoursWorked()
    {
        int temp = 0; 
        // iterates through the arrayBackedList hoursWorked 
        // and adds the elements 
        for (int i = 0; i < hoursWorked.getSize(); i ++)
        {
            temp += (int) hoursWorked.get(i);
        }
        return temp; 
    }
    
    // this method adds more hours to the total hours the employee has worked 
    // in the current pay period. The new hours to be added are given as an
    // Integer parameter. It always returns true. 
    public boolean addHours(int hours)
    {
        hoursWorked.add(hours); 
        return true; 
    }
    
    // this method returns the total sales the employee has made in the 
    // current pay period in the form of a Double. It has no parameters. 
    public double getTotalSales()
    {
        double temp = 0.0; 
        // iterates through the arrayBackedList sales
        // and adds the elements 
        for (int i = 0; i < sales.getSize(); i ++)
        {
            temp += (double) sales.get(i);
        }
        return temp; 
    }
    
    // this method adds a sale amount to the arraybackedList sales that is 
    // given in the double parameter. Represents the employee making a sale. 
    public void sale(double amount)
    {
        sales.add(amount); 
    }
    
    // this method returns the pay of the employee always in the value of 0.0. 
    // it has no parameters. 
    public double getPay()
    {
        return 0.0; 
    }
    
    // this method resets the hours worked and sales made of the employee, 
    // representing when a new pay period has begun. The sales and hoursWorked 
    // arraybackedLists are reset to their default states with a single 
    // default element. 
    public void reset()
    {
        hoursWorked = new ArrayBackedList(1);
        hoursWorked.add(0);
        
        sales = new ArrayBackedList(1);
        sales.add(0.0);
    }

}

