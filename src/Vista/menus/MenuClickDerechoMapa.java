/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.menus;

import Controlador.ControladorGeneral;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Bejaranos
 */
public class MenuClickDerechoMapa extends JPopupMenu implements ActionListener
{

        private JMenuItem obtenerGeoPunto;
        private JMenuItem verCoordenada;
        private ControladorGeneral controlador;
        
        private double latitudTemporal;
        private double longitudTemporal;
        private double altitudTemporal;
        
        public MenuClickDerechoMapa(ControladorGeneral controlador){

            this.controlador = controlador;
            
            obtenerGeoPunto = new JMenuItem("Obtener Punto");
            obtenerGeoPunto.setIcon(new ImageIcon("src/Images/iconos16x16/arrow-curve-270-left.png"));
            obtenerGeoPunto.addActionListener(this);
            
            verCoordenada = new JMenuItem("Ver Coordenada");
            verCoordenada.addActionListener(this);
            
            this.add(obtenerGeoPunto);
            this.add(verCoordenada);
        }
        
        public void setObtenerGeoPunto(double latitud, double longitud, double altitud){
            this.latitudTemporal = latitud;
            this.longitudTemporal = longitud;
            this.altitudTemporal = altitud;
        }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Obtener Punto"))
        {
            controlador.getLibroDeProyectos().getMapa().setObtenerPunto(latitudTemporal, longitudTemporal, altitudTemporal);
        }
    }
}
