package view;

import model.logic.Modelo;

public class View 
{
	    /**
	     * Metodo constructor
	     */
	    public View()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Cargar inicial de datos vertices y arcos");
			System.out.println("2. Cargar datos de estaciones de policia");
			System.out.println("3. Imprimir un archivo JSON con el grafo");
			System.out.println("4. Crear un grafo a partir de un archivo JSON");
			System.out.println("5. Crear el mapa del grafo en la zona delimitada");
			System.out.println("6. Crear el mapa del grafo con las estaciones de policia en la zona delimitada");
			System.out.println("7. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
	
}
