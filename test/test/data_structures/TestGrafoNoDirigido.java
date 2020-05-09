package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import model.data_structures.GrafoNoDirigido;

public class TestGrafoNoDirigido 
{

	private GrafoNoDirigido<Integer, String> grafo;
	
	@Before
	public void setUpEscenario1()
	{
		grafo = new GrafoNoDirigido<Integer, String>();
		grafo.addVertex(1, "A");
		grafo.addVertex(2, "B");
		grafo.addVertex(3, "C");
		grafo.addVertex(4, "D");
		grafo.addVertex(5, "E");
	}
	
	public void setUpEscenario2()
	{
		grafo.addEdge(1, 2, (double) 1);
		grafo.addEdge(3, 4, (double) 3);
		grafo.addEdge(4, 5, (double) 4);
		grafo.addEdge(3, 5, (double) 2);
	}
	
	@Test
	public void testV()
	{
		assertEquals("El numero de vertices no es el esperado", grafo.V(), 5);
	}
	
	@Test 
	public void testE()
	{
		assertEquals("El numero de arcos no es el esperado", grafo.E(), 0);
		setUpEscenario2();
		assertEquals("El numero de arcos no es el esperado", grafo.E(), 4);
	}
	
	@Test 
	public void testAddVertex()
	{
		grafo.addVertex(6, "F");
		assertEquals("El numero de vertices no es el correcto", grafo.V(), 6);
	}
	
	@Test
	public void testAddEdge()
	{
		grafo.addVertex(6, "F");
		grafo.addEdge(5, 6, (double) 2);
		Iterator<Integer> iter = grafo.adj(5).iterator();
		boolean encontrado=false;
		while(iter.hasNext())
			if((int)(iter.next())==6)
				encontrado=true;
		assertTrue("Debería haber una conexion con 6",encontrado);	
	}
	
	@Test 
	public void testGetInfoVertex()
	{
		assertEquals("El valor del vertice no es el adecuado", grafo.getInfoVertex(5),"E");
	}
	
	@Test
	public void testSetInfoVertex()
	{
		grafo.setInfoVertex(5, "H");
		assertEquals("El valor del vertice no es el adecuado", grafo.getInfoVertex(5),"H");
		assertEquals("El numero de vertices no es el correcto", grafo.V(), 5);
	}
	
	
	@Test
	public void testGetCostArc()
	{
		setUpEscenario2();
		assertEquals("La informacion del arco no es la esperada", grafo.getCostArc(1, 2),1,1);
	}
	
	@Test
	public void testSetCostArc()
	{
		setUpEscenario2();
		grafo.setCostArc(1, 2, 4.978);
		assertEquals("La informacion del arco no es la esperada", grafo.getCostArc(1, 2),4.978, 4.978);
	}
	
	
	@Test
	public void testAdj()
	{
		setUpEscenario2();
		Iterator<Integer> iter = grafo.adj(1).iterator();
		int contador=0;
		while(iter.hasNext())
		{
			contador++;
			iter.next();
		}
		iter=grafo.adj(5).iterator();
		int contador2=0;
		
		while(iter.hasNext())
		{
			contador2++;
			iter.next();
		}
		assertEquals("El elemento no tiene el numero correcto de adyacentes", 1,contador);
		assertEquals("El elemento no tiene el numero correcto de adyacentes", 2,contador2);
		
	}
	
	
	
	
	
	
	
	
	
}
