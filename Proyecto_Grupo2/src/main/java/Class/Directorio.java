
package Class;

import java.io.File;
import TDAS.ArrayList;
import java.util.Comparator;

public class Directorio implements Comparator<Directorio>{
    private String ruta;
    private String nombre;
    private int tamanio;
    private Boolean isDirectory = false;
    private ArrayList<Directorio> archivos;
    
    //Para construir los directorios 
    public Directorio(String nombre, String ruta) {
        this.ruta = ruta;
        this.nombre = nombre;
        isDirectory = true;
        archivos = new ArrayList<>();
    }
    
    //para construir los archivos normales
    public Directorio(String nombre, int tamanio, String ruta) {
        this.nombre = nombre;
        this.tamanio = tamanio;
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getTamanio() {
        return tamanio;
    }

    public Boolean getIsDirectory() {
        return isDirectory;
    }

    public ArrayList<Directorio> getArchivos() {
        return archivos;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public void setIsDirectory(Boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

    public void setArchivos(ArrayList<Directorio> archivos) {
        this.archivos = archivos;
    }

    @Override
    public String toString() {
        return "Directorio{" + "nombre=" + nombre + ", tamanio=" + tamanio + ", isDirectory=" + isDirectory + '}';
    }

    public int calcularPeso(File f) {
        int total = 0;
        if(isDirectory == true) {
            File[] archivos = f.listFiles();
            for(File tmp: archivos){
                if(tmp.isDirectory()){
                    total += calcularPeso(tmp);
                } else {
                    total += tmp.length();
                }
            }
        }
        return total;
    }

    public void llenarArchivos() {
        File directorio = new File(ruta);
        if(isDirectory == true) {
            File[] todos = directorio.listFiles();
            for(File f: todos){
                if(!f.isDirectory()){
                    Directorio tmp = new Directorio(f.getName(), (int)f.length(), f.getPath());
                    this.archivos.addFirst(tmp);
                } else {
                    Directorio tmp = new Directorio(f.getName(), f.getPath());
                    tmp.setTamanio(calcularPeso(f));
                    tmp.llenarArchivos();
                    this.archivos.addFirst(tmp);
                }               
            }
        }  
    }
    
    public Boolean isDirectory(){
        return isDirectory;
    }

    @Override
    public int compare(Directorio o1, Directorio o2) {
        return o1.getNombre().compareTo(o2.getNombre());
    }
    
}
