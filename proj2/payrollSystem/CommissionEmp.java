
package payrollSystem;

/* CommissionEmp is a class that represents a single commissioned employee in a 
 * instance (copy) of the payroll software. 
 * Each commissioned employee is a separate instance of this class.
 * This class is a subclass of the Employee class and inherits the appropriate 
 * fields and methods of its superclass. This class has 1 unique field, 
 * commissionRate, which is a double containing the commission rate of the 
 * employee. 
 * This class also overrides 1 method of its superclass, getPay. 
 */
public class CommissionEmp extends Employee
{
    private double commissionRate; 
    
    public CommissionEmp (String empName, double commissionRate)
    {
        super(empName); 
        this.commissionRate = commissionRate; 
    }
    
    // This method has no parameters and returns the pay of a commissioned 
    // employee in the form of a double. It overrides the superclass method 
    // because the pay of a commissioned employee is unique and based on their 
    // commission rate and the total sales they make in a period.  
    @Override
    public double getPay()
    {
        return super.getTotalSales() * (commissionRate / 100.0); 
    }
    

}

