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

    public TPUModel(String id, String name, String speed) {
        super(id, name, speed, null); // Pass null for classification since TPU doesn't use it
    }

  
}
