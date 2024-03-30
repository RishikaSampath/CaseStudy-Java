package entity.model;

import java.time.LocalDate;

public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String email;
    private String phoneNumber;
    private String address;
    private String position;
    private LocalDate joiningDate;
    private LocalDate terminationDate;
    private double basicSalary;
    private double overtimePay;
    private double bonus;
    
    public Employee() {
    	
    }
    
    public Employee(String firstName, String lastName, double basicSalary, double overtimePay, double bonus) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.basicSalary = basicSalary;
        this.overtimePay = overtimePay;
        this.bonus = bonus;
    }

    // Method to calculate gross salary
    public double calculateGrossSalary() {
        return basicSalary + overtimePay + bonus;
    }

    
    public Employee(int employeeID, String firstName, String lastName, LocalDate dateOfBirth, String gender,
                    String email, String phoneNumber, String address, String position, LocalDate joiningDate,
                    LocalDate terminationDate) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.position = position;
        this.joiningDate = joiningDate;
        this.terminationDate = terminationDate;
    }
    
    public double getBasicSalary() {
    	return basicSalary;
    }
    
    public double getOvertimePay() {
    	return overtimePay;
    }
    
    public double getBonus() {
    	return bonus;
    }

    
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }

    public LocalDate getTerminationDate() {
        return terminationDate;
    }

    public void setTerminationDate(LocalDate terminationDate) {
        this.terminationDate = terminationDate;
    }

    
    public int calculateAge() {
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - dateOfBirth.getYear();
        if (dateOfBirth.getMonthValue() > currentDate.getMonthValue() ||
            (dateOfBirth.getMonthValue() == currentDate.getMonthValue() &&
             dateOfBirth.getDayOfMonth() > currentDate.getDayOfMonth())) {
            age--;
        }
        return age;
    }
}
