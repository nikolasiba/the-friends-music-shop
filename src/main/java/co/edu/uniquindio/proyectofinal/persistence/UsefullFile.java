package co.edu.uniquindio.proyectofinal.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Esta clase teine metodo estaticos que permite usarlos sin crear instancias de la clase
 * Lo que se hizo fue crear esta libreria para el manejo de los archivos
 * @author Admin
 *
 */
public  class UsefullFile {

    static String fechaSistema = "";
    /**
     * Este metodo recibe una cadena con el contenido que se quiere guardar en el archivo
     * @param ruta es la ruta o path donde esta ubicado el archivo
     * @throws IOException
     */
    public static void guardarArchivo(String ruta,String contenido, Boolean flagAnexarContenido) throws IOException {

        FileWriter fw = new FileWriter(ruta,flagAnexarContenido);
        BufferedWriter bfw = new BufferedWriter(fw);
        bfw.write(contenido);
        bfw.close();
        fw.close();
    }

    /**
     * ESte metodo retorna el contendio del archivo ubicado en una ruta,con la lista de cadenas.
     * @param ruta
     * @return
     * @throws IOException
     */
    public static ArrayList<String> leerArchivo(String ruta) throws IOException {

        ArrayList<String>  contenido = new ArrayList<String>();
        FileReader fr=new FileReader(ruta);
        BufferedReader bfr=new BufferedReader(fr);
        String linea="";
        while((linea = bfr.readLine())!=null)
        {
            contenido.add(linea);
        }
        bfr.close();
        fr.close();
        return contenido;
    }

    //------------------------------------SERIALIZACIÓN  y XML
    /**
     * Escribe en el fichero que se le pasa el objeto que se le envia

     */

    public static Object cargarRecursoSerializado(String rutaArchivo) throws IOException, ClassNotFoundException {
        File archivo = new File(rutaArchivo);
        if (archivo.length() == 0) {
            return null;
        }
        Object objeto = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            objeto = ois.readObject();
        }
        return objeto;
    }


    public static void salvarRecursoSerializado(String rutaArchivo, Object object)	throws Exception {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo, false));
            oos.writeObject(object);
        } catch (Exception e) {
            throw e;
        } finally {
            if (oos != null)
                oos.close();
        }
    }

}