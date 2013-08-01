/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.menus;

import Controlador.ControladorGeneral;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 *
 * @author Usuario
 */
public class MenuClickDerechoMundo extends JPopupMenu{
    private JMenuItem item1;
        private JMenuItem item2;
        private JMenuItem item3;
        private ControladorGeneral controlador;

        public MenuClickDerechoMundo(ControladorGeneral controlador){

            item1 = new JMenuItem("Test");
            item2 = new JMenuItem("Test");
            item3 = new JMenuItem("Test");

            this.add(item1);
            this.add(item2);
            this.add(item3);
        }
}
