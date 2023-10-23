/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MostAskedDao {

    private Connection conn;

    // Initializes the MostAskedDao with a database connection.
    public MostAskedDao(Connection connection)  throws DatabaseOperationException {
        this.conn = connection;
    }

    // Retrieves the count for a given option name from the MostAsked table in the database.
    public int getMostAskedCountForOption(String optionName)  throws DatabaseOperationException {
        String query = "SELECT optionName, count FROM MostAsked WHERE OptionName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, optionName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Database operation failed.", e);
        }
        return 0;
    }

    // Retrieves the top 5 most asked options from the MostAsked table in the database.
    public List<MostAsked> getTop5MostAsked()  throws DatabaseOperationException {
        List<MostAsked> mostAskedList = new ArrayList<>();
        String query = "SELECT OptionName, Count FROM MostAsked ORDER BY Count DESC FETCH FIRST 5 ROWS ONLY";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MostAsked mostAsked = new MostAsked(rs.getString("OptionName"), rs.getInt("Count"));
                mostAskedList.add(mostAsked);
            }
        } catch (SQLException e) {
            throw new DatabaseOperationException("Database operation failed.", e);
        }
        return mostAskedList;
    }

    // Inserts a new option with a count of 1 into the MostAsked table in the database.
    public void insertNewOption(String optionName)  throws DatabaseOperationException {
        String query = "INSERT INTO MostAsked (OptionName, Count) VALUES (?, 1)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, optionName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Database operation failed.", e);
        }
    }

    // Increments the count of an option in the MostAsked table in the database.
    public void incrementOptionCount(String optionName)  throws DatabaseOperationException {
        String query = "UPDATE MostAsked SET Count = Count + 1 WHERE OptionName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, optionName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DatabaseOperationException("Database operation failed.", e);
        }
    }
}