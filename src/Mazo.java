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

	public Mazo() {
		cartas = new ArrayList<Carta>();
	}
	
    public void cargarMazo(String jsonFile) {
        File jsonInputFile = new File(jsonFile);
        InputStream is;
        try {
            is = new FileInputStream(jsonInputFile);
            JsonReader reader = Json.createReader(is);
            JsonArray cartas = (JsonArray) reader.readObject().getJsonArray("cartas");
            for (JsonObject cartaJson : cartas.getValuesAs(JsonObject.class)) {
                String nombreCarta = cartaJson.getString("nombre");
                Carta unaCarta = new Carta(nombreCarta);
                JsonObject atributos = (JsonObject) cartaJson.getJsonObject("atributos");
                for (String nombreAtributo:atributos.keySet()) {
                	Atributo unAtributo = new Atributo(nombreAtributo, atributos.getInt(nombreAtributo));
                    unaCarta.addAtributo(unAtributo);
                }
                if (cartaValida(unaCarta))
                	this.addCarta(unaCarta);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
	}


	public boolean cartaValida(Carta unaCarta){
		return ((this.cartas.isEmpty()) || elegirPrimerCarta().esDelMismoTipo(unaCarta));
	}

	public void addCarta(Carta unaCarta) {
		cartas.add(unaCarta);
	}

	public void mezclarCartas() {
		Collections.shuffle(cartas);
	}


	protected void darCartas(Jugador jugador1, Jugador jugador2) {
		while (cartas.size() != 0) {
			jugador1.recibirCarta(this.darCarta());
			if (!cartas.isEmpty())
				jugador2.recibirCarta(this.darCarta());
		}
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

	//Despues de mezclarlas Las repartimos al azar.
	public void addPocimaAcarta(Pocima pocima) {
			int i = (int) (Math.random() * cartas.size());
			Carta cartaAux = cartas.get(i);
			cartaAux.setPocima(pocima);
	}


	public boolean tieneCartaGanadora(Carta ganadora) {
		if (this.elegirPrimerCarta().equals(ganadora))
			return true;
		else
			return false;
	}
	
}