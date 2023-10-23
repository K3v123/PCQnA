/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
public class DatabaseController implements DatabaseOperations {

    private DatabaseManager dbManager;

    public DatabaseController() {
        this.dbManager = new DatabaseManager();
    }

    @Override
    public void addToDatabase(Component component) {
        String query = "INSERT INTO components (id, name) VALUES ('"
                + component.getId() + "', '"
                + component.getName() + "')";
        int rowsAffected = dbManager.executeUpdate(query);
        if (rowsAffected <= 0) {
            System.out.println("Error adding component.");
        }
    }

    @Override
    public void editInDatabase(String componentId, Component updatedComponent) {
        String query = "UPDATE components SET name = '"
                + updatedComponent.getName() + "' WHERE id = '"
                + componentId + "'";
        int rowsAffected = dbManager.executeUpdate(query);
        if (rowsAffected <= 0) {
            System.out.println("Error updating component.");
        }
    }

    @Override
    public void deleteFromDatabase(String componentId) {
        String query = "DELETE FROM components WHERE id = '" + componentId + "'";
        int rowsAffected = dbManager.executeUpdate(query);
        if (rowsAffected <= 0) {
            System.out.println("Error deleting component.");
        }
    }

    // Additional methods for more functionality can be added here...
}
