package TDAS;

public interface List<E> extends Iterable<E> {
    
    /*** Métodos que deben ser implementados en el taller de la clase ArrayList ****/

    public boolean addFirst(E e); // inserta el elemento e al inicio

    public boolean addLast(E e); // inserta el elemento e al final

    public E removeFirst(); // remueve el elemento al inicio de la lista

    public E removeLast() throws Exception; // remueve el elemento al final de la lista

    public int size();

    public boolean isEmpty();

    public void clear();          

    public boolean add(int index, E element) throws Exception; // inserta element en la posición index

    public E remove(int index) throws Exception; // remueve y retorna el elemento en la posición index

    public E get(int index)throws Exception;; // retorna el elemento ubicado en la posición index

    public E set(int index, E element)throws Exception; // setea el element en la posición index
    
    public String toString(); // retorna una cadena de caracteres representando los elementos que la lista contiene*/

}
