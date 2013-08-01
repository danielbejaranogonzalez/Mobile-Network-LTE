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
public class HerramientasProyecto extends JToolBar implements ActionListener{

    private JButton nuevoSitio;
    private JButton eliminarSitio;
    private JButton editarSitio;
    
    private JButton nuevaRAN;
    private JButton eliminarRAN;
    private JButton editarRAN;
    
    private JButton nuevoENodeB;
    private JButton editarENodeB;
    private JButton eliminarENodeB;
    
    private JButton nuevoEU;
    private JButton nuevoOutdoorLink;
    private ControladorGeneral controlador;
    
    public HerramientasProyecto(ControladorGeneral controlador){
        
        this.controlador = controlador;
        this.setFloatable(true);
        
        nuevoSitio = new JButton();
	nuevoSitio.setIcon(new ImageIcon("src/Images/iconos32x32/building_add.png"));
	nuevoSitio.setToolTipText("Nuevo Sitio");
	nuevoSitio.setActionCommand("Nuevo Sitio");
        nuevoSitio.addActionListener(this);
        
        eliminarSitio = new JButton();
	eliminarSitio.setIcon(new ImageIcon("src/Images/iconos32x32/building_delete.png"));
	eliminarSitio.setToolTipText("Eliminar Sitio");
	eliminarSitio.setActionCommand("Eliminar Sitio");
        eliminarSitio.addActionListener(this);
        
        editarSitio = new JButton();
	editarSitio.setIcon(new ImageIcon("src/Images/iconos32x32/building_edit.png"));
	editarSitio.setToolTipText("Editar Sitio");
	editarSitio.setActionCommand("Editar Sitio");
        editarSitio.addActionListener(this);
        
        nuevaRAN = new JButton();
	nuevaRAN.setIcon(new ImageIcon("src/Images/iconos32x32/RAN-32add.png"));
	nuevaRAN.setToolTipText("Nueva RAN");
	nuevaRAN.setActionCommand("Nueva RAN");
        nuevaRAN.addActionListener(this);
        
        eliminarRAN = new JButton();
	eliminarRAN.setIcon(new ImageIcon("src/Images/iconos32x32/RAN-32delete.png"));
	eliminarRAN.setToolTipText("Eliminar RAN");
	eliminarRAN.setActionCommand("Eliminar RAN");
        eliminarRAN.addActionListener(this);
        
        editarRAN = new JButton();
	editarRAN.setIcon(new ImageIcon("src/Images/iconos32x32/RAN-32edit.png"));
	editarRAN.setToolTipText("Editar RAN");
	editarRAN.setActionCommand("Editar RAN");
        editarRAN.addActionListener(this);
        
        // Botones de eNodeB
        
        nuevoENodeB = new JButton();
	nuevoENodeB.setIcon(new ImageIcon("src/Images/iconos32x32/eNB6-32add.png"));
	nuevoENodeB.setToolTipText("Crear Nuevo eNodeB");
	nuevoENodeB.setActionCommand("Crear Nuevo eNodeB");
        nuevoENodeB.addActionListener(this);
        
        editarENodeB = new JButton();
	editarENodeB.setIcon(new ImageIcon("src/Images/iconos32x32/eNB6-32edit.png"));
	editarENodeB.setToolTipText("Editar eNodeB");
	editarENodeB.setActionCommand("Editar eNodeB");
        editarENodeB.addActionListener(this);
        
        eliminarENodeB = new JButton();
	eliminarENodeB.setIcon(new ImageIcon("src/Images/iconos32x32/eNB6-32-Delete.png"));
	eliminarENodeB.setToolTipText("Eliminar eNodeB");
	eliminarENodeB.setActionCommand("Eliminar eNodeB");
        eliminarENodeB.addActionListener(this);
        
        nuevoEU = new JButton();
	nuevoEU.setIcon(new ImageIcon("src/Images/iconos32x32/blackberry_white-add.png"));
	nuevoEU.setToolTipText("Crear Nuevo Equipo de Usuario");
	nuevoEU.setActionCommand("Crear Nuevo Equipo de Usuario");
        nuevoEU.addActionListener(this);
        
        nuevoOutdoorLink = new JButton();
	nuevoOutdoorLink.setIcon(new ImageIcon("src/Images/iconos32x32/connect-add.png"));
	nuevoOutdoorLink.setToolTipText("Crear Nueva Conexión Outdoor");
	nuevoOutdoorLink.setActionCommand("Crear Nueva Conexión Outdoor");
        nuevoOutdoorLink.addActionListener(this);
        
        this.setName("Herramientas de Proyecto");
        
        add(nuevoSitio);
        add(nuevaRAN);
        add(nuevoENodeB);
        add(nuevoEU);
        add(nuevoOutdoorLink);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        //Eventos para botones de Sitios
        if(e.getActionCommand().equals("Nuevo Sitio")){
            controlador.getControladorBotones().nuevaVentanaNuevoSitio();
        }if(e.getActionCommand().equals("Eliminar Sitio")){
            controlador.getControladorBotones().crearVentanaEliminarSitio();
        }if(e.getActionCommand().equals("Editar Sitio")){
            controlador.getControladorBotones().crearVentanaEditarSitio();
        }
        //Eventos para botones de RANs
        if(e.getActionCommand().equals("Nueva RAN")){
            controlador.getControladorBotones().crearVentanaNuevoRAN();
        }if(e.getActionCommand().equals("Eliminar RAN")){
            controlador.getControladorBotones().crearVentanaEliminarRAN();
        }if(e.getActionCommand().equals("Editar RAN")){
            controlador.getControladorBotones().crearVentanaEditarRAN();
        }
        //Eventos para botones de eNodeB
        if(e.getActionCommand().equals("Crear Nuevo eNodeB")){
            controlador.getControladorBotones().crearVentanaNuevoNodeB();
        }if(e.getActionCommand().equals("Editar eNodeB")){
            controlador.getControladorBotones().crearVentanaEditarNodoB();
        }if(e.getActionCommand().equals("Eliminar eNodeB")){
            controlador.getControladorBotones().crearVentanaEliminarNodoB();
        }if(e.getActionCommand().equals("Crear Nuevo Equipo de Usuario")){
             controlador.getControladorBotones().crearVenatanaEU();
        }if(e.getActionCommand().equals("Crear Nueva Conexión Outdoor")){
             controlador.getControladorBotones().crearVentanaNuevoEnlaceOutdoor();
        }
    }
}
