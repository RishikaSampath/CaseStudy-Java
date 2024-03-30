package dao;

import entity.model.FinancialRecord;
import util.DatabaseContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FinancialRecordService implements IFinancialRecordService {
	@Override
    public void addFinancialRecord(int employeeId, String description, double amount, String recordType) {
        String sql = "INSERT INTO FinancialRecord (EmployeeID, RecordDate, Description, Amount, RecordType) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            statement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));
            statement.setString(3, description);
            statement.setDouble(4, amount);
            statement.setString(5, recordType);
            statement.executeUpdate();
            System.out.println("Financial record added successfully.");
        } catch (SQLException e) {
            System.err.println("Error adding financial record: " + e.getMessage());
        }
    }


    @Override
    public FinancialRecord getFinancialRecordById(int recordId) {
        FinancialRecord financialRecord = null;
        String sql = "SELECT * FROM FinancialRecord WHERE RecordID = ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, recordId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    financialRecord = new FinancialRecord();
                    financialRecord.setRecordID(resultSet.getInt("RecordID"));
                    financialRecord.setEmployeeID(resultSet.getInt("EmployeeID"));
                    financialRecord.setRecordDate(resultSet.getDate("RecordDate").toLocalDate());
                    financialRecord.setDescription(resultSet.getString("Description"));
                    financialRecord.setAmount(resultSet.getDouble("Amount"));
                    financialRecord.setRecordType(resultSet.getString("RecordType"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return financialRecord;
    }

    @Override
    public List<FinancialRecord> getFinancialRecordsForEmployee(int employeeId) {
        List<FinancialRecord> financialRecords = new ArrayList<>();
        String sql = "SELECT * FROM FinancialRecord WHERE EmployeeID = ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, employeeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    FinancialRecord financialRecord = new FinancialRecord();
                    financialRecord.setRecordID(resultSet.getInt("RecordID"));
                    financialRecord.setEmployeeID(resultSet.getInt("EmployeeID"));
                    financialRecord.setRecordDate(resultSet.getDate("RecordDate").toLocalDate());
                    financialRecord.setDescription(resultSet.getString("Description"));
                    financialRecord.setAmount(resultSet.getDouble("Amount"));
                    financialRecord.setRecordType(resultSet.getString("RecordType"));
                    financialRecords.add(financialRecord);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return financialRecords;
    }
    
    
    @Override
    public List<FinancialRecord> getFinancialRecordsForDate(LocalDate recordDate) {
        List<FinancialRecord> financialRecords = new ArrayList<>();
        String sql = "SELECT * FROM FinancialRecord WHERE RecordDate = ?";
        try (Connection connection = DatabaseContext.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, java.sql.Date.valueOf(recordDate));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    FinancialRecord financialRecord = new FinancialRecord();
                    financialRecord.setRecordID(resultSet.getInt("RecordID"));
                    financialRecord.setEmployeeID(resultSet.getInt("EmployeeID"));
                    financialRecord.setRecordDate(resultSet.getDate("RecordDate").toLocalDate());
                    financialRecord.setDescription(resultSet.getString("Description"));
                    financialRecord.setAmount(resultSet.getDouble("Amount"));
                    financialRecord.setRecordType(resultSet.getString("RecordType"));
                    financialRecords.add(financialRecord);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return financialRecords;
    }
}

