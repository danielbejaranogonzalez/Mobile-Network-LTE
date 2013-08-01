/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista.barras;

import Controlador.ControladorGeneral;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.text.DefaultEditorKit;

/**
 *
 * @author Bejaranos
 */
public class HerramientasPrincipales extends JToolBar implements ActionListener
{

    private JButton nuevoProyecto;
    private JButton abrirProyecto;
    private JButton eliminarProyecto;
    private JButton proyectoPredeterminado;

    private JButton guardar;
    private JButton propiedadesProjectBook;
    private JButton actualizar;

    private ControladorGeneral controlador;
  
    public HerramientasPrincipales(ControladorGeneral controlador)
    {
        this.controlador = controlador;
        this.setFloatable(true);
        this.setName("Barra de Herramientas Principales");

        nuevoProyecto = new JButton();
	nuevoProyecto.setIcon(new ImageIcon("src/Images/iconos32x32/package_add.png"));
	nuevoProyecto.setToolTipText("Nuevo Proyecto");
	nuevoProyecto.setActionCommand("Nuevo Proyecto");
        nuevoProyecto.addActionListener(this);

        abrirProyecto = new JButton();
	abrirProyecto.setIcon(new ImageIcon("src/Images/iconos32x32/folder-project.png"));
	abrirProyecto.setToolTipText("Abrir Proyecto");
	abrirProyecto.setActionCommand("Abrir Proyecto");
        abrirProyecto.addActionListener(this);

        eliminarProyecto = new JButton();
	eliminarProyecto.setIcon(new ImageIcon("src/Images/iconos32x32/package_delete.png"));
	eliminarProyecto.setToolTipText("Eliminar Proyecto");
	eliminarProyecto.setActionCommand("Eliminar Proyecto");
        eliminarProyecto.addActionListener(this);

        proyectoPredeterminado = new JButton();
	proyectoPredeterminado.setIcon(new ImageIcon("src/Images/iconos32x32/package-pre.png"));
	proyectoPredeterminado.setToolTipText("Establecer Proyecto Predeterminado...");
	proyectoPredeterminado.setActionCommand("Establecer Proyecto Predeterminado");
        proyectoPredeterminado.addActionListener(this);
        
        guardar = new JButton();
	guardar.setIcon(new ImageIcon("src/Images/iconos32x32/disk.png"));
	guardar.setToolTipText("Guardar Proyecto Predeterminado");
	guardar.setActionCommand("Guardar");
        guardar.setEnabled(true);
        guardar.addActionListener(this);
        
        propiedadesProjectBook = new JButton();
	propiedadesProjectBook.setIcon(new ImageIcon("src/Images/iconos32x32/book_edit.png"));
	propiedadesProjectBook.setToolTipText("Propiedades del ProjectBook");
	propiedadesProjectBook.setActionCommand("Propiedades del ProjectBook");
        propiedadesProjectBook.setEnabled(true);
        propiedadesProjectBook.addActionListener(this);
        
        actualizar = new JButton();
	actualizar.setIcon(new ImageIcon("src/Images/iconos32x32/update.png"));
	actualizar.setToolTipText("Actualizar");
	actualizar.setActionCommand("Actualizar");
        actualizar.setEnabled(true);
        actualizar.addActionListener(this);
        
        /*
        cut = new JButton( new DefaultEditorKit.CutAction());
	cut.setIcon(new ImageIcon("src/Images/iconos24x24/box-label.png"));
        cut.setText("");
	cut.setToolTipText("Guardar");
	cut.setActionCommand("Guardar");
        cut.addActionListener(this);
         */

        add(nuevoProyecto);
        add(eliminarProyecto);
        add(proyectoPredeterminado);
        this.addSeparator();
        add(abrirProyecto);
        add(guardar);
        add(propiedadesProjectBook);
        add(actualizar);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand().equals("Nuevo Proyecto"))
        {
            controlador.getControladorBotones().crearVentanaNuevoProyecto();
        }if(e.getActionCommand().equals("Establecer Proyecto Predeterminado"))
         {
            controlador.getControladorBotones().crearVentanaEstablecerProyectoPredeterminado();
          }if(e.getActionCommand().equals("Propiedades del ProjectBook"))
           {
               controlador.getControladorBotones().crearVentanaConfiguracionProjectBook();
           }if(e.getActionCommand().equals("Eliminar Proyecto"))
             {
                 controlador.getControladorBotones().crearVentanaEliminarProyecto();
             }if(e.getActionCommand().equals("Actualizar"))
              {
                    controlador.getControladorVentanas().actualizarArbolPrincipal();
              }if(e.getActionCommand().equals("Guardar"))
               {
                       controlador.getControladorBotones().guardarProyecto(); 
               }if(e.getActionCommand().equals("Abrir Proyecto"))
               {
                         controlador.getControladorBotones().abrirProyecto(); 
               }
    }
}
