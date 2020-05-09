package model.logic;

public class EstacionPolicia implements Comparable<EstacionPolicia>
{

	/**
	 * Nombre de la estación de policía
	 */
	private String nombre; 

	/**
	 * ObjectID de la estación de policía
	 */
	private int objectID; 

	/**
	 * Latitud de la estación de policía
	 */
	private double latitud;

	/**
	 * Longitud de la estación de policía
	 */
	private double longitud;


	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	/**
	 * Crea una estación de policía con toda la informacion respectiva.
	 * @param pNombre Nombre de la estación de policía
	 * @param pID Object ID de la estación de policía
	 * @param pLatitud Latitud de la estación de policía
	 * @param pLongitud Longitud de la estación de policía
	 */

	public EstacionPolicia(String pNombre, int pID, double pLatitud, double pLongitud)
	{
		nombre = pNombre;
		objectID = pID;
		latitud = pLatitud;
		longitud = pLongitud;
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	public String darNombre()
	{
		return nombre;
	}
	
	public int darObjectID()
	{
		return objectID;
	}
	
	public double darLatitud()
	{
		return latitud;
	}
	
	public double darLongitud()
	{
		return longitud;
	}

	@Override
	public int compareTo(EstacionPolicia o) 
	{
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString()
	{
		return "ObjectID; " + objectID + "; Nombre: " + nombre + "; Latitud: " + latitud + "; Longitud: " + longitud;
	}
	
	
}
