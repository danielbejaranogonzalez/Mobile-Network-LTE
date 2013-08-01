/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.SIM.Outdoor.Paths;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class Path implements Serializable{
    
    private int numeroPath;
    
    public Path(int numeroPath){
        this.numeroPath = numeroPath;;
    }

    /**
     * @return the numeroPath
     */
    public int getNumeroPath() {
        return numeroPath;
    }
}
