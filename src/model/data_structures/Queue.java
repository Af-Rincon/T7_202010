package model.data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T> {
	
	private Node<T> primero;
	private Node<T> ultimo;
	private int size;
	
	public Queue()
	{
		primero = null;
		ultimo = null;
		size = 0;
	}
	
	public void enqueue(T item)
	{
		Node<T> nuevo = new Node<T>(item);
		
		if(size == 0)
		{
			primero = nuevo;
			ultimo = nuevo;
		}
		else{
			Node<T> viejo = ultimo;
			viejo.cambiarSiguiente(nuevo);
			ultimo = nuevo;
		}
		size++;
	}
	
	public T dequeue()
	{
		Node<T> viejo = primero;
		T buscado = primero.darElemento();
		primero = viejo.darSiguiente();
		viejo.cambiarSiguiente(null);
		size--;
		return buscado;

	}
	
	public int darTamano()
	{
		return size;
	}
	
	public Node<T> darPrimerNodo()
	{
		return primero;
	}
	
	public Node<T> darUltimoNodo()
	{
		return ultimo;
	}

	public T darPrimerElemento()
	{
		return primero.darElemento();
	}
	
	public T darUltimoElemento()
	{
		return ultimo.darElemento();
	}

	
	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new MiIteradorNodo();
	}

	protected class MiIteradorNodo implements Iterator<T>
	{
		Node<T> actual;
		T value;
		
		public MiIteradorNodo()
		{
			actual = primero;
			value = null;
		}
		@Override
		public boolean hasNext() {
			return (actual!=null);
		}

		@Override
		public T next() {
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			else
			{
				value = actual.darElemento();
				actual = actual.darSiguiente();
				
			}
			return value;
		}
		
	}

}
