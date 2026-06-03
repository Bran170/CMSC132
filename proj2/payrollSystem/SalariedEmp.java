
package payrollSystem;

/* SalariedEmp is a class that represents a salaried employee in a 
 * specific banking software (instance of BusinessPayrollSystem) of a company. 
 * This class is a subclass of Employee, and inherits all the appropriate 
 * fields and methods of that class. 
 * This class has 1 unique field, a double yearlySalary
 * that holds the yearly salary of the employee. 
 * This class has overrides 3 methods of its superclass, addHours, 
 * getTotalSales, and getPay. 
 */
public class SalariedEmp extends Employee
{

    private double yearlySalary; 
    
    public SalariedEmp(String empName, double yearlySalary) 
    {
        super(empName);
        this.yearlySalary = yearlySalary; 
    }
    
    // this overridden method adds hours that is passed in as an Integer 
    // parameter to the arrayBackedList containing a list of the hours the 
    // employee has worked and returns true, but only if the sum of all the 
    // hours will not go over 80 hours if it is added. 
    // Otherwise, it will do nothing and return false. 
    @Override
    public boolean addHours(int hours)
    {
        boolean temp = false;
        // checks if we can add the hours 
        if (super.getHoursWorked() + hours <= 80)
        {
            super.addHours(hours); 
            
            temp = true;
        }
        return temp; 
    }
    
    // this overridden method returns a double value for the total sales the 
    // employee has made, but because we do not really care about the sales of 
    // a salaried employee it always returns 0.0. It has no parameters. 
    @Override
    public double getTotalSales()
    {
        return 0.0; 
    }
    
    // this overridden method returns the pay that a salaried employee should 
    // get each pay period in the form of a double. It has no parameters. 
    @Override
    public double getPay()
    {
        return yearlySalary / 26; 
    }
    

}

