package test.data_structures;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Iterator;

import model.data_structures.SeparateChainingHash;

public class testHashSeparateChaining 
{

	SeparateChainingHash<String, String> hashSC; 
	
	
	@Before
	public void setUp()
	{
		hashSC = new SeparateChainingHash<String, String>(7);
		hashSC.put("1", "H");
		hashSC.put("2", "O");
		hashSC.put("3", "L");
		hashSC.put("4", "A");
	}
	
	@Test
	public void testDarTamano()
	{
		assertEquals("El tamano no es el esperado", 7, hashSC.darTamano() );
	}
	
	@Test
	public void testDarTotalDuplas()
	{
		assertEquals("El numero de duplas no es el esperado", 4, hashSC.darTotalDuplas());
	}
	
	@Test 
	public void testDarTotalRehashes()
	{
		assertEquals("El numero de rehashes no es el esperado", 0, hashSC.darTotalRehashes());
	}
	
	@Test
	public void testContains()
	{
		assertTrue(hashSC.contains("1"));
		assertTrue(!hashSC.contains("5"));
	}
	
	@Test
	public void testPut()
	{

		hashSC.put("1", "!");
		assertEquals("El total de valores no es el esperado", hashSC.get("1"), "!");
		
		hashSC.put("5", "C");
		assertTrue(hashSC.contains("5"));
		assertEquals("El total de valores no es el esperado", hashSC.get("5"), "C");
	}
	
	@Test
	public void testGet()
	{
		assertEquals("El total de valores no es el esperado", hashSC.get("1"), "H");
		assertNull("El total de valores no es el esperado", hashSC.get("8"));
	}
	
	@Test
	public void testDeleteSet()
	{
		hashSC.delete("1");
		assertTrue(!hashSC.contains("1"));
	}
	
	@Test 
	public void testResize()
	{
		hashSC.resize(16);
		assertEquals("El tamano no es el esperado", 16, hashSC.darTamano() );
		assertEquals("El numero de duplas no es el esperado", 4, hashSC.darTotalDuplas());
		assertEquals("El numero de rehashes no es el esperado", 1, hashSC.darTotalRehashes());
	}
	
	
}
