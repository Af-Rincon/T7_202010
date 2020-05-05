package model.data_structures;

public class GrafoNoDirigido<Key,Value> {
	
private int nVertices;

private int nArcos;

	
	public GrafoNoDirigido(int num)
	{
		nVertices = num;
		nArcos = 0;
	}
	
	public int V()
	{
		return nVertices;
	}
	
	public int E()
	{
		return nArcos;
	}
	
	private void addEdge(Key VI, Key VF, double cost)
	{
		
	}
	
	private Vertice getInfoVertex(Key id)
	{
		return null;
	}
	
	private void setInfoVertex(Key id, Value info)
	{
		
	}
	
	private double getCostArc(Key VI, Key VF)
	{
		return 0;
	}
	
	private void setCostArc(Key idVI,Key idVF, double cost)
	{
		
	}
	
	private void addVertex(Key idVertix, Value infoVertix)
	{
		
	}
	
	private Iterable<Key> adj(Key idVertix)
	{
		return null;
	}

	private void uncheck()
	{
		
	}
	
	private void dfs(Key s)
	{
		
	}
	
	private int cc()
	{
		return 0;
	}
	
	private Iterable<Key> getCC(Key idVertix)
	{
		return null;
	}
}
