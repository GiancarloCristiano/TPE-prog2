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
	
	public Jugador enfrentarse(Jugador jugadorTurnoDos, Juego juego) {
		Atributo atributoJPri = this.empezarRonda();
		String nombreAtributo = atributoJPri.getNombre();
		Carta cartaJT1 = cartasJugador.elegirPrimerCarta();
		Carta cartaJT2 = jugadorTurnoDos.elegirPrimerCarta();
		juego.imprimirAccionesRonda(nombreAtributo, cartaJT1, cartaJT2);
		Carta ganadora = cartaJT1.compararCartas(cartaJT2, nombreAtributo);
		if (this.tieneCartaGanadora(ganadora)) {
			accionesFinalizarRonda(this, jugadorTurnoDos);
			return this;

		}
		else if (jugadorTurnoDos.tieneCartaGanadora(ganadora)) {
			accionesFinalizarRonda(jugadorTurnoDos, this);
			return jugadorTurnoDos;
		}
		return null;
	}



	public void accionesFinalizarRonda(Jugador ganadorRonda, Jugador perdedorRonda) {
		ganadorRonda.recibirCarta(perdedorRonda.darCarta());
		ganadorRonda.enviarCartaAlFondo();
		setTurnos(ganadorRonda, perdedorRonda);
		imprimirEstadoCartas(ganadorRonda, perdedorRonda);
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