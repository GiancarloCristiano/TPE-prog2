//import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Objects;

public class Jugador implements Estrategia{
	private String nombre;
	protected Mazo cartasJugador;
	private Estrategia estrategia;
	
	public Jugador(String nombre){
		this.nombre = nombre;
		cartasJugador = new Mazo();
	}
	
	public Jugador enfrentarse(Jugador perdedor) {
		Atributo atributoJPri = this.empezarRonda();
		String nombreAtributo = atributoJPri.getNombre();
		imprimirSeleccionAtributo(nombreAtributo);
		Carta carta1 = cartasJugador.elegirPrimerCarta();
		Carta carta2 = perdedor.elegirPrimerCarta();
		Carta ganadora = carta1.compararCartas(carta2, nombreAtributo, this.getNombre(), perdedor.getNombre());
		if (this.tieneCartaGanadora(ganadora)) {
			accionesFinalizarRonda(this, perdedor);
			return this;
		}
		if (perdedor.tieneCartaGanadora(ganadora)) {
			accionesFinalizarRonda(perdedor, this);
			return perdedor;
		}
		return null;
	}


	public void imprimirSeleccionAtributo(String nombreAtributo){
		System.out.println("El jugador " + this.getNombre().toUpperCase() + " selecciona competir por el atributo "+ nombreAtributo.toUpperCase());
	}


	public void accionesFinalizarRonda(Jugador ganadorRonda, Jugador perdedorRonda) {
		ganadorRonda.recibirCarta(perdedorRonda.darCarta());
		ganadorRonda.enviarCartaAlFondo();
		imprimirEstadoCartas(ganadorRonda, perdedorRonda);
	}


	private void imprimirEstadoCartas(Jugador ganador, Jugador perdedor) {
		System.out.println("Gana la ronda " + ganador.getNombre().toUpperCase()+", queda con "+ganador.cantidadCartas()+" cartas y "+perdedor.getNombre()+" posee ahora "+perdedor.cantidadCartas()+" cartas.\n");
	}


	public boolean tieneCartaGanadora(Carta ganadora) {
		return cartasJugador.tieneCartaGanadora(ganadora);
	}

	public int cantidadCartas() {
		return cartasJugador.getCantCartas();
	}


	public void addPocimaAcarta(Pocima pocima) {
		cartasJugador.addPocimaAcarta(pocima);
	}

	public void recibirCarta(Carta unaCarta) {
		cartasJugador.addCarta(unaCarta);
	}
	
	private void removerCarta() {
		cartasJugador.eliminarCarta();
	}
	

	public Carta elegirPrimerCarta() {
		return cartasJugador.elegirPrimerCarta();
	}
	
	public Carta darCarta() {
		return cartasJugador.darCarta();
	}
	
	public void enviarCartaAlFondo() {
		cartasJugador.enviarCartaAlFondo();
	}
	
	@Override
	public Atributo elegirAtributo(Carta carta) {
		return estrategia.elegirAtributo(carta);
	}
	
	public Atributo empezarRonda() {
		Carta primerCartaJ1 = cartasJugador.elegirPrimerCarta();
		Atributo atributoSeleccionado = this.elegirAtributo(primerCartaJ1);
		return atributoSeleccionado;
	}
	
	public boolean tieneCartas() {
		if(cartasJugador.getCantCartas() > 0)
			return true;
		else
			return false;
	}
	
	//GET AND SET
	public String getNombre() {
		return nombre;
	}

	public void setEstrategia(Estrategia estrategia) {
		this.estrategia = estrategia;
	}


	@Override
	public boolean equals(Object o) {
		try {
			Jugador jugador = (Jugador) o;
			if (jugador.getNombre().equals(this.getNombre()))
				return true;
			else
				return false;
		} catch (Exception exc) {
			return false;
		}
	}

}