/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Libreria;

import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.avlist.AVKey;
import javax.swing.JFrame;

/**
 *
 * @author Bejaranos
 */
public class WWHelper {

    private WWHelper()
    {
        
    }

    public static void setup(final WorldWindow ww)
    {
        Model m = (Model) WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
        ww.setModel(m);
    }
}
