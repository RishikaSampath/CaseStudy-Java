package dao;

import entity.model.Tax;
import exception.TaxCalculationException;

import java.util.List;

public interface ITaxService {
    double calculateTax(int employeeId, int taxYear) throws TaxCalculationException;
    Tax getTaxById(int taxId);
    List<Tax> getTaxesForEmployee(int employeeId);
    List<Tax> getTaxesForYear(int taxYear);
}
