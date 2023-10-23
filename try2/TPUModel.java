/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package try2;

/**
 *
 * @author kq635
 */
public class TPUModel extends GPUModel {

    // Constructs a TPUModel with provided attributes.
    public TPUModel(String id, String Type, String speed) {
        super(id, Type, speed, Type); // Pass Type for both name and classification
    }

    // Get the unique identifier of the TPU.
    public String getId() {
        return id;
    }

    // Get the name of the TPU.
    public String getName() {
        return name;
    }

    // Set the classification of the TPU.
    @Override
    public void setClassification(String classification) {
        super.setClassification(classification);
    }

    // Get the classification of the TPU.
    @Override
    public String getClassification() {
        return super.getClassification();
    }

    // Constructs a default TPUModel with placeholder values.
    public TPUModel() {
        super("defaultID", "defaultType", "defaultSpeed", null); // Pass null for classification
    }
}
