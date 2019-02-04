package linkedLists;
/**
 * Singly linked list with references to its first and its
 * last node. 
 * 
 * @author pirvos
 *
 */

import linkedLists.LinkedList;


public class SLFLList<E> 
implements LinkedList<E>
{

	private SNode<E> first, last; 
	int length = 0; 
	
	public SLFLList() { 
		first = last = null; 
	}
	
	
	public void addFirstNode(Node<E> nuevo) {
		// Pre: nuevo is not a node in the list
		((SNode<E>) nuevo).setNext(this.first);
		this.first = (SNode<E>) nuevo;
		if(this.last == null) {
			this.last = this.first;
		}
		this.length++;
	}

	public void addNodeAfter(Node<E> target, Node<E> nuevo) {
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list
		
		SNode<E> sNuevo = (SNode<E>) nuevo;
		SNode<E> sTarget = (SNode<E>) target;
		
		sNuevo.setNext(sTarget.getNext());
		sTarget.setNext(sNuevo);
		
		if(sTarget==this.last) {
			this.last = sNuevo;
		}
		
		this.length++;
	}

	public void addNodeBefore(Node<E> target, Node<E> nuevo) {
		// Pre: target is a node in the list
		// Pre: nuevo is not a node in the list
		
		if(target == this.first) {
			this.addFirstNode(nuevo);
		}
		else {
			this.addNodeAfter(this.getNodeBefore(target), nuevo);
		}
		
	}

	public Node<E> getFirstNode() throws NodeOutOfBoundsException {
		if(this.first == null) {
			throw new NodeOutOfBoundsException("getFirstNode(): Empty list.");
		}
		else {
			return this.first;
		}
	}

	public Node<E> getLastNode() throws NodeOutOfBoundsException {
		if(this.first == null) {
			throw new NodeOutOfBoundsException("getLastNode(): Empty list.");
		}
		else {
			return this.last;
		}
	}

	public Node<E> getNodeAfter(Node<E> target) throws NodeOutOfBoundsException {
		// Pre: target is a node in the list
		
		SNode<E> aNode = ((SNode<E>) target).getNext(); 
		if(aNode == null) {
			throw new NodeOutOfBoundsException("getNodeAfter(...) : target is the last node.");
		}
		else {
			
			return aNode;
		}
	}

	public Node<E> getNodeBefore(Node<E> target)
			throws NodeOutOfBoundsException {
		if(target == this.first) {
			throw new NodeOutOfBoundsException("getNodeBefore(...) : target is the first node.");
		}
		else{ 
			SNode<E> prev = this.first; 
			while (prev != null && prev.getNext() != target) 
				prev = prev.getNext();  
			return prev; 
		}
	}

	public int length() {
		return this.length;
	}

	public void removeNode(Node<E> target) {
		// Pre: target is a node in the list
		
		if(target == this.first) {
			// the list is not empty....
			SNode<E> ntr = this.first; 
			this.first = this.first.getNext(); 
			ntr.setNext(null);      // notice that the node keeps its data..
		}
		else if(target == this.last) {
			SNode<E> prev = (SNode<E>) this.getNodeBefore(this.last);
			prev.setNext(null);
			this.last = prev;
		}
		else {
			SNode<E> prevNode = (SNode<E>) this.getNodeBefore(target); 
			prevNode.setNext(((SNode<E>) target).getNext()); 
		}
		
		this.length--;
	}
	
	public Node<E> createNewNode() {
		return new SNode<E>();
	}


	///////////////////////////////////////////////////
	// private and static inner class that defines the 
	// type of node that this list implementation uses
	private static class SNode<T> implements Node<T> {
		private T element; 
		private SNode<T> next; 
		public SNode() { 
			element = null; 
			next = null; 
		}
		public SNode(T data, SNode<T> next) { 
			this.element = data; 
			this.next = next; 
		}
		public SNode(T data)  { 
			this.element = data; 
			next = null; 
		}
		public T getElement() {
			return element;
		}
		public void setElement(T data) {
			this.element = data;
		}
		public SNode<T> getNext() {
			return next;
		}
		public void setNext(SNode<T> next) {
			this.next = next;
		}
	}

	@Override
	public Object[] toArray() {
		Object[] a = new Object[this.length];
		SNode<E> aNode= this.first;
		
		for (int i = 0; i < this.length; i++) {
			a[i] = aNode.getElement();
			aNode = aNode.getNext();
		}
		return a;
	}

}
