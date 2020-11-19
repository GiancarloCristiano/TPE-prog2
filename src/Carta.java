import java.util.ArrayList;

public class Carta {
	private String nombrePersonaje;
	private ArrayList<Atributo> atributos;
	private Pocima pocima;
	
	public Carta(String nombrePersonaje) {
		this.nombrePersonaje = nombrePersonaje;
		atributos = new ArrayList<Atributo>();
		this.pocima = null;
	}
	
	public String toString() {
		return this.getNombrePersonaje().toUpperCase() + "    "+ atributos.toString();
	}


	public Carta compararCartas (Carta cartaJ2, String nombreAtributo, String j1, String j2) {
		double valorJ1 = this.getValorAtributoPorNombre(nombreAtributo);
		imprimirValoresCarta (valorJ1, this.getNombrePersonaje(), nombreAtributo, j1);
		valorJ1 = metodosPocima(this, valorJ1, nombreAtributo);
		double valorJ2 = cartaJ2.getValorAtributoPorNombre(nombreAtributo);
		imprimirValoresCarta (valorJ2, cartaJ2.getNombrePersonaje(), nombreAtributo, j2);
		valorJ2 = metodosPocima(cartaJ2, valorJ2, nombreAtributo);
		return compararValores(valorJ1, valorJ2, cartaJ2);
	}

	public void imprimirValoresCarta (Double valor, String nombre, String atributo, String jugador){
		System.out.print("La carta de " + jugador.toUpperCase() + " es " + nombre + " con " + atributo + " " + valor + ".");
	}

	public Double metodosPocima(Carta carta, Double valor, String nombreAtributo) {
		if (carta.tienePocima()) {
			double valorMasPocima = carta.aplicarPocima(valor, nombreAtributo);
			valor = valorMasPocima;
			imprimirPocima(carta.pocima.getNombre(), valorMasPocima);
			carta.borrarPocima();
		}
		imprimirSalto();
		return valor;
	}

	public void imprimirPocima (String nombrePocima, Double valorPocima){
		System.out.print(" Se aplicó la pócima " + nombrePocima + ", valor resultante: " + valorPocima + ".");
	}

	public Carta compararValores(double valorJ1, double valorJ2, Carta cartaJ2) {
		if (valorJ1 > valorJ2)
			return this;
		if (valorJ1 < valorJ2)
			return cartaJ2;
		else return null;
	}


	public boolean esDelMismoTipo(Carta unaCarta) { 	
		ArrayList<String> listaUno = new ArrayList<String>();
		ArrayList<String> listaDos = new ArrayList<String>();
		listaUno = unaCarta.getNombreAtributos();
		listaDos = this.getNombreAtributos();
		for(int i = 0; i < listaUno.size(); i++) {
			if (!listaDos.contains(listaUno.get(i))) {
				//System.out.println("descartado " + unaCarta.getNombrePersonaje());
				return false;
			}
		}
		//System.out.println("agregado " + unaCarta.getNombrePersonaje());
		return true;	
	}
	
	public double getValorAtributoPorNombre(String atributo) {
		for(int i = 0; i < atributos.size(); i++) {
			Atributo atributoAux = atributos.get(i);
			if (atributoAux.getNombre().equals(atributo))
				return atributoAux.getValor();
		}
		return -1;
	}

	public ArrayList<String> getNombreAtributos(){
		ArrayList<String> nombres = new ArrayList<String>();
		for (int i = 0; i < atributos.size(); i++)
			nombres.add(atributos.get(i).getNombre());
		return nombres;
	}
	
	public void addAtributo(Atributo unAtributo) {
		if(!atributos.contains(unAtributo)) {
			atributos.add(unAtributo);
		}
	}
	
	public boolean equals(Object o) {
		try {
			Carta unaCarta = (Carta) o;
			if (unaCarta.getNombrePersonaje().equals(this.getNombrePersonaje()))
				return true;
			else
				return false;
		}
		catch(Exception exc){
			return false;
		}
	}


	//------------- NUEVOS -----------------
	public Atributo getAtributoMaxValor() {
		Atributo aux = new Atributo(null, 0);
		for(Atributo atributo: atributos){
			if (atributo.getValor() > aux.getValor())
				aux = atributo;
		}
		return aux;
	}
	
	public Atributo getAtributoRandom() {
		int i = (int) Math.floor(Math.random() * atributos.size());
		return this.atributos.get(i);
	}
		
	public Atributo getAtributoObstinado() {
		return atributos.get(0);
	}
	

	public double aplicarPocima(double valorAtributoJpri, String nombreAtributo) {
		Atributo atributo = new Atributo(nombreAtributo, valorAtributoJpri);
		double valorAtributoConPocima = pocima.modificarValor(atributo);
		return valorAtributoConPocima;
	}

	public void imprimirSalto() {
		System.out.println();
	}
	
	//GETS AND SETS
	public String getNombrePersonaje() {
		return nombrePersonaje;
	}


	public void setPocima(Pocima pocima) {
		this.pocima = pocima;
	}
	
	public boolean tienePocima() {
		return this.pocima != null;
	}
	
	public void borrarPocima() {
		this.pocima = null;
	}


}