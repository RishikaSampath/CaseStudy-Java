package payxpert;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import dao.PayrollService;
import entity.model.Employee;
import exception.PayrollGenerationException;

public class ProcessPayrollForMultipleEmployeesTest {

    @Test
    public void testProcessPayrollForMultipleEmployees() {
        List<Employee> employees = createMockEmployees();
        PayrollService payrollService = new PayrollService();

        try {
            payrollService.processPayrollForMultipleEmployees(employees);
            int actualTotalPayrollCount = payrollService.getTotalPayrollCount();
            assertEquals(employees.size(), actualTotalPayrollCount);
        } catch (PayrollGenerationException e) {
            e.printStackTrace();
        }
    }

    private List<Employee> createMockEmployees() {
        List<Employee> employees = new ArrayList<>();

        Employee employee1 = new Employee();
        employee1.setEmployeeID(1);
        employee1.setEmployeeID(1);
        employee1.setFirstName("John");
        employee1.setLastName("Doe");
        employee1.setDateOfBirth(LocalDate.of(1990, 5, 15));
        employee1.setGender("Male");
        employee1.setEmail("john.doe@example.com");
        employee1.setPhoneNumber("1234567890");
        employee1.setAddress("123 Main St, City, Country");
        employee1.setPosition("Manager");
        employee1.setJoiningDate(LocalDate.of(2015, 7, 1));
        employee1.setTerminationDate(null);
        employees.add(employee1);

        Employee employee2 = new Employee();
        employee2.setEmployeeID(2);
        employee2.setEmployeeID(2);
        employee2.setFirstName("Jane");
        employee2.setLastName("Smith");
        employee2.setDateOfBirth(LocalDate.of(1985, 10, 25));
        employee2.setGender("Female");
        employee2.setEmail("jane.smith@example.com");
        employee2.setPhoneNumber("9876543210");
        employee2.setAddress("456 Oak St, City, Country");
        employee2.setPosition("Analyst");
        employee2.setJoiningDate(LocalDate.of(2018, 3, 15));
        employee2.setTerminationDate(LocalDate.of(2021, 6, 30)); 
        employees.add(employee2);

        
        
        return employees;
    }
}












