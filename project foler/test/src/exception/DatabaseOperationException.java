/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package exception;
/**
 * Custom exception class for database operation-related exceptions.
 *
 * @author kq635
 */
public class DatabaseOperationException extends Exception {

    /**
     * Constructs a new DatabaseOperationException with the specified error
     * message.
     *
     * @param message The error message associated with the exception.
     */
    public DatabaseOperationException(String message) {
        super(message);
    }

    /**
     * Constructs a new DatabaseOperationException with the specified error
     * message and cause.
     *
     * @param message The error message associated with the exception.
     * @param cause The cause of the exception (e.g., another exception).
     */
    public DatabaseOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
