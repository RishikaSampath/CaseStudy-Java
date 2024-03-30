package payxpert;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import entity.model.Employee;

public class CalculateGrossSalaryForEmployeeTest {

    @Test
    public void testCalculateGrossSalaryForEmployee() {
        
        Employee employee = new Employee("John", "Doe", 2000.0, 100.0, 50.0);

        
        double expectedGrossSalary = 2000.0 + 100.0 + 50.0; // Basic Salary + Overtime Pay + Bonus

        
        assertEquals(expectedGrossSalary, employee.calculateGrossSalary(), 0.001);
    }
}