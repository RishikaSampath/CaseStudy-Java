package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseContext {

    public static void main(String[] args) {
        try (Connection connection = getConnection()) {
            createEmployeeTable(connection);
            createPayrollTable(connection);
            createTaxTable(connection);
            createFinancialRecordTable(connection);
            
            
            insertEmployeeValues(connection);
            insertPayrollValues(connection);
            insertTaxValues(connection);
            insertFinancialRecordValues(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public static Connection getConnection() throws SQLException {
        String DB_URL = "jdbc:mysql://localhost:3306/payxpert";
        String USER = "root";
        String PASSWORD = "Rishika@12"; 
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    
    private static void createEmployeeTable(Connection connection) throws SQLException {
        
        String sql = "CREATE TABLE IF NOT EXISTS Employee (" +
                "EmployeeID INT AUTO_INCREMENT PRIMARY KEY," +
                "FirstName VARCHAR(50) NOT NULL," +
                "LastName VARCHAR(50) NOT NULL," +
                "DateOfBirth DATE NOT NULL," +
                "Gender VARCHAR(10) NOT NULL," +
                "Email VARCHAR(100) NOT NULL," +
                "PhoneNumber VARCHAR(20) NOT NULL," +
                "Address VARCHAR(255) NOT NULL," +
                "Position VARCHAR(100) NOT NULL," +
                "JoiningDate DATE NOT NULL," +
                "TerminationDate DATE" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    
    private static void createPayrollTable(Connection connection) throws SQLException {
        
        String sql = "CREATE TABLE IF NOT EXISTS Payroll (" +
                "PayrollID INT AUTO_INCREMENT PRIMARY KEY," +
                "EmployeeID INT NOT NULL," +
                "PayPeriodStartDate DATE NOT NULL," +
                "PayPeriodEndDate DATE NOT NULL," +
                "BasicSalary DECIMAL(10, 2) NOT NULL," +
                "OvertimePay DECIMAL(10, 2) NOT NULL," +
                "Deductions DECIMAL(10, 2) NOT NULL," +
                "NetSalary DECIMAL(10, 2) NOT NULL," +
                "FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    
    private static void createTaxTable(Connection connection) throws SQLException {
        
        String sql = "CREATE TABLE IF NOT EXISTS Tax (" +
                "TaxID INT AUTO_INCREMENT PRIMARY KEY," +
                "EmployeeID INT NOT NULL," +
                "TaxYear YEAR NOT NULL," +
                "TaxableIncome DECIMAL(10, 2) NOT NULL," +
                "TaxAmount DECIMAL(10, 2) NOT NULL," +
                "FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    
    private static void createFinancialRecordTable(Connection connection) throws SQLException {
        
        String sql = "CREATE TABLE IF NOT EXISTS FinancialRecord (" +
                "RecordID INT AUTO_INCREMENT PRIMARY KEY," +
                "EmployeeID INT NOT NULL," +
                "RecordDate DATE NOT NULL," +
                "Description VARCHAR(255) NOT NULL," +
                "Amount DECIMAL(10, 2) NOT NULL," +
                "RecordType VARCHAR(50) NOT NULL," +
                "FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)" +
                ")";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    
    private static void insertEmployeeValues(Connection connection) throws SQLException {
        
        String sql = "INSERT INTO Employee (FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate, TerminationDate) " +
                "VALUES " +
                "('John', 'Doe', '1990-05-15', 'Male', 'john.doe@example.com', '1234567890', '123 Main St, City, Country', 'Manager', '2020-01-01', NULL), " +
                "('Jane', 'Smith', '1985-08-20', 'Female', 'jane.smith@example.com', '9876543210', '456 Oak St, City, Country', 'Developer', '2021-03-15', NULL), " +
                "('Alice', 'Johnson', '1993-11-10', 'Female', 'alice.johnson@example.com', '4561237890', '789 Elm St, City, Country', 'HR Assistant', '2019-07-10', '2022-06-30'), " +
                "('Bob', 'Williams', '1978-03-25', 'Male', 'bob.williams@example.com', '7894561230', '987 Maple St, City, Country', 'Analyst', '2018-02-01', NULL), " +
                "('Eva', 'Brown', '1982-09-18', 'Female', 'eva.brown@example.com', '3216549870', '654 Pine St, City, Country', 'Designer', '2020-11-05', NULL)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

   
    private static void insertPayrollValues(Connection connection) throws SQLException {
       
        String sql = "INSERT INTO Payroll (EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary) " +
                "VALUES " +
                "(1, '2022-01-01', '2022-01-15', 5000.00, 250.00, 400.00, 4850.00), " +
                "(2, '2022-01-01', '2022-01-15', 4500.00, 200.00, 350.00, 4350.00), " +
                "(3, '2022-01-01', '2022-01-15', 4800.00, 300.00, 420.00, 4580.00), " +
                "(4, '2022-01-01', '2022-01-15', 5200.00, 350.00, 450.00, 5000.00), " +
                "(5, '2022-01-01', '2022-01-15', 4700.00, 220.00, 380.00, 4500.00)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    
    private static void insertTaxValues(Connection connection) throws SQLException {
        
        String sql = "INSERT INTO Tax (EmployeeID, TaxYear, TaxableIncome, TaxAmount) " +
                "VALUES " +
                "(1, 2022, 50000.00, 7500.00), " +
                "(2, 2022, 48000.00, 7200.00), " +
                "(3, 2022, 52000.00, 7800.00), " +
                "(4, 2022, 55000.00, 8250.00), " +
                "(5, 2022, 49000.00, 7350.00)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    
    private static void insertFinancialRecordValues(Connection connection) throws SQLException {
        
        String sql = "INSERT INTO FinancialRecord (EmployeeID, RecordDate, Description, Amount, RecordType) " +
                "VALUES " +
                "(1, '2022-01-05', 'Bonus', 1000.00, 'Income'), " +
                "(2, '2022-01-10', 'Overtime Payment', 150.00, 'Income'), " +
                "(3, '2022-01-08', 'Travel Expense', 200.00, 'Expense'), " +
                "(4, '2022-01-12', 'Medical Allowance', 300.00, 'Income'), " +
                "(5, '2022-01-07', 'Rent Payment', 800.00, 'Expense')";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }
}
