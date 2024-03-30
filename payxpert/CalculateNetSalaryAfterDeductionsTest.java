package payxpert;


import org.junit.Test;
import static org.junit.Assert.assertEquals;
import entity.model.Payroll;

public class CalculateNetSalaryAfterDeductionsTest {

    @Test
    public void testCalculateNetSalaryAfterDeductions() {
        
        double grossSalary = 3000.0; 
        double deductions = 500.0; 

        
        double expectedNetSalary = grossSalary - deductions;

        
        assertEquals(expectedNetSalary, Payroll.calculateNetSalaryAfterDeductions(grossSalary, deductions), 0.001);
    }
}
