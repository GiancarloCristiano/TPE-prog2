import java.util.ArrayList;
import java.util.Collections;

public class Juego {
	private int cantTurnos;	
	private static int nroRonda = 0;
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador jugadorTurno;
	private Mazo mazo;
	private boolean seJuegaConPocimas;
	private ArrayList<Pocima> pocimas;
	
	public Juego(int cantTurnos, Jugador jugador1, Jugador jugador2,
			Mazo mazo, boolean seJuegaConPocimas) {
		this.cantTurnos = cantTurnos;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.mazo = mazo;
		this.jugadorTurno = jugador1;
		this.seJuegaConPocimas = seJuegaConPocimas;
		pocimas = new ArrayList<Pocima>();
	}
	
	// INICIA JUEGO
	public void repartirCartas(){ 	
		mazo.darCartas(jugador1, jugador2);
		if(seJuegaConPocimas()) {
			mezclarPocimas();
			repartirPocimas();
		}
	}

	//COMPARAR
	public void jugar() {
		while(!juegoTerminado()) {
			nroRonda ++;
			imprimirNroRonda();
			enfrentarJugadores();
		}
		Jugador ganoJuego = chequearGanador();
		imprimirGanadorJuego(ganoJuego);
	}

	public void enfrentarJugadores(){
		if (jugadorTurno.equals(jugador1))
			jugadorTurno = jugadorTurno.enfrentarse(jugador2);
		else
			jugadorTurno = jugadorTurno.enfrentarse(jugador1);
	}

	private Jugador chequearGanador() {
		if (jugador1.cantidadCartas() > jugador2.cantidadCartas())
			return jugador1;
		if (jugador1.cantidadCartas() < jugador2.cantidadCartas())
			return jugador2;
		else	return null;
	}

	private boolean juegoTerminado(){
		if((!jugador1.tieneCartas() || !jugador2.tieneCartas())
				|| noHayMasTurnos())
			return true;
		else
			return false;
	}

	private boolean noHayMasTurnos() {
		if(cantTurnos - nroRonda == 0)
			return true;
		else
			return false;
	}

	//----------------------------- POCIMAS --------------------------------------------
	private void repartirPocimas() {
		while (this.getCantPocimas() != 0){
			int i = 0;
			jugador1.addPocimaAcarta(this.pocimas.get(i));
			pocimas.remove(i);
			jugador2.addPocimaAcarta(this.pocimas.get(i));
			pocimas.remove(i);
		}
	}

	private void mezclarPocimas() {
		Collections.shuffle(this.pocimas);
	}

	public void addPocima (Pocima p) {
		if (p != null && !pocimas.contains(p))
			pocimas.add(p);
	}

	public int getCantPocimas() {
		return this.pocimas.size();
	}

	public boolean seJuegaConPocimas() {
		return seJuegaConPocimas;
	}
	
	//------------------------------    METODOS IMPRIMIR    -------------------------------------
	private void imprimirNroRonda() {
		System.out.println("---------------- RONDA " + nroRonda+" ----------------");
	}

	private void imprimirGanadorJuego(Jugador jugador) {
		if(jugador!= null)
			System.out.println("GANÓ EL JUEGO " + jugador.getNombre().toUpperCase() +" :D" );
		else
			System.out.println("EL JUEGO TERMINÓ EMPATADO.");
	}

}