package model.data_structures;

import java.util.Iterator;

public class GrafoNoDirigido<Key extends Comparable<Key>, Value> 
{

	@SuppressWarnings("hiding")
	public class Arco<Key>
	{
		private Key idVertexInicio;

		private Key idVertexFin;

		private double costoArco;


		public Arco(Key idI, Key idF, double costo)
		{
			idVertexInicio = idI;
			idVertexFin = idF;
			costoArco = costo;
		}


		public void cambiarCosto(double pCosto)
		{
			costoArco  = pCosto;
		}

		public Key darInicio()
		{
			return idVertexInicio;
		}

		public Key darFin()
		{
			return idVertexFin;
		}

		public double darCosto()
		{
			return costoArco;
		}

	}

	@SuppressWarnings("hiding")
	public class Vertice<Key, Value>
	{
		private Key idVertex;

		private Value valorVertex;

		private boolean marca; 

		private Queue<Arco<Key>> adyacentes;

		public Vertice(Key id, Value valor)
		{
			idVertex  = id;
			valorVertex  = valor;
			adyacentes  = new Queue<Arco<Key>>();
		}

		public void agregarArco(Arco<Key> arco)
		{
			adyacentes.enqueue(arco);
		}

		public void cambiarInfoVertice(Value valor)
		{
			valorVertex = valor;
		}

		public Queue<Arco<Key>> darAdyacentes()
		{
			return adyacentes;
		}

		public boolean darMarked()
		{
			return marca;
		}

		public void marcar()
		{
			marca = true;
		}
		
		public void desmarcar()
		{
			marca = false;
		}

	}


	private int nVertices;

	private int nArcos;

	private SeparateChainingHash<Key, Vertice<Key, Value>> vertices;
	
	private Queue<Arco<Key>> arcos;


	public GrafoNoDirigido()
	{
		nVertices = 0;
		nArcos = 0;

		vertices = new SeparateChainingHash<Key, Vertice<Key, Value>>(1000);
		arcos = new Queue<Arco<Key>>();

	}

	public GrafoNoDirigido(int num)
	{
		nVertices = num;
		nArcos = 0;

		vertices = new SeparateChainingHash<Key, Vertice<Key, Value>>(1000);


	}

	public SeparateChainingHash<Key, Vertice<Key, Value>> darVertices()
	{
		return vertices;
	}
	public int V()
	{
		return nVertices;
	}

	public int E()
	{
		return nArcos;
	}

	public void addEdge(Key VI, Key VF, double cost)
	{
		if(getInfoVertex(VI) != null && getInfoVertex(VF) != null)
		{
			Vertice<Key, Value> inicio = vertices.get(VI);
			Vertice<Key, Value> fin = vertices.get(VF);


			Arco<Key> arco =new Arco<Key>(VI, VF, cost);

			inicio.agregarArco(arco);
			fin.agregarArco(arco);
			arcos.enqueue(arco);
			nArcos++;
		}
	}

	public Value getInfoVertex(Key id)
	{
		Vertice<Key,Value> buscado = vertices.get(id);

		if (buscado != null)
		{
			return buscado.valorVertex;
		}

		return null;

	}

	public Vertice<Key, Value> getVertex(Key id)
	{
		Vertice<Key,Value> buscado = vertices.get(id);

		if (buscado != null)
		{
			return buscado;
		}

		return null;
	}

	public void setInfoVertex(Key id, Value info)
	{
		Vertice<Key,Value> buscado = vertices.get(id);

		if (buscado != null)
		{
			buscado.cambiarInfoVertice(info);
		}
	}

	public double getCostArc(Key VI, Key VF)
	{
		Vertice<Key, Value> buscadoInicial =  vertices.get(VI);
		Vertice<Key, Value> buscadoFinal = vertices.get(VF);

		Iterator<Arco<Key>> ady = buscadoInicial.darAdyacentes().iterator();

		while(ady.hasNext())
		{
			Arco<Key> actual = ady.next();
			if(actual.idVertexFin.compareTo(VF) == 0)
			{
				return actual.costoArco;
			}
		}

		return -1;

	}

	public void setCostArc(Key idVI,Key idVF, double cost)
	{
		Vertice<Key, Value> buscadoInicial =  vertices.get(idVI);
		Vertice<Key, Value> buscadoFinal = vertices.get(idVF);

		Iterator<Arco<Key>> ady = buscadoInicial.darAdyacentes().iterator();

		while(ady.hasNext())
		{
			Arco<Key> actual = ady.next();
			if(actual.idVertexFin.compareTo(idVI) == 0)
			{
				actual.cambiarCosto(cost);
				break;
			}
		}

		ady = buscadoFinal.darAdyacentes().iterator();

		while(ady.hasNext())
		{
			Arco<Key> actual = ady.next();
			if(actual.idVertexFin.compareTo(idVI) == 0)
			{
				actual.cambiarCosto(cost);
				break;
			}
		}

	}

	public void addVertex(Key idVertix, Value infoVertix)
	{
		Vertice<Key, Value> nuevoVertice = new Vertice<Key, Value>(idVertix, infoVertix);
		vertices.put(idVertix, nuevoVertice);
		nVertices++;
	}

	public Iterable<Key> adj(Key idVertix)
	{
		Vertice<Key, Value> buscado =  vertices.get(idVertix);

		Iterator<Arco<Key>> ady = buscado.darAdyacentes().iterator();
		Queue<Key> aRetornar = new Queue<Key>();

		while(ady.hasNext())
		{
			Arco<Key> actual = ady.next();
			aRetornar.enqueue(actual.idVertexFin);
		}

		return aRetornar;

	}

	public void uncheck()
	{
		Iterator<Key> llaves = vertices.keys();

		while(llaves.hasNext())
		{
			Key llaveActual = llaves.next();
			vertices.get(llaveActual).desmarcar();
		}
	}

	public void dfs(Key s)
	{
		Vertice<Key,Value> v =  vertices.get(s);
		v.marcar();
		vertices.put(s, v);
		for(Key e: adj(s))
		{
			Vertice<Key,Value> actual = vertices.get(e);
			if(!actual.darMarked())
			{
				dfs(e);
			}
			
		}
	}

	public int cc()
	{
		uncheck();
		Iterator<Key> llaves = vertices.keys();
		int cc  = 0;

		while(llaves.hasNext())
		{
			Key actual = llaves.next();
			Vertice<Key,Value> v = vertices.get(actual);
			if(!v.darMarked()){
				dfs(actual);
				cc++;
			}
		}
		
		return cc;

	}

	public Iterable<Key> getCC(Key idVertix)
	{
		Queue<Key> resp  = new Queue<Key>();
		uncheck();
		dfs(idVertix);
		Iterator<Key> llaves = vertices.keys();
		while(llaves.hasNext())
		{
			Key actual = llaves.next();
			if(vertices.get(actual).darMarked())
			{
				resp.enqueue(actual);
			}
		}
		return resp;
	}
}
