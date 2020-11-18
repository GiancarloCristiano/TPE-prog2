public class Jugador implements Estrategia{
	private String nombre;
	protected Mazo cartasJugador;
	private Estrategia estrategia;
	
	public Jugador(String nombre){
		this.nombre = nombre;
		cartasJugador = new Mazo();
	}
	
	public Jugador enfrentarse(Jugador segundo) {
		Atributo atributoSelec = this.empezarRonda();
		String nombreAtributo = atributoSelec.getNombre();
		imprimirSeleccionAtributo(nombreAtributo);
		Carta carta1 = this.elegirPrimerCarta();
		Carta carta2 = segundo.elegirPrimerCarta();
		Carta ganadora = carta1.compararCartas(carta2, nombreAtributo, this.getNombre(), segundo.getNombre());
		return definirGanadorRonda(this, segundo, ganadora);
	}

	public void imprimirSeleccionAtributo(String nombreAtributo){
		System.out.println("El jugador " + this.getNombre().toUpperCase() + " selecciona competir por el atributo " + nombreAtributo.toUpperCase());
	}

	public Jugador definirGanadorRonda(Jugador j1, Jugador j2, Carta ganadora){
		if (j1.tieneCartaGanadora(ganadora))
			entregarCarta(this, j2);
		else if (j2.tieneCartaGanadora(ganadora)) {
			entregarCarta(j2, this);
			return j2;
		}
		else
			empatar(this, j2);
		return j1;
	}

	public boolean tieneCartaGanadora(Carta ganadora) {
		return cartasJugador.tieneCartaGanadora(ganadora);
	}

	public void entregarCarta(Jugador ganadorRonda, Jugador perdedorRonda) {
		ganadorRonda.recibirCarta(perdedorRonda.darCarta());
		ganadorRonda.enviarCartaAlFondo();
		imprimirCantidadCartas(ganadorRonda, perdedorRonda);
	}

	public void recibirCarta(Carta unaCarta) {
		cartasJugador.addCarta(unaCarta);
	}

	public void enviarCartaAlFondo() {
		cartasJugador.enviarCartaAlFondo();
	}

	private void imprimirCantidadCartas(Jugador ganador, Jugador perdedor) {
		System.out.println("Gana la ronda " + ganador.getNombre().toUpperCase() + " y queda con " + ganador.cantidadCartas()+" cartas ("
				+ perdedor.getNombre().toUpperCase() + " posee ahora " + perdedor.cantidadCartas()+" cartas).\n");
	}


	public int cantidadCartas() {
		return cartasJugador.getCantCartas();
	}

	public void addPocimaAcarta(Pocima pocima) {
		cartasJugador.addPocimaAcarta(pocima);
	}


	public Carta elegirPrimerCarta() {
		return cartasJugador.elegirPrimerCarta();
	}

	
	public Carta darCarta() {
		return cartasJugador.darCarta();
	}


	public Atributo empezarRonda() {
		Carta primerCartaJ1 = cartasJugador.elegirPrimerCarta();
		Atributo atributoSeleccionado = this.elegirAtributo(primerCartaJ1);
		return atributoSeleccionado;
	}

	@Override
	public Atributo elegirAtributo(Carta carta) {
		return estrategia.elegirAtributo(carta);
	}


	public boolean tieneCartas() {
		if(cartasJugador.getCantCartas() > 0)
			return true;
		else
			return false;
	}

	public void empatar(Jugador j1, Jugador j2){
		System.out.println("La ronda resultó en EMPATE.\n");
		j1.enviarCartaAlFondo();
		j2.enviarCartaAlFondo();
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