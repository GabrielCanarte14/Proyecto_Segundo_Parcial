
package Class;

import java.io.File;
import java.util.LinkedList;

public class Directorio{
    private String nombre;
    private int tamanio;
    private Boolean isDirectory = false;
    private LinkedList<Directorio> archivos;
    
    //Para construir los directorios 
    public Directorio(String nombre) {
        this.nombre = nombre;
        isDirectory = true;
        archivos = new LinkedList<>();
    }
    
    //para construir los archivos normales
    public Directorio(String nombre, int tamanio) {
        this.nombre = nombre;
        this.tamanio = tamanio;
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

    public LinkedList<Directorio> getArchivos() {
        return archivos;
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

    public void setArchivos(LinkedList<Directorio> archivos) {
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
        File directorio = new File(nombre);
        if(isDirectory == true && directorio != null) {
            File[] todos = directorio.listFiles();
            for(File f: todos){
                if(!f.isDirectory()){
                    Directorio tmp = new Directorio(f.getPath(), (int)f.length());
                    this.archivos.add(tmp);
                } else {
                    Directorio tmp = new Directorio(f.getPath());
                    tmp.setTamanio(calcularPeso(f));
                    tmp.llenarArchivos();
                    this.archivos.add(tmp);
                }               
            }
        }  
    }
    
    public Boolean isDirectory(){
        return isDirectory;
    }
    
}
