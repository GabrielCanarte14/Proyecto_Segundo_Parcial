package TDAS;


public class Tree<E> {
    private NodeTree<E> root;

    public Tree() {
        this.root = null;
    }
    
    public NodeTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeTree<E> root) {
        this.root = root;
    }
    
    
}
