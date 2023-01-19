package TDAS;

import TDAS.ArrayList;

public class Tree<E> {
    private NodeTree<E> root;

    public Tree() {
        this.root = null;
    }
    
    public Tree(E rootContent) {
        this.root = new NodeTree<>(rootContent);
    }
    
    public boolean isEmpty() {
        return this.root == null;
    }
    
    public boolean isLeaf() {
        return !isEmpty() && this.root.getChildren().isEmpty();
    }
    
    public NodeTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeTree<E> root) {
        this.root = root;
    }
    
    //Dice cuantos elementos tiene el arbol
    public int cantidad() {
        int valor = 1;
        if(this.isEmpty()) {
            return 0;
        } else if(this.isLeaf()){
            return 1;
        } else {
            for(Tree<E> tmp: this.root.getChildren()){
                valor += tmp.cantidad();
            }
            return valor;
        }      
    }
    
    public ArrayList<E> recorrer(){
        ArrayList<E> result = new ArrayList<>();
        
        if(this.isLeaf()){
           System.out.println(this.root.getContent());
           result.addLast(this.root.getContent());         
        } else {
            System.out.println(this.root.getContent());
            result.addLast(this.root.getContent());
            for(Tree<E> tmp: this.root.getChildren()){
                ArrayList<E> secundario = tmp.recorrer();
                for(E e: secundario){
                    result.addLast(e);
                }
            }
        }
        return result;
    }
}
