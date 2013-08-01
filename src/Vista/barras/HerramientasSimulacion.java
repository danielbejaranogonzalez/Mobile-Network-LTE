/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.barras;

import Controlador.ControladorGeneral;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

/**
 *
 * @author User
 */
public class HerramientasSimulacion extends JToolBar implements ActionListener{
    
    private JButton iniciarSimulacion;
    private JButton pararSimulacion;
    private JButton configuracionSimulacionPath;
    private JComboBox listaTiposSimulacion;
    
    private ControladorGeneral controlador;
    private String LISTA_TIPOS[] = {"<Conexión Outdoor>"};
    
    public HerramientasSimulacion(ControladorGeneral controlador){
        
        this.controlador = controlador;
        
        configuracionSimulacionPath = new JButton();
	configuracionSimulacionPath.setIcon(new ImageIcon("src/Images/iconos32x32/control_panel.png"));
	configuracionSimulacionPath.setToolTipText("Configuracion de Simumulador");
	configuracionSimulacionPath.setActionCommand("Configuracion de Simumulador");
        configuracionSimulacionPath.addActionListener(this);
        
        iniciarSimulacion = new JButton();
	iniciarSimulacion.setIcon(new ImageIcon("src/Images/iconos32x32/control_play_blue.png"));
	iniciarSimulacion.setToolTipText("Correr Simulacion");
	iniciarSimulacion.setActionCommand("Correr Simulacion");
        iniciarSimulacion.addActionListener(this);
        
        pararSimulacion = new JButton();
	pararSimulacion.setIcon(new ImageIcon("src/Images/iconos32x32/control_stop_blue.png"));
	pararSimulacion.setToolTipText("Parar Simulacion");
	pararSimulacion.setActionCommand("Parar Simulacion");
        pararSimulacion.addActionListener(this);
        pararSimulacion.setEnabled(false);
        
        listaTiposSimulacion = new JComboBox();
	listaTiposSimulacion.setToolTipText("Tipo de Simulacion");
	listaTiposSimulacion.setActionCommand("Tipo de Simulacion");
        listaTiposSimulacion.addActionListener(this);
        listaTiposSimulacion.setModel(new DefaultComboBoxModel(LISTA_TIPOS));
        listaTiposSimulacion.setMaximumSize(new Dimension(135,25));
        
        this.setName("Simulacion");
        add(configuracionSimulacionPath);
        add(iniciarSimulacion);
        add(pararSimulacion);
        add(listaTiposSimulacion);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Configuracion de Simumulador")){
            controlador.getControladorBotones().crearVentanaConfiguracionSimulacion();
        }else if(e.getActionCommand().equals("Correr Simulacion")){
            controlador.getControladorHilos().crearVentanaCorrerSimulacion();
            pararSimulacion.setEnabled(true);
            iniciarSimulacion.setEnabled(false);
        }else if(e.getActionCommand().equals("Parar Simulacion")){
            controlador.getControladorHilos().pararSimulacion();
            iniciarSimulacion.setEnabled(true);
            pararSimulacion.setEnabled(false);
        }
    }

}
