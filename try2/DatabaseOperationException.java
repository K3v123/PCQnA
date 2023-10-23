/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;
/**
 *
 * @author kq635
 */

// Custom exception class for handling database operation errors.
public class DatabaseOperationException extends Exception {
    
    // Constructs a new DatabaseOperationException with the specified detail message.
    public DatabaseOperationException(String message) {
        super(message);
    }

    // Constructs a new DatabaseOperationException with the specified detail message and cause.
    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
