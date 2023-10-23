/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Base;
/*
 * The `Component` class is an abstract base class that represents a generic component in a system.
 * It provides common properties such as 'id' and 'name' for identifying and describing components.
 * Subclasses of `Component` can be created to represent specific types of components and
 * implement the 'loadFromDatabase' method to load details from a database.
 *
 * @author kq635
 */
public abstract class Component {

    protected String id;
    protected String name;

    /**
     * Get the unique identifier of the component.
     *
     * @return The component's ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the unique identifier of the component.
     *
     * @param id The component's ID to set.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the name or description of the component.
     *
     * @return The component's name or description.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name or description of the component.
     *
     * @param name The component's name or description to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Constructor for creating a new component with an ID and a name.
     *
     * @param id The unique identifier of the component.
     * @param name The name or description of the component.
     */
    public Component(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Abstract method to be implemented by subclasses for loading specific
     * component details from a database.
     */
    public abstract void loadFromDatabase();
}
