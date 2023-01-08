package TDAS;

import java.util.LinkedList;


public class NodeTree<E> {
    private E content; 
    private LinkedList<Tree<E>> children;

    public NodeTree(E content) {
        this.content = content;
        this.children = new LinkedList<>();
    }
    
    public NodeTree() {
        this.content = null;
        this.children = null;
    }

    public E getContent() {
        return content;
    }

    public LinkedList<Tree<E>> getChildren() {
        return children;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public void setChildren(LinkedList<Tree<E>> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "nombre = " + content;
    }
    
    
}
