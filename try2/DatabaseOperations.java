/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */

public interface DatabaseOperations {

    void addToDatabase(Component component);

    void editInDatabase(String componentId, Component updatedComponent);

    void deleteFromDatabase(String componentId);
}
