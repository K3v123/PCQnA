/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
public abstract class Component {

    protected String id;
    protected String name;

    // getters and setters 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Component(String id, String name) {
        this.id = id;
        this.name = name;
    }
    
    // Abstract method to load specific component details from the database.
    public abstract void loadFromDatabase();

}
