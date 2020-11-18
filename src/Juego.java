import java.util.ArrayList;
import java.util.Collections;

public class Juego {
	private int cantTurnos;	
	private static int nroRonda = 1;
	private Jugador jugador1;
	private Jugador jugador2;
	private Jugador ganadorRonda;
	private Jugador perdedorRonda;
	private Mazo mazo;
	private boolean seJuegaConPocimas;
	private ArrayList<Pocima> pocimas;
	
	public Juego(int cantTurnos, Jugador jugador1, Jugador jugador2,
			Mazo mazo, boolean seJuegaConPocimas) {
		this.cantTurnos = cantTurnos;
		this.jugador1 = jugador1;
		this.jugador2 = jugador2;
		this.mazo = mazo;
		this.ganadorRonda = jugador1;
		this.perdedorRonda = jugador2;
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
	public void comparar() {
		while(!juegoTerminado()) {
			imprimirNroRonda();
			Jugador aux = ganadorRonda;
			setPerdedor();
			ganadorRonda = ganadorRonda.enfrentarse(perdedorRonda);
			if (ganadorRonda == null) {
				imprimirEmpate();
				empatar();
				ganadorRonda = aux;
			}
			nroRonda++;
		}
		Jugador ganoJuego = chequearGanador();
		imprimirGanadorJuego(ganoJuego);
	}

	/*//COMPARAR
	public void comparar() {
		while(!juegoTerminado()) {
			imprimirNroRonda();
			Jugador ganadorRonda = jugadorTurno.enfrentarse(jugadorTurnoDos, this);
			if (ganadorRonda != null) {

				//Jugador ganadorRonda = determinarRol(jugadoresRonda, 1);
				//Jugador perdedorRonda = determinarRol(jugadoresRonda, 0);
				//accionesFinalizarRonda(ganadorRonda, perdedorRonda);
			} else {
				imprimirEmpate();
				empatar();
			}
			nroRonda++;
		}
		Jugador ganoJuego = chequearGanador();
		imprimirGanadorJuego(ganoJuego);
	}*/




	/*
	public void accionesFinalizarRonda(Jugador ganadorRonda, Jugador perdedorRonda){
		ganadorRonda.recibirCarta(perdedorRonda.darCarta());
		ganadorRonda.enviarCartaAlFondo();
		setTurnos(ganadorRonda, perdedorRonda);
		imprimirEstadoCartas(ganadorRonda, perdedorRonda);
	}

	public Jugador determinarRol(ArrayList<Jugador> jugadoresRonda, int indice){
		return jugadoresRonda.get(indice);
	}*/

	public void setPerdedor(){
		if (ganadorRonda.equals(jugador1))
			perdedorRonda = jugador2;
		if (ganadorRonda.equals(jugador2))
			perdedorRonda = jugador1;
	}

	/*
	public void setTurnos(Jugador jugadorTurno, Jugador jugadorTurnoDos) {
		this.jugadorTurno = jugadorTurno;
		this.jugadorTurnoDos = jugadorTurnoDos;
	}*/

	public void empatar(){
		jugador1.enviarCartaAlFondo();
		jugador2.enviarCartaAlFondo();
	}

	private Jugador chequearGanador() {
		if(!jugador1.tieneCartas())
			return jugador2;
		if(!jugador2.tieneCartas())
			return jugador1;
		else {
			if (jugador1.cantidadCartas() > jugador2.cantidadCartas())
				return jugador1;
			if (jugador1.cantidadCartas() < jugador2.cantidadCartas())
				return jugador2;
			else
				return null;
		}
	}

	private boolean juegoTerminado(){
		if((!jugador1.tieneCartas() || !jugador2.tieneCartas())
				|| noHayMasTurnos())
			return true;
		else
			return false;
	}

	private boolean noHayMasTurnos() {
		if(cantTurnos - nroRonda == -1)
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

	public void addPocima(Pocima p) {
		if (p != null && !pocimas.contains(p))
			pocimas.add(p);
	}

	public int getCantPocimas() {
		return this.pocimas.size();
	}

	public boolean seJuegaConPocimas() {
		return seJuegaConPocimas;
	}
	
	//-----------------------------    METODOS IMPRIMIR --------------------------------------------
	private void imprimirNroRonda() {
		System.out.println("---------------- RONDA " + nroRonda+" ----------------");
	}

	/*
	public void imprimirAccionesRonda(String nombreAtributo, Carta cartaJT1, Carta cartaJT2) {
		imprimirSeleccionJturno(jugadorTurno, nombreAtributo);
		imprimirAccionJugador(jugadorTurno, nombreAtributo, cartaJT1);
		imprimirAccionJugador(jugadorTurnoDos, nombreAtributo, cartaJT2);
	}*/
/*
	private void imprimirSeleccionJturno(Jugador j, String a) {
		System.out.println("El jugador "+j.getNombre().toUpperCase()+" selecciona competir por el atributo "+ a.toUpperCase());
	}*/
	
	private void imprimirAccionJugador(Jugador j, String nombreAtributo, Carta carta) {
		System.out.println("La carta de "+j.getNombre().toUpperCase()+" es "+carta.getNombrePersonaje()+" con "+nombreAtributo+" "+carta.getValorAtributoPorNombre(nombreAtributo));
		if(carta.tienePocima())
			imprimirAccionPocima(carta, nombreAtributo);
	}

	private void imprimirAccionPocima(Carta carta, String nombreAtributo) {
		String datos = carta.getDatosPocima(nombreAtributo);
		System.out.println(datos);
	}

	private void imprimirEstadoCartas(Jugador ganador, Jugador perdedor) {
		System.out.println("Gana la ronda " + ganador.getNombre().toUpperCase()+", queda con "+ganador.cantidadCartas()+" cartas y "+perdedor.getNombre()+" posee ahora "+perdedor.cantidadCartas()+" cartas.\n");
	}
	
	private void imprimirGanadorJuego(Jugador jugador) {
		if(jugador!= null)
			System.out.println("GANÓ EL JUEGO " + jugador.getNombre().toUpperCase() +" :D" );
		else
			System.out.println("EL JUEGO TERMINÓ EMPATADO.");
	}

	private void imprimirEmpate() {
			System.out.println("La ronda resultó en EMPATE.\n");
	}

	/*
	public void linea() {
		System.out.println("--------------------------------------------------------");
	}*/

}