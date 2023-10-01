/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PcQnA;

/**
 * some methods such as readAndPrintSection are no longer "needed". it is still
 * needed cause it will bug the other methods if delete, but its not really used
 * in the sub classes, instead there is now a fileUtility class that does some
 * or most of these stuff. (read & print file etc). some are still needed like
 * printbriefinfo because it makes more sense that its in pc.
 *
 * @author kq635
 */
public abstract class Pc {

  
    // Abstract methods for printing brief information about a PC component
    protected abstract void printBriefInfo();

    // Abstract methods for printing full information about a PC component
    protected abstract void printFullInfo();

    // Abstract methods for printing types or categories of a PC component
    protected abstract void printTypes();

    // Abstract methods for printing recommendations about a PC component
    protected abstract void printRecommendation();

   
    // Abstract method to start and control the functionality of a PC component
    protected abstract void start();
}
