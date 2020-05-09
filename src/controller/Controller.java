package controller;

import java.util.Scanner;

import model.logic.Comparendo;
import model.logic.Mapa;
import model.logic.Modelo;
import view.View;

public class Controller {

	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){

			case 1:

				modelo = new Modelo();
				try
				{
					long start = System.currentTimeMillis();
					modelo.cargarVertices();
					modelo.cargarArcos();
					long end = System.currentTimeMillis();

					view.printMessage("\n---------");
					view.printMessage("Se cargo el grafo");
					view.printMessage("Numero total de vertices: " + modelo.darNumeroVertices());
					view.printMessage("Numero total de arcos: " + modelo.darNumeroArcos());
					view.printMessage("Tiempo de carga: " +  (end-start)/1000.0 );
					view.printMessage("\n---------");
				}

				catch (Exception e)
				{
					view.printMessage("Hubo un error al cargar los archivos: " + e.getMessage());
					e.printStackTrace();
				}

				break;				


			case 2:

				long startE = System.currentTimeMillis();
				modelo.cargarEstaciones();
				long endE = System.currentTimeMillis();

				view.printMessage("\n---------");
				view.printMessage("Se cargaron las estaciones de policia");
				view.printMessage("Total de estaciones " + modelo.darTamano());
				view.printMessage("Tiempo de carga: " +  (endE-startE)/1000.0 );
				view.printMessage("\n---------");

				break;

			case 3:

				view.printMessage("Ingrese el nombre que le quiere dar al archivo (sin .json:)");

				String nombreArchivo = lector.next();
				String rutaArchivo = "./data/" + nombreArchivo + ".json";

				try
				{
					modelo.crearJSON(rutaArchivo);
					
					view.printMessage("\n---------");
					view.printMessage("El archivo fue guardado en la ruta: " + rutaArchivo) ;
					view.printMessage("\n---------");
					
				}

				catch(Exception e)
				{
					view.printMessage("\n---------");
					view.printMessage("Hubo un error al crear el archivo: " + e.getMessage());
					e.printStackTrace();
					view.printMessage("\n---------");
				}

				break;

			case 4:

				view.printMessage("Ingrese el nombre del archivo guardado en la carpeta data (sin .json: ");
				String cargaArchivo = lector.next();
				String rutaCarga = "./data/" + cargaArchivo + ".json";
				
				try
				{
					modelo.leerJSON(rutaCarga);
					view.printMessage("\n---------");
					view.printMessage("Se cargo el grafo");
					view.printMessage("Numero total de vertices: " + modelo.darGrafoCreado().V());
					view.printMessage("Numero total de arcos: " + modelo.darGrafoCreado().E());
					view.printMessage("\n---------");
				}

				catch(Exception e)
				{
					view.printMessage("Hubo un error al crear el archivo: " + e.getMessage());
					e.printStackTrace();
				}

				break;

			case 5:
				
				Mapa map  = new Mapa(modelo, false);
				map.iniciarFrame();
				
				break;

			case 6:
				
				Mapa mapaEstaciones  = new Mapa(modelo, true);
				mapaEstaciones.iniciarFrame();
				break; 

			case 7:

				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;	

			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
