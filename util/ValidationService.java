package util;

import java.time.LocalDate;

public class ValidationService {
	
	public static boolean validateEmployeeData(String firstName, String lastName, LocalDate dateOfBirth, String gender,
	                                               String email, String phoneNumber, String address, String position,
	                                               LocalDate joiningDate) {
	        return firstName != null && !firstName.isEmpty() &&
	               lastName != null && !lastName.isEmpty() &&
	               dateOfBirth != null &&
	               gender != null && !gender.isEmpty() &&
	               email != null && !email.isEmpty() &&
	               phoneNumber != null && !phoneNumber.isEmpty() &&
	               address != null && !address.isEmpty() &&
	               position != null && !position.isEmpty() &&
	               joiningDate != null;
	    }

	    
	 public static boolean validatePayrollData(LocalDate payPeriodStartDate, LocalDate payPeriodEndDate,
	                                              double basicSalary, double overtimePay, double deductions,
	                                              double netSalary) {
	        return payPeriodStartDate != null &&
	               payPeriodEndDate != null &&
	               basicSalary >= 0 &&
	               overtimePay >= 0 &&
	               deductions >= 0 &&
	               netSalary >= 0;
	    }

	    
	  public static boolean validateTaxData(int taxYear, double taxableIncome, double taxAmount) {
	        return taxYear > 0 &&
	               taxableIncome >= 0 &&
	               taxAmount >= 0;
	    }

	    
	  public static boolean validateFinancialRecordData(LocalDate recordDate, String description,
	                                                       double amount, String recordType) {
	        return recordDate != null &&
	               description != null && !description.isEmpty() &&
	               amount >= 0 &&
	               recordType != null && !recordType.isEmpty();
	    }
	}



