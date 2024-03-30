package dao;

import entity.model.Tax;
import util.DatabaseContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaxService implements ITaxService {
    @Override
    public double calculateTax(int employeeId, int taxYear) {
        double totalTax = 0;
        String sql = "SELECT TaxAmount FROM Tax WHERE EmployeeID = ? AND TaxYear = ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            statement.setInt(2, taxYear);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    totalTax += resultSet.getDouble("TaxAmount");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return totalTax;
    }


    @Override
    public Tax getTaxById(int taxId) {
        Tax tax = null;
        String sql = "SELECT * FROM Tax WHERE TaxID = ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taxId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    tax = new Tax();
                    tax.setTaxID(resultSet.getInt("TaxID"));
                    tax.setEmployeeID(resultSet.getInt("EmployeeID"));
                    tax.setTaxYear(resultSet.getInt("TaxYear"));
                    tax.setTaxableIncome(resultSet.getDouble("TaxableIncome"));
                    tax.setTaxAmount(resultSet.getDouble("TaxAmount"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tax;
    }

    @Override
    public List<Tax> getTaxesForEmployee(int employeeId) {
        List<Tax> taxes = new ArrayList<>();
        String sql = "SELECT * FROM Tax WHERE EmployeeID = ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tax tax = new Tax();
                    tax.setTaxID(resultSet.getInt("TaxID"));
                    tax.setEmployeeID(resultSet.getInt("EmployeeID"));
                    tax.setTaxYear(resultSet.getInt("TaxYear"));
                    tax.setTaxableIncome(resultSet.getDouble("TaxableIncome"));
                    tax.setTaxAmount(resultSet.getDouble("TaxAmount"));
                    taxes.add(tax);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taxes;
    }

    @Override
    public List<Tax> getTaxesForYear(int taxYear) {
        List<Tax> taxes = new ArrayList<>();
        String sql = "SELECT * FROM Tax WHERE TaxYear = ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, taxYear);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tax tax = new Tax();
                    tax.setTaxID(resultSet.getInt("TaxID"));
                    tax.setEmployeeID(resultSet.getInt("EmployeeID"));
                    tax.setTaxYear(resultSet.getInt("TaxYear"));
                    tax.setTaxableIncome(resultSet.getDouble("TaxableIncome"));
                    tax.setTaxAmount(resultSet.getDouble("TaxAmount"));
                    taxes.add(tax);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return taxes;
    }
}
