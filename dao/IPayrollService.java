package dao;

import entity.model.Payroll;
import exception.PayrollGenerationException;

import java.time.LocalDate;
import java.util.List;

public interface IPayrollService {
    void generatePayroll(int employeeId, LocalDate startDate, LocalDate endDate) throws PayrollGenerationException;
    Payroll getPayrollById(int payrollId);
    List<Payroll> getPayrollsForEmployee(int employeeId);
    List<Payroll> getPayrollsForPeriod(LocalDate startDate, LocalDate endDate);
}
