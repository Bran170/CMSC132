
package payrollSystem;

import arrayBackedList.ArrayBackedList;

/* BusinessPayrollSystem is a class that represents a single payed payroll 
 * software. This class has 2 fields, a String that contains the company name 
 * and an ArraybackedList that contains the employees, both salaried and 
 * commissioned in the payroll. 
 * This class has certain public methods that allow other classes to get 
 * information about the payroll software and also issue paychecks. 
 */
public class BusinessPayrollSystem 
{
    private String companyName;
    private ArrayBackedList employees = new ArrayBackedList(1);
    
    public BusinessPayrollSystem(String companyName)
    {
        this.companyName = companyName; 
    }

    public String getCompanyName() 
    {
        return companyName; 
    }

    public boolean newCommissionEmp(String empName, double commissionRate) 
    {
        boolean temp = false; 
        
        if (empName != null && !empName.equals("") && commissionRate > 0.0 
                && commissionRate <= 100.00 && !isDuplicate(empName))
        {
            employees.add(new CommissionEmp(empName, commissionRate));
            
            temp = true; 
        }
        return temp; 
    }

    public boolean newSalariedEmp(String empName, double yearlySalary) 
    {
        boolean temp = false; 
        
        if (empName != null && !empName.equals("") && yearlySalary > 0.0 
                 && !isDuplicate(empName))
        {
            employees.add(new SalariedEmp(empName, yearlySalary));
            
            temp = true; 
        }
        return temp; 
    }

    public boolean isEmployee(String empName) 
    {
        return isDuplicate(empName);
    }

    public int employeeCount() 
    {
        return employees.getSize();
    }

    public int maxEmployees() 
    {
        return Integer.MAX_VALUE; 
    }

    public boolean addHoursWorked(String empName, int hoursWorked) 
    {
        boolean temp = false; 
        
        if (empName != null && !empName.equals("") && hoursWorked > 0
                 && isDuplicate(empName))
        {
            for (int i = 0; i < employees.getSize(); i ++)
            {
                if (((Employee) employees.get(i)).isEmpName(empName))
                {
                    temp = ((Employee) employees.get(i)).addHours(hoursWorked); 
                }
            }
        }
        return temp; 
    }

    public int hoursWorked(String empName) 
    {
        int temp = -1; 
        
        if (empName != null && !empName.equals("") && isDuplicate(empName))
        {
            for (int i = 0; i < employees.getSize(); i ++)
            {
                if (((Employee) employees.get(i)).isEmpName(empName))
                {
                    temp = ((Employee) employees.get(i)).getHoursWorked(); 
                }
            }
        }
        return temp; 
    }

    public boolean saleMade(String empName, double saleAmt) 
    {
        boolean temp = false;
        
        if (empName != null && !empName.equals("") && isDuplicate(empName)
                && saleAmt > 0.0)
        {
            for (int i = 0; i < employees.getSize(); i ++)
            {
                if (((Employee) employees.get(i)).isEmpName(empName))
                {
                    ((Employee) employees.get(i)).sale(saleAmt);
                    
                    temp = true; 
                }
            }
        }
        return temp; 
        
    }

    public double totalSalesMade(String empName) 
    {
        double temp = -1.0; 
        
        if (empName != null && !empName.equals("") && isDuplicate(empName))
        {
            for (int i = 0; i < employees.getSize(); i ++)
            {
                if (((Employee) employees.get(i)).isEmpName(empName))
                {
                    temp = ((Employee) employees.get(i)).getTotalSales();
                }
            }
        }
        return temp; 
    }

    public double issuePaycheck(String empName) 
    {
        double temp = -1.0; 
        
        if (empName != null && !empName.equals("") && isDuplicate(empName))
        {
            for (int i = 0; i < employees.getSize(); i ++)
            {
                if (((Employee) employees.get(i)).isEmpName(empName))
                {
                    temp = ((Employee) employees.get(i)).getPay();
                }
            }
        }
        return temp; 
    }

    public double payrollTotal() 
    {
        double temp = 0.0;
        
        for (int i = 0; i < employees.getSize(); i ++)
        {
            temp += ((Employee) employees.get(i)).getPay();
        }
        return temp; 
    }

    public void changePayPeriod() 
    {
        for (int i = 0; i < employees.getSize(); i ++)
        {
            ((Employee) employees.get(i)).reset();
        }
    }

    public double amountOwedToUs() 
    {
        return 10.0 * employees.getSize();
    }
    
    private boolean isDuplicate(String empName)
    {
        boolean temp = false;
        
        for (int i = 0; i < employees.getSize(); i ++)
        {
            if (((Employee) employees.get(i)).isEmpName(empName))
            {
                temp = true; 
            }
        }
        return temp; 
    }

}
