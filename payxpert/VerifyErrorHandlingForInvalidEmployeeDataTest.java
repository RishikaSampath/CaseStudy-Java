package payxpert;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import dao.EmployeeService;
import entity.model.Employee;

public class VerifyErrorHandlingForInvalidEmployeeDataTest {

    @Test
    public void testErrorHandlingForInvalidEmployeeData() {
        
        EmployeeService employeeService = new EmployeeService();
        Employee invalidEmployeeData = new Employee();

        boolean isExceptionThrown = false;
        try {
            employeeService.addEmployee(invalidEmployeeData);
        } catch (Exception e) {
            
            isExceptionThrown = true;

        }
        assertTrue(isExceptionThrown);
    }
  }

