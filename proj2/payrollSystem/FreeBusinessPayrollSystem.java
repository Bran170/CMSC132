
package payrollSystem;

/* FreeBusinessPayrollSystem is a class that represents a single free version 
 * of the payroll software. 
 * This class is a subclass of BusinessPayrollSoftware and inherits the 
 * appropriate fields and methods of its superclass. 
 * It has 1 unique field, maxEmployees that is an Integer storing the maximum 
 * number of employees it can have. It also has 4 overridden methods. 
 */
public class FreeBusinessPayrollSystem extends BusinessPayrollSystem
{
    private int maxEmployees; 
    
    public FreeBusinessPayrollSystem(String companyName, int maxEmployees)
    {
        super (companyName);
        this.maxEmployees = maxEmployees; 
    }
    
    // this method overrides the newCommissionEmp and only adds the employee 
    // if adding the employee wont go over the limit of employees 
    @Override
    public boolean newCommissionEmp(String empName, double commissionRate) 
    {
        boolean temp = false;
        
        if (super.employeeCount() < maxEmployees)
        {
            temp = super.newCommissionEmp(empName, commissionRate);
        }
        return temp; 
    }
    
    // this method overrides the newSalariedEmp and only adds the employee 
    // if adding the employee wont go over the limit of employees 
    @Override
    public boolean newSalariedEmp(String empName, double commissionRate) 
    {
        boolean temp = false;
        
        if (super.employeeCount() < maxEmployees)
        {
            temp = super.newSalariedEmp(empName, commissionRate);
        }
        return temp; 
    }
    
    // this method returns the maximum number of employees the plan can have 
    // in the form of an Integer 
    @Override
    public int maxEmployees() 
    {
        return maxEmployees; 
    }
    
    // this method returns 0.0 for the amount that is owed as the plan is free 
    @Override
    public double amountOwedToUs() 
    {
        return 0.0;
    }
    
    

}

