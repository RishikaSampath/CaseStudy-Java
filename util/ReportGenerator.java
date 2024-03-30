package util;

import java.util.List;

import dao.FinancialRecordService;
import dao.PayrollService;
import dao.TaxService;
import entity.model.FinancialRecord;
import entity.model.Payroll;
import entity.model.Tax;

public class ReportGenerator {
	
	public static void generatePayrollReport(int employeeID) {
        PayrollService payrollService = new PayrollService();
        List<Payroll> payrolls = payrollService.getPayrollsForEmployee(employeeID);

        System.out.println("Payroll Report for Employee ID: " + employeeID);
        System.out.println("----------------------------------------");
        System.out.println("Payroll ID | Employee ID | Start Date | End Date | Basic Salary | Overtime Pay | Deductions | Net Salary");
        System.out.println("----------------------------------------");
        for (Payroll payroll : payrolls) {
            System.out.println(String.format("%-10d | %-11d | %-10s | %-8s | %-12.2f | %-12.2f | %-10.2f | %-10.2f",
                    payroll.getPayrollID(), payroll.getEmployeeID(), payroll.getPayPeriodStartDate(),
                    payroll.getPayPeriodEndDate(), payroll.getBasicSalary(), payroll.getOvertimePay(),
                    payroll.getDeductions(), payroll.getNetSalary()));
        }
    }
	
	public static void generateTaxReport(int employeeID) {
        TaxService taxService = new TaxService();
        List<Tax> taxes = taxService.getTaxesForEmployee(employeeID);

        System.out.println("Tax Report for Employee ID: " + employeeID);
        System.out.println("----------------------------------------");
        System.out.println("Tax ID | Employee ID | Tax Year | Taxable Income | Tax Amount");
        System.out.println("----------------------------------------");
        for (Tax tax : taxes) {
            System.out.println(String.format("%-7d | %-11d | %-8d | %-14.2f | %-10.2f", tax.getTaxID(),
                    tax.getEmployeeID(), tax.getTaxYear(), tax.getTaxableIncome(), tax.getTaxAmount()));
        }
    }

    
	public static void generateFinancialRecordReport(int employeeID) {
        FinancialRecordService financialRecordService = new FinancialRecordService();
        List<FinancialRecord> financialRecords = financialRecordService.getFinancialRecordsForEmployee(employeeID);

        System.out.println("Financial Record Report for Employee ID: " + employeeID);
        System.out.println("----------------------------------------");
        System.out.println("Record ID | Employee ID | Record Date | Description | Amount | Record Type");
        System.out.println("----------------------------------------");
        for (FinancialRecord record : financialRecords) {
            System.out.println(String.format("%-10d | %-11d | %-11s | %-12s | %-7.2f | %-11s", record.getRecordID(),
                    record.getEmployeeID(), record.getRecordDate(), record.getDescription(), record.getAmount(),
                    record.getRecordType()));
        }
    }
}
