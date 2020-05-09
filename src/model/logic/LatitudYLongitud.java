package model.logic;

public class LatitudYLongitud 
{
	/**
	 * Latitud de la ubicación geográfica
	 */
	private double latitud; 

	/**
	 * Longitud de la ubicación geográfica
	 */

	private double longitud;

	// -----------------------------------------------------------------
	// Constructores
	// -----------------------------------------------------------------

	public LatitudYLongitud(double pLatitud, double pLongitud)
	{
		latitud = pLatitud;
		longitud = pLongitud;
	}


	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	public double darLatitud()
	{
		return latitud;
	}

	public double darLongitud()
	{
		return longitud;
	}

	
	public String toString()
	{
		return "Latitud: " + latitud + "; Longitud: " + longitud;
	}


}
