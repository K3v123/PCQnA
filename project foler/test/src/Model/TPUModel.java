/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 * This class represents a model for TPU (Tensor Processing Unit) components. It
 * extends the GPUModel class, inheriting properties and methods.
 *
 * @author kq635
 */
public class TPUModel extends GPUModel {

    /**
     * Constructor for the TPUModel class.
     *
     * @param id The ID of the TPU.
     * @param Type The type of the TPU.
     * @param speed The speed of the TPU.
     */
    public TPUModel(String id, String Type, String speed) {
        super(id, Type, speed, Type); // Pass Type for both name and classification
    }

    /**
     * Getter method to retrieve the ID of the TPU.
     *
     * @return The ID of the TPU.
     */
    public String getId() {
        return id;
    }

    /**
     * Getter method to retrieve the name of the TPU.
     *
     * @return The name of the TPU.
     */
    public String getName() {
        return name;
    }

    /**
     * Override the setClassification method to set the classification of the
     * TPU.
     *
     * @param classification The classification of the TPU.
     */
    @Override
    public void setClassification(String classification) {
        super.setClassification(classification);
    }

    /**
     * Override the getClassification method to retrieve the classification of
     * the TPU.
     *
     * @return The classification of the TPU.
     */
    @Override
    public String getClassification() {
        return super.getClassification();
    }

    /**
     * Default constructor for the TPUModel class. Initializes with default
     * values.
     */
    public TPUModel() {
        super("defaultID", "defaultType", "defaultSpeed", null); // Pass null for classification
    }
}
