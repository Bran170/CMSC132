
package payrollSystem;

/* TerpCorp is a class that represents our company that offers banking payroll 
 * software to its customers in the form of a payed plan and a free plan. 
 * Each copy of the software is a separate instance of this class.
 * This class has no fields and only 2 static methods that create instances of 
 * payed and free plans when called. 
 */
public class TerpCorp 
{
    // this static method takes in the company name as a String parameter and 
    // creates an instance of the payed plan version of the payroll software 
    // and returns it, but only if the parameter is not null or an empty 
    // string. Otherwise it does nothing and returns null. 
    public static BusinessPayrollSystem makeUserSystem(String companyName) 
    {
        BusinessPayrollSystem temp = null;
        // checks if companyName is valid
        if (companyName != null && !companyName.equals(""))
        {
            temp = new BusinessPayrollSystem(companyName);
        }
        return temp; 
    }

    // this static method takes in the company name as a String parameter and 
    // the number of max employees as an Integer and 
    // creates an instance of the free plan version of the payroll software 
    // and returns it, but only if the parameter is not null or an empty 
    // string. Otherwise it does nothing and returns null. 
    public static BusinessPayrollSystem makeUserSystem(String companyName,
                                                       int maxEmployees) 
    {
        BusinessPayrollSystem temp = null;
        // checks if parameters are valid 
        if (companyName != null && !companyName.equals("") && maxEmployees > 0)
        {
            temp = new FreeBusinessPayrollSystem(companyName, maxEmployees);
        }
        return temp;
    }

}
