package model.data_structures;

import java.util.Iterator;


public class SeparateChainingHash<Key,Value> {

	private int n;
	private int m;
	private int numRehashes;
	private ST<Key, Value>[] st;

	public SeparateChainingHash(int tamano) {

		n = 0;
		m = tamano;
		numRehashes = 0;

		st = (ST<Key, Value>[]) new ST[m];

		for (int i = 0; i < m; i++)
		{
			st[i] = new ST<Key, Value>();
		}

	} 


	public int darTamano()
	{
		return m;
	}

	public int darTotalDuplas()
	{
		return n; 
	}


	public int darTotalRehashes()
	{
		return numRehashes;
	}


	public boolean contains(Key key)
	{
		if (key == null) throw new IllegalArgumentException("argument to contains() is null");
		return get(key) != null;
	}


	private int hash(Key key) {
		return (key.hashCode() & 0x7fffffff) % m;
	} 

	public void resize(int chains) 
	{
		SeparateChainingHash<Key, Value> temp = new SeparateChainingHash<Key, Value>(chains);


		for (int i = 0; i < m; i++) {
			for (Key key : st[i].keys()) {
				temp.put(key, st[i].get(key));
			}
		}
		this.m  = temp.m;
		this.n  = temp.n;
		this.st = temp.st;
		numRehashes ++;
	}

	public Value get(Key key) {

		if (key == null) throw new IllegalArgumentException("La llave ingresada es null");
		int i = hash(key);
		return st[i].get(key);

	} 


	public void put(Key key, Value val)
	{
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			delete(key);
			return;
		}

		// double table size if average length of list >= 10
		if (n >= 10*m) resize(2*m);

		int i = hash(key);
		if (!st[i].contains(key)) n++;
		st[i].put(key, val);
	}
	
	public void delete(Key key) 
	{
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);

        // halve table size if average length of list <= 2
        if (m > 5 && n <= 2*m) resize(m/2);
    } 

	
	public Iterator<Key> keys() 
	{
		Queue<Key> llaves = new Queue<Key>();
		for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys())
                llaves.enqueue(key);
        }
		return llaves.iterator();
	}





}
