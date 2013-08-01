/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Libreria;

/**
 *
 * @author User
 */
public class Delay {
    
    
    public static void generarDelayAritmetico(int valor){
        double i =0;
        while(i < valor){
            Thread.yield();
            Thread.yield();
            i++;
       }
    }
    
}
