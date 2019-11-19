package AI;

import java.util.LinkedList;
import java.util.List;

//basic node data structure

public class Node<T> {

	T data;
    Node<T> parent;
    List<Node<T>> children;

    public Node(T data) {
        this.data = data;
        this.children = new LinkedList<Node<T>>();
    }

    public void addChild(T childData) {
        Node<T> child = new Node<T>(childData);
        child.parent = this;
        this.children.add(child);
    }
    
    public void removeChildOnIndex(int i) {
    	this.children.remove(i);
    }
	
}
