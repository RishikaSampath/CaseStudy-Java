package dao;

import entity.model.Employee;
import exception.EmployeeNotFoundException;

import java.util.List;

public interface IEmployeeService {
    Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException;
    List<Employee> getAllEmployees();
    void addEmployee(Employee employeeData);
    void updateEmployee(Employee employeeData) throws EmployeeNotFoundException;
    void removeEmployee(int employeeId) throws EmployeeNotFoundException;
}
