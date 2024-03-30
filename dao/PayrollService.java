package dao;
import entity.model.Employee;
import entity.model.Payroll;
import exception.PayrollGenerationException;
import util.DatabaseContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PayrollService implements IPayrollService {

	@Override
    public void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate) throws PayrollGenerationException {
        double basicSalary = 3000.0;
        double overtimePay = 200.0;
        double deductions = 500.0;
        double netSalary = basicSalary + overtimePay - deductions;

        Payroll payroll = new Payroll();
        payroll.setEmployeeID(employeeId);
        payroll.setPayPeriodStartDate(startDate);
        payroll.setPayPeriodEndDate(endDate);
        payroll.setBasicSalary(basicSalary);
        payroll.setOvertimePay(overtimePay);
        payroll.setDeductions(deductions);
        payroll.setNetSalary(netSalary);
    }
	
    @Override
    public Payroll getPayrollById(int payrollId) {
        Payroll payroll = null;
        String sql = "SELECT * FROM Payroll WHERE PayrollID = ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, payrollId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    payroll = new Payroll();
                    payroll.setPayrollID(resultSet.getInt("PayrollID"));
                    payroll.setEmployeeID(resultSet.getInt("EmployeeID"));
                    payroll.setPayPeriodStartDate(resultSet.getDate("PayPeriodStartDate").toLocalDate());
                    payroll.setPayPeriodEndDate(resultSet.getDate("PayPeriodEndDate").toLocalDate());
                    payroll.setBasicSalary(resultSet.getDouble("BasicSalary"));
                    payroll.setOvertimePay(resultSet.getDouble("OvertimePay"));
                    payroll.setDeductions(resultSet.getDouble("Deductions"));
                    payroll.setNetSalary(resultSet.getDouble("NetSalary"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payroll;
    }

    @Override
    public List<Payroll> getPayrollsForEmployee(int employeeId) {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM Payroll WHERE EmployeeID = ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Payroll payroll = new Payroll();
                    payroll.setPayrollID(resultSet.getInt("PayrollID"));
                    payroll.setEmployeeID(resultSet.getInt("EmployeeID"));
                    payroll.setPayPeriodStartDate(resultSet.getDate("PayPeriodStartDate").toLocalDate());
                    payroll.setPayPeriodEndDate(resultSet.getDate("PayPeriodEndDate").toLocalDate());
                    payroll.setBasicSalary(resultSet.getDouble("BasicSalary"));
                    payroll.setOvertimePay(resultSet.getDouble("OvertimePay"));
                    payroll.setDeductions(resultSet.getDouble("Deductions"));
                    payroll.setNetSalary(resultSet.getDouble("NetSalary"));
                    payrolls.add(payroll);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }

    @Override
    public List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate) {
        List<Payroll> payrolls = new ArrayList<>();
        String sql = "SELECT * FROM Payroll WHERE PayPeriodStartDate >= ? AND PayPeriodEndDate <= ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(startDate));
            statement.setDate(2, java.sql.Date.valueOf(endDate));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Payroll payroll = new Payroll();
                    payroll.setPayrollID(resultSet.getInt("PayrollID"));
                    payroll.setEmployeeID(resultSet.getInt("EmployeeID"));
                    payroll.setPayPeriodStartDate(resultSet.getDate("PayPeriodStartDate").toLocalDate());
                    payroll.setPayPeriodEndDate(resultSet.getDate("PayPeriodEndDate").toLocalDate());
                    payroll.setBasicSalary(resultSet.getDouble("BasicSalary"));
                    payroll.setOvertimePay(resultSet.getDouble("OvertimePay"));
                    payroll.setDeductions(resultSet.getDouble("Deductions"));
                    payroll.setNetSalary(resultSet.getDouble("NetSalary"));
                    payrolls.add(payroll);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }
    
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

    public void processPayroll() {
        
        List<Employee> employees = getAllEmployees();
        
        for (Employee employee : employees) {
            try {
                generatePayroll(employee.getEmployeeID(), LocalDate.now(), LocalDate.now().plusDays(30));
            } catch (PayrollGenerationException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void processPayrollForMultipleEmployees(List<Employee> employees) throws PayrollGenerationException {
        for (Employee employee : employees) {
            try {
                generatePayroll(employee.getEmployeeID(), LocalDate.now(), LocalDate.now().plusDays(30));
            } catch (PayrollGenerationException e) {     
                System.err.println("Error generating payroll for employee ID " + employee.getEmployeeID() + ": " + e.getMessage());
            }
        }
    }

    public int getTotalPayrollCount() {
        int totalPayrollCount = 0;
        String sql = "SELECT COUNT(*) AS TotalCount FROM Payroll";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                totalPayrollCount = resultSet.getInt("TotalCount");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalPayrollCount;
    }

}

