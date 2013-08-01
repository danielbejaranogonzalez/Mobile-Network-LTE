/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.PRO;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Storage {
    
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    
    public Storage (){
            //outputStream = new ObjectOutputStream(null);
            //inputStream = new ObjectInputStream(null);
            //inputStream = new ObjectInputStream(new FileInputStream("D:\test.mnd"));
    }
    
    public void guardarProyectoPredeterminado(Proyecto proyecto){
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream(proyecto.getURLProyecto()));
            outputStream.writeObject(proyecto);
            outputStream.reset();
            //outputStream.close();
            System.out.println("TEST Grabacion Proyectos");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public Proyecto abrirProyecto(String ruta){
        
        Proyecto proyectoReaded = null;
        try {
                try{
                    inputStream = new ObjectInputStream(new FileInputStream(ruta));
                }catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "'Archivo no existe'","Error 025: FileNotFoundException",
                            JOptionPane.ERROR_MESSAGE);
                } 
            try{
                Proyecto aux = (Proyecto)inputStream.readObject();  
                if(aux != null){
                    proyectoReaded = aux;
                    System.out.println("TEST lectura de Proyectos");
                }
            }catch(NotSerializableException NSE){

            }catch(InvalidClassException ICE){
                
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        
        //</editor-fold>
        
        return proyectoReaded;
    }
}
