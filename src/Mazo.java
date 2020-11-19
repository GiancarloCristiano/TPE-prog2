import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class Mazo {
	protected ArrayList<Carta> cartas;
	//private ArrayList<Integer> randoms;
	
	public Mazo() {
		cartas = new ArrayList<Carta>();
		//randoms = new ArrayList<Integer>();
	}
	
    public void cargarMazo(String jsonFile) {
        //URL url = getClass().getResource(jsonFile);
        File jsonInputFile = new File(jsonFile);
        InputStream is;
        //Mazo mazo = new Mazo(); 
        try {
            is = new FileInputStream(jsonInputFile);
            // Creo el objeto JsonReader de Json.
            JsonReader reader = Json.createReader(is);
            // Obtenemos el JsonObject a partir del JsonReader.
            JsonArray cartas = (JsonArray) reader.readObject().getJsonArray("cartas");
            for (JsonObject cartaJson : cartas.getValuesAs(JsonObject.class)) {
                String nombreCarta = cartaJson.getString("nombre");
                Carta unaCarta = new Carta(nombreCarta);
                JsonObject atributos = (JsonObject) cartaJson.getJsonObject("atributos");
                //String atributosStr = "";
                for (String nombreAtributo:atributos.keySet()) {
                	Atributo unAtributo = new Atributo(nombreAtributo, atributos.getInt(nombreAtributo));
                    unaCarta.addAtributo(unAtributo);
                	//atributosStr = atributosStr + nombreAtributo + ": " +
                            //atributos.getInt(nombreAtributo) + "; ";
                }
                this.addCarta(unaCarta);
                //System.out.println(nombreCarta+"\t\t\t"+atributosStr);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

	}
	/*
	public void chequearMazo() {
		for(int i = 0; i < getTamanioMazo(); i++) {
			Carta carta = cartas.get(0);
			Carta cartaAux = cartas.get(i);
			if(!carta.esDelMismoTipo(cartaAux))
				cartas.remove(i);
		}
	}*/

	public void addCarta(Carta unaCarta) {
		Carta c = this.elegirPrimerCarta();
		if (cartas.size() < 1 || c.esDelMismoTipo(unaCarta)) {
			if (!cartas.contains(unaCarta))
				cartas.add(unaCarta);
		}
	}

	/*public void addCarta(Carta unaCarta) {
		if(cartas.size() < 1 || !cartas.contains(unaCarta))
			cartas.add(unaCarta);
	}*/
	
	public void mostrarMazo() {
		for(int i = 0; i < getTamanioMazo(); i++) {
			Carta cartaAux = cartas.get(i);
			System.out.println(cartaAux.toString());
		}
	}
	
	public String toString(Carta carta){
        return carta.toString();
    }
	
	public ArrayList<Carta> getMazo(){
		return new ArrayList<Carta>(this.cartas);
	}
	
	private void mezclarCartas() {
		Collections.shuffle(cartas);
	}
	
	protected void darCartas(Jugador jugador1, Jugador jugador2) {
		mezclarCartas();
		int i = 0;
		while (cartas.size() != 0) {
			jugador1.recibirCarta(cartas.get(i));
			cartas.remove(i);
			if (!cartas.isEmpty()){
				jugador2.recibirCarta(cartas.get(i));
				cartas.remove(i);
			}
		}
	}
	
	public int getTamanioMazo() {
		return cartas.size();
	}


	public void eliminarCarta() {
		if (cartas.size() > 0) {
			cartas.remove(0);
		}
	}
	
	public Carta elegirPrimerCarta() {
		if (cartas.size() > 0)
			return cartas.get(0);
		else return null;
	}
	
	public void enviarCartaAlFondo() {
		Carta c = elegirPrimerCarta();
		eliminarCarta();
		addCarta(c);
	}
	
	public int getCantCartas() {
		return cartas.size();
	}
	
	public Carta darCarta() {
		Carta c = elegirPrimerCarta();
		eliminarCarta();
		return c;
	}

	/* ESTE HACE QUE SI ESA CARTA TENIA POCIMA, PIERDA LA QUE LE QUIERO ADD.
	public void addPocimaAcarta(Pocima pocima) {
		int i = (int) (Math.random() * cartas.size());
		Carta cartaAux = cartas.get(i);
		if (!cartaAux.tienePocima()){
			cartaAux.setPocima(pocima);
			}
		}
	*/

	public void addPocimaAcarta(Pocima pocima) {
		boolean fin = false;
		while (!fin) {
			int i = (int) (Math.random() * cartas.size());
			Carta cartaAux = cartas.get(i);
			if (!cartaAux.tienePocima()){
				cartaAux.setPocima(pocima);
				fin = true;
			}
		}
	}

	/*ESTO ANDA PERO TUVE QUE CREAR UNA LISTA AL PEDO
	public void addPocimaAcarta(Pocima pocima) {
		boolean agregada = false;
		while (!agregada) {
			int i = (int) (Math.random() * cartas.size());
			if (!randoms.contains(i)){
				randoms.add(i);
				Carta cartaAux = cartas.get(i);
				cartaAux.setPocima(pocima);
				agregada = true;
			}
		}
	}*/


	/*ESTE ERA EL VIEJO QUE SE PERDIAN EN PROMEDIO 2,5 POCIMAS X JUEGO.
	public void addPocimaAcarta(Pocima pocima) {
		int i = (int) (Math.random() * cartas.size());
		Carta cartaAux = cartas.get(i);
		cartaAux.setPocima(pocima);
	}*/

	
	public boolean tieneCartaGanadora(Carta ganadora) {
		if (this.elegirPrimerCarta().equals(ganadora))
			return true;
		else
			return false;
	}
	
}