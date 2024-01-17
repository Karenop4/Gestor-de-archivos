/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.controlador;

import ec.edu.ups.modelo.GestionarDirectorios;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author karen
 */
public class GestionarDirectoriosControlador {
    private GestionarDirectorios gestDirectorios;

    public GestionarDirectoriosControlador() { 
    }
    
    public File[] listarDirectorios(String raiz){
        gestDirectorios = new GestionarDirectorios(raiz);
        return gestDirectorios.abrirDirectorio();
    }
    
    public String informacionFile(String nombreArchivo) throws IOException{
        return gestDirectorios.obtenerInformacion(nombreArchivo);
    }
    
    public int crearArchivo(String archivo){
        return gestDirectorios.crearArchivo(archivo);
    }
    
    public int crearDirectorio(String directorio){
        return gestDirectorios.crearDirectorio(directorio);
    }
    
    public void eliminar(String archivo){
        gestDirectorios.eliminar(archivo);
    }

    public void renombrar(String nombreArchivoSeleccionado, String nuevoNombre) {
        gestDirectorios.renombrar(nombreArchivoSeleccionado,nuevoNombre);
    }
}
