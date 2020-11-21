public class Juego {
	private int cantTurnos;	
	private static int nroRonda = 0;
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador jugadorTurno;
	private Mazo mazo;
	private boolean seJuegaConPocimas;


	public Juego(int cantTurnos, Jugador jugador1, Jugador jugador2,
			Mazo mazo, boolean seJuegaConPocimas) {
		this.cantTurnos = cantTurnos;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.mazo = mazo;
		this.jugadorTurno = jugador1;
		this.seJuegaConPocimas = seJuegaConPocimas;
	}

	public boolean seJuegaConPocimas() {
		return seJuegaConPocimas;
	}

	// INICIA JUEGO
	public void repartirCartas(){
		mazo.mezclarCartas();
		if(seJuegaConPocimas()) {
			mazo.mezclarPocimas();
			mazo.repartirPocimas();
		}
		mazo.darCartas(jugador1, jugador2);
	}

	//JUGAR
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

	//------------------------------    METODOS IMPRIMIR    -------------------------------------
	private void imprimirNroRonda() {
		System.out.println("---------------- RONDA " + nroRonda+" ----------------");
	}

	private void imprimirGanadorJuego(Jugador jugador) {
		if(jugador != null)
			System.out.println("GANÓ EL JUEGO " + jugador.getNombre().toUpperCase() + " :D" );
		else
			System.out.println("EL JUEGO TERMINÓ EMPATADO.");
	}

}