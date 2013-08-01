package Libreria;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JApplet;


public class CentrarScreen 
{
    /**
     * Centra una ventana en la pantalla
     * @param ventana La ventana que se va a centrar de tipo JFrame
     */
    public static void centrarVentana( JFrame ventana )
    {
        Dimension dPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        Dimension dVentana = ventana.getSize( );

        int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
        int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );

        ventana.setLocation( xEsquina, yEsquina );
    }
   
    /**
     * Centra una ventana en la pantalla
     * @param ventana La ventana que se va a centrar de tipo JDialog
     */
    public static void centrarVentana( JDialog ventana )
    {
        Dimension dPantalla = Toolkit.getDefaultToolkit( ).getScreenSize( );
        Dimension dVentana = ventana.getSize( );

        int xEsquina = ( dPantalla.width / 2 ) - ( dVentana.width / 2 );
        int yEsquina = ( dPantalla.height / 2 ) - ( dVentana.height / 2 );

        ventana.setLocation( xEsquina, yEsquina );
    }
	
}
