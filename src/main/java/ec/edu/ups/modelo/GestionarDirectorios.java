/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.modelo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karen
 */
public class GestionarDirectorios {
    private String raiz;

    public GestionarDirectorios(String raiz) {
        this.raiz = raiz;
    }

    public String getRaiz() {
        return raiz;
    }

    public void setRaiz(String path) {
        this.raiz = path;
    }
    
    public File[] abrirDirectorio(){
        File path = new File(raiz);
        File [] elementos = path.listFiles();
        return elementos;
    }
    
    public String obtenerInformacion(String nombreArchivo) throws IOException {
        String ruta = raiz + "/" + nombreArchivo;
        Path path = Paths.get(ruta);
        
        //Para construir la cadena de texto con la información del archivo/directorio
        StringBuilder informacion = new StringBuilder();

        try {
            // Obtener tamaño del archivo
            long size = Files.size(path);

            // Obtener atributos del archivo
            BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);

            // Obtener permisos
            boolean esLectura = Files.isReadable(path);
            boolean esEscritura = Files.isWritable(path);

            // Obtener última fecha de modificación
            FileTime ultimaModificacion = attrs.lastModifiedTime();

            // Construir la cadena de información
            informacion.append("Ruta: ").append(path).append("\n");
            informacion.append("Tamaño: ").append(size).append(" bytes\n");
            informacion.append("Lectura: ").append(esLectura).append("\n");
            informacion.append("Escritura: ").append(esEscritura).append("\n");
            informacion.append("Última modificación: ").append(ultimaModificacion).append("\n");
        } catch (IOException ex) {
            Logger.getLogger(GestionarDirectorios.class.getName()).log(Level.SEVERE, null, ex);
            informacion.append("Error al obtener información del archivo.");
        }
        return informacion.toString();
    }
    
    public int crearArchivo (String nombreArchivo){
       
        String rutaArchivo;
        
        if(nombreArchivo != null){//Verifica que se pase un nombre
            rutaArchivo = raiz + "/" +nombreArchivo;
        }else{
            return 0;
        }
        
        File archivo = new File(rutaArchivo);//Instancia un nuevo File
        try {
            if (archivo.createNewFile()) {
                //Archivo creado con éxito
                return 1;
            } else {
                //El archivo ya existe
                return 0;
            }
        } catch (IOException e) {
            System.out.println("Error al intentar crear el archivo: " + e.getMessage());
        }
        return 0;
    }
    
    public int crearDirectorio(String nombreDirectorio) {
        String rutaDirectorio = null;

        if (nombreDirectorio != null) {
            rutaDirectorio = raiz + "/" + nombreDirectorio;
        }

        File directorio = new File(rutaDirectorio);

        if (directorio.mkdir()) {
            // Directorio creado con éxito
            return 1;
        } else {
            // El directorio ya existe o no se pudo crear
            return 0;
        }
    }
    
    public void eliminar(String archivo){
        String ruta= null;

        if (archivo != null) {
            ruta = raiz + "/" + archivo;
        }
        
        File eliminar = new File(ruta);
        eliminar.delete();
    }

    public void renombrar(String nombreArchivoSeleccionado, String nuevoNombre) {
        String ruta = null;

        if (nombreArchivoSeleccionado != null) {
            ruta = raiz + "/" + nombreArchivoSeleccionado;
        }

        File renombrar = new File(ruta);
        
        // Obtener la ruta del nuevo archivo con el nuevo nombre
        String nuevaRuta = raiz + "/" + nuevoNombre;
        File actualizado = new File(nuevaRuta);
        renombrar.renameTo(actualizado);
    }
}
