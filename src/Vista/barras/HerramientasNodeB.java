/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.barras;

import Controlador.ControladorGeneral;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;

/**
 *
 * @author Usuario
 */
public class HerramientasNodeB extends JToolBar implements ActionListener{
    
    private ControladorGeneral controlador;
    
    private JButton AdministracionEquiposDeRadio;
    private JButton AdministracionAntenas;
    private JButton AdministracionAteuadores;
    private JButton enlaceIndoor;
    
    public HerramientasNodeB(ControladorGeneral controlador){
        this.controlador = controlador;
        this.setFloatable(true);
        this.setName("Barra de Herramientas de eNodeB");
        
        AdministracionEquiposDeRadio = new JButton();
	AdministracionEquiposDeRadio.setIcon(new ImageIcon("src/Images/iconos32x32/stanchion.png"));
	AdministracionEquiposDeRadio.setToolTipText("Administracion de Equipos de Radio");
	AdministracionEquiposDeRadio.setActionCommand("Administracion de Equipos de Radio");
        AdministracionEquiposDeRadio.addActionListener(this);
        
        AdministracionAntenas = new JButton();
	AdministracionAntenas.setIcon(new ImageIcon("src/Images/iconos32x32/satellite_dish.png"));
	AdministracionAntenas.setToolTipText("Administración de Antenas");
	AdministracionAntenas.setActionCommand("Administración de Antenas");
        AdministracionAntenas.addActionListener(this);
        
        AdministracionAteuadores = new JButton();
	AdministracionAteuadores.setIcon(new ImageIcon("src/Images/iconos32x32/port.png"));
	AdministracionAteuadores.setToolTipText("Administración de Atenuadores");
	AdministracionAteuadores.setActionCommand("Administración de Atenuadores");
        AdministracionAteuadores.addActionListener(this);
        
        enlaceIndoor = new JButton();
	enlaceIndoor.setIcon(new ImageIcon("src/Images/iconos32x32/connect2.png"));
	enlaceIndoor.setToolTipText("Gestion de Enlace Indoor");
	enlaceIndoor.setActionCommand("Gestion de Enlace Indoor");
        enlaceIndoor.addActionListener(this);
        
        add(AdministracionEquiposDeRadio);
        add(AdministracionAntenas);
        add(AdministracionAteuadores);
        add(enlaceIndoor);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Administracion de Equipos de Radio"))
        {
            controlador.getControladorBotones().crearVentanaGestionEquiposRadio();
        }if(e.getActionCommand().equals("Administración de Antenas"))
         {
             controlador.getControladorBotones().crearVenatanGestionDeAntenas();
         }if(e.getActionCommand().equals("Administración de Atenuadores"))
           {
               controlador.getControladorBotones().crearVenatanaGestionAtenuadores();
           }if(e.getActionCommand().equals("Gestion de Enlace Indoor"))
            {
               controlador.getControladorBotones().crearVenatanaGestionIndoorLink();
            }
    }
}
