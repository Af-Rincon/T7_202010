package model.logic;

import java.awt.BorderLayout;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.teamdev.jxmaps.Circle;
import com.teamdev.jxmaps.CircleOptions;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.LatLngBounds;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;

import model.data_structures.GrafoNoDirigido;
import model.data_structures.GrafoNoDirigido.Vertice;
import model.data_structures.SeparateChainingHash;

import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Polyline;
import com.teamdev.jxmaps.PolylineOptions;
import com.teamdev.jxmaps.swing.MapView;

public class Mapa extends MapView
{

	/**
	 * Constantes: zona delimitada 
	 */
	public static double MIN_LONGITUD =  -74.094723 ;

	public static double MIN_LATITUD = 4.597714;

	public static double MAX_LONGITUD = -74.062707;

	public static double MAX_LATITUD = 4.621360;

	/**
	 * Mapa
	 */
	private Map mapa; 

	/**
	 * Modelo
	 */
	private Modelo modelo; 


	public Mapa(Modelo pModelo, boolean pintarEstaciones)
	{
		GrafoNoDirigido<Integer, LatitudYLongitud> grafo = pModelo.darGrafo();
		SeparateChainingHash<Integer,GrafoNoDirigido<Integer,LatitudYLongitud>.Vertice<Integer,LatitudYLongitud>> vertices = grafo.darVertices();

		setOnMapReadyHandler(new MapReadyHandler() 
		{
			@Override
			public void onMapReady(MapStatus status)
			{
				if(status == MapStatus.MAP_STATUS_OK )
				{
					mapa = getMap();

					//Opciones de dibujo

					//Circulos
					CircleOptions circulos = new CircleOptions();
					circulos.setFillColor("#CD5C5C");
					circulos.setFillOpacity(1);
					circulos.setRadius(20);

					//Caminos
					PolylineOptions caminos = new PolylineOptions(); 
					caminos.setStrokeColor("#E9967A");
					caminos.setStrokeOpacity(1);
					caminos.setStrokeWeight(1.5);



					//Dibujar todos los vertices
					Iterator<Integer> v = vertices.keys();
					int i = 0;

					while(v.hasNext()&&i<20000)
					{
						int id = v.next();
						double latitudActual = grafo.getInfoVertex(id).darLatitud();
						double longitudActual = grafo.getInfoVertex(id).darLongitud();

						if(latitudActual >= MIN_LATITUD && latitudActual <= MAX_LATITUD && longitudActual >= MIN_LONGITUD && longitudActual <= MAX_LONGITUD)
						{
							Circle vertice = new Circle(mapa);
							vertice.setOptions(circulos);
							vertice.setCenter(new LatLng(latitudActual, longitudActual));

							Iterator<Integer> adyacentes = grafo.adj(id).iterator();
							LatLng[] arco = new LatLng[2];
							arco[0] = new LatLng(latitudActual, longitudActual);

							while(adyacentes.hasNext())
							{
								int idAdyacente = adyacentes.next();
								double latitudAdyacente = grafo.getInfoVertex(idAdyacente).darLatitud();
								double longitudAdyacente = grafo.getInfoVertex(idAdyacente).darLongitud();

								if(latitudAdyacente >= MIN_LATITUD && latitudAdyacente <= MAX_LATITUD && longitudAdyacente >= MIN_LONGITUD && longitudAdyacente <= MAX_LONGITUD)
								{
									arco[1] = new LatLng(latitudAdyacente, longitudAdyacente);
									Polyline a = new Polyline(mapa);
									a.setOptions(caminos);
									a.setPath(arco);
								}

							}
						}


						i++;

					}

					if(pintarEstaciones == true)
					{
						//Estaciones de policia
						CircleOptions circulosPolicia = new CircleOptions();
						circulosPolicia.setFillColor("#B0DFF5" );
						circulosPolicia.setFillOpacity(0.5);
						circulosPolicia.setRadius(50);

						//Pintar las que entran
						Iterator<EstacionPolicia> estaciones = modelo.darEstaciones().iterator();

						while(estaciones.hasNext())
						{
							EstacionPolicia estacionActual = estaciones.next();
							double lat = estacionActual.darLatitud();
							double lon = estacionActual.darLongitud();

							
								Circle estacion = new Circle(mapa);
								estacion.setOptions(circulosPolicia);
								estacion.setCenter(new LatLng(lat, lon));


							

						}
					}

					iniciarMapa(mapa);




				}
			}

		});
	}


	public void iniciarMapa(Map mapa)
	{
		MapOptions opciones = new MapOptions();
		MapTypeControlOptions opcionesControl = new MapTypeControlOptions();

		opcionesControl.setPosition(ControlPosition.TOP_RIGHT);
		opciones.setMapTypeControlOptions(opcionesControl);

		mapa.setOptions(opciones);
		mapa.fitBounds(new LatLngBounds(new LatLng(MIN_LATITUD, MIN_LONGITUD), new LatLng(MAX_LATITUD, MAX_LONGITUD)));;
		mapa.setZoom(16.0);
	}

	public void iniciarFrame()
	{
		JFrame frame = new JFrame("Mapa");
		
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(this, BorderLayout.CENTER);
		frame.setSize(700, 700);
		
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
	}


}
