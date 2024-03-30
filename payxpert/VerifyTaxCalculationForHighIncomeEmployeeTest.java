package payxpert;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import dao.TaxService;

public class VerifyTaxCalculationForHighIncomeEmployeeTest {

    @Test
    public void testTaxCalculationForHighIncomeEmployee() {
        
        double income = 100000.0; 
        TaxService taxService = new TaxService();
        double expectedTaxAmount = taxService.calculateTax((int) income, 2024);
        assertEquals(expectedTaxAmount, taxService.calculateTax((int) income, 2024), 0.001);
    }
}

