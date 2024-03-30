package dao;

import entity.model.Employee;
import exception.EmployeeNotFoundException;
import util.DatabaseContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService implements IEmployeeService {
    private Map<Integer, Employee> employeeMap; 

    public EmployeeService() {
        
        this.employeeMap = new HashMap<>();
    }
    private static final String DB_URL = "jdbc:mysql://localhost:3306/payxpert";
    private static final String USER = "root";
    private static final String PASSWORD = "Rishika@12";

    @Override
    public Employee getEmployeeById(int employeeId) throws EmployeeNotFoundException {
        Employee employee = null;
        String sql = "SELECT * FROM Employee WHERE EmployeeID = ?";
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    employee = new Employee();
                    employee.setEmployeeID(resultSet.getInt("EmployeeID"));
                    employee.setFirstName(resultSet.getString("FirstName"));
                    employee.setLastName(resultSet.getString("LastName"));
                    employee.setDateOfBirth(resultSet.getDate("DateOfBirth").toLocalDate());
                    employee.setGender(resultSet.getString("Gender"));
                    employee.setEmail(resultSet.getString("Email"));
                    employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                    employee.setAddress(resultSet.getString("Address"));
                    employee.setPosition(resultSet.getString("Position"));
                    employee.setJoiningDate(resultSet.getDate("JoiningDate").toLocalDate());
                    Date terminationDate = resultSet.getDate("TerminationDate");
                    if (terminationDate != null) {
                        employee.setTerminationDate(terminationDate.toLocalDate());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EmployeeNotFoundException("Error retrieving employee with ID " + employeeId);
        }
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
        }
        return employee;
    }


    @Override
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employee";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmployeeID(resultSet.getInt("EmployeeID"));
                employee.setFirstName(resultSet.getString("FirstName"));
                employee.setLastName(resultSet.getString("LastName"));
                employee.setDateOfBirth(resultSet.getDate("DateOfBirth").toLocalDate());
                employee.setGender(resultSet.getString("Gender"));
                employee.setEmail(resultSet.getString("Email"));
                employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
                employee.setAddress(resultSet.getString("Address"));
                employee.setPosition(resultSet.getString("Position"));
                employee.setJoiningDate(resultSet.getDate("JoiningDate").toLocalDate());
                Date terminationDate = resultSet.getDate("TerminationDate");
                if (terminationDate != null) {
                    employee.setTerminationDate(terminationDate.toLocalDate());
                }
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }


    @Override
    public void addEmployee(Employee employee) {
        employeeMap.put(employee.getEmployeeID(), employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws EmployeeNotFoundException {
        if (employeeMap.containsKey(employee.getEmployeeID())) {
            employeeMap.put(employee.getEmployeeID(), employee);
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + employee.getEmployeeID() + " not found.");
        }
    }

    @Override
    public void removeEmployee(int employeeId) throws EmployeeNotFoundException {
        if (employeeMap.containsKey(employeeId)) {
            employeeMap.remove(employeeId);
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + employeeId + " not found.");
        }
    }

	
}
