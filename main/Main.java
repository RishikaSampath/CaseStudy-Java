package main;

import java.util.Scanner;
import java.util.List;
import dao.EmployeeService;
import dao.PayrollService;
import dao.TaxService;
import dao.FinancialRecordService;
import entity.model.Employee;
import util.ReportGenerator;
import entity.model.Tax;
import exception.EmployeeNotFoundException;


public class Main {
    private EmployeeService employeeService;
    private PayrollService payrollService;
    private TaxService taxService;
    @SuppressWarnings("unused")
	private FinancialRecordService financialrecordService;
    private Scanner scanner;

    public Main() {
        employeeService = new EmployeeService();
        payrollService = new PayrollService();
        taxService = new TaxService();
        financialrecordService = new FinancialRecordService();
        scanner = new Scanner(System.in);
    }
    
    public FinancialRecordService getFinancialRecordService() {
		return getFinancialRecordService();
	}

	public void setFinancialRecordService(FinancialRecordService financialRecordService) {
		this.financialrecordService = financialRecordService;
	}


    public void start() throws EmployeeNotFoundException {
        int choice;
        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    manageEmployees();
                    break;
                case 2:
                    processPayroll();
                    break;
                case 3:
                    calculateTaxes();
                    break;
                case 4:
                    generateReports();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void displayMenu() {
        System.out.println("===== Main Menu =====");
        System.out.println("1. Manage Employees");
        System.out.println("2. Process Payroll");
        System.out.println("3. Calculate Taxes");
        System.out.println("4. Generate Reports");
        System.out.println("5. Exit");
        System.out.println("=====================");
    }

    private void manageEmployees() throws EmployeeNotFoundException {
        int choice;
        do {
            displayEmployeeMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    updateEmployee();
                    break;
                case 3:
                    removeEmployee();
                    break;
                case 4:
                    viewEmployeeDetails();
                    break;
                case 5:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void displayEmployeeMenu() {
        System.out.println("===== Employee Management Menu =====");
        System.out.println("1. Add Employee");
        System.out.println("2. Update Employee");
        System.out.println("3. Delete Employee");
        System.out.println("4. View Employee Details");
        System.out.println("5. Return to Main Menu");
        System.out.println("====================================");
    }

    private void addEmployee() {
        System.out.println("Adding a new employee...");
        Employee employee = new Employee(); 
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter employee first name: ");
        String firstName = scanner.nextLine();
        employee.setEmployeeID(employeeId);
        employee.setFirstName(firstName);
        employeeService.addEmployee(employee);
        System.out.println("Employee added successfully.");
    }

    private void updateEmployee() throws EmployeeNotFoundException {
        System.out.println("Updating employee details...");
        System.out.print("Enter employee ID to update: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); 
        Employee employee = employeeService.getEmployeeById(employeeId);
        if (employee != null) {
            
            System.out.print("Enter new first name: ");
            String newFirstName = scanner.nextLine();
            System.out.print("Enter new last name: ");
            String newLastName = scanner.nextLine();
            
            employee.setFirstName(newFirstName);
            employee.setLastName(newLastName);
            
            employeeService.updateEmployee(employee);
            System.out.println("Employee details updated successfully.");
        } else {
            System.out.println("Employee not found with ID: " + employeeId);
        }
    }

    private void removeEmployee() throws EmployeeNotFoundException {
        System.out.println("Deleting an employee...");
        System.out.print("Enter employee ID to delete: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        employeeService.removeEmployee(employeeId);
        System.out.println("Employee deleted successfully.");
    }

    private void viewEmployeeDetails() {
        System.out.print("Enter employee ID: ");
        int employeeId = scanner.nextInt();
        scanner.nextLine(); 

        try {
            Employee employee = employeeService.getEmployeeById(employeeId);
            
            if (employee != null) {
                System.out.println("Employee Details:");
                System.out.println("ID: " + employee.getEmployeeID());
                System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
            } else {
                
                System.out.println("Employee not found with ID: " + employeeId);
            }
        } catch (EmployeeNotFoundException e) {
            
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            
            System.out.println("An unexpected error occurred while retrieving employee details.");
            e.printStackTrace(); 
        }
    }


    private void processPayroll() {
        System.out.println("Processing payroll...");
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            for (Employee employee : employees) {
                payrollService.processPayroll();
                System.out.println("Payroll processed successfully for Employee ID " + employee.getEmployeeID() + ": " + employee.getFirstName() + " " + employee.getLastName());
            }
            System.out.println("Payroll processed successfully.");
        } catch (Exception e) {
            System.out.println("Error generating payroll: " + e.getMessage());
        }
    }




    private void calculateTaxes() {
        System.out.println("Calculating taxes...");
        List<Tax> taxes = taxService.getTaxesForYear(2022); 
        if (taxes.isEmpty()) {
            System.out.println("No taxes found for the specified year.");
        } else {
            System.out.println("Tax Report:");
            System.out.println("----------------------------------------");
            System.out.println("Tax ID | Employee ID | Tax Year | Taxable Income | Tax Amount");
            System.out.println("----------------------------------------");
            for (Tax tax : taxes) {
                System.out.println(String.format("%-7d | %-11d | %-8d | %-14.2f | %-10.2f", tax.getTaxID(),
                        tax.getEmployeeID(), tax.getTaxYear(), tax.getTaxableIncome(), tax.getTaxAmount()));
            }
            System.out.println("Taxes calculated successfully.");
        }
    }


    private void generateReports() {
        System.out.println("Generating reports...");

        
        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine(); 
        ReportGenerator.generatePayrollReport(employeeID);
        ReportGenerator.generateTaxReport(employeeID);
        ReportGenerator.generateFinancialRecordReport(employeeID);

        System.out.println("Reports generated successfully.");
    }


    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.start();
        } catch (EmployeeNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

	
}
