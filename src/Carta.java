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
	
	//----------------------------------
	public Carta compararCartas(Carta cartaJT2, String nombreAtributo) {
		double valorJT1 = this.getValorAtributoPorNombre(nombreAtributo);
		double valorJT2 = cartaJT2.getValorAtributoPorNombre(nombreAtributo);
		if(this.tienePocima()) {
			double valorJT1masPocima = this.aplicarPocima(valorJT1, nombreAtributo);
			valorJT1 = valorJT1masPocima;
		}
		if(cartaJT2.tienePocima()) {
			double valorJT2masPocima = cartaJT2.aplicarPocima(valorJT2, nombreAtributo);
			valorJT2 = valorJT2masPocima;
		}
		return compararValores(valorJT1, valorJT2, cartaJT2);
	}
	
	public Carta compararValores(double valorJT1, double valorJT2, Carta cartaJT2) {
		if(valorJT1 > valorJT2) 
			return this;
		if(valorJT1 < valorJT2)
			return cartaJT2;
		else
			return null;
	}
	//----------------------------------
	
	public boolean esDelMismoTipo(Carta unaCarta) { 	
		ArrayList<String> listaUno = new ArrayList<String>();
		ArrayList<String> listaDos = new ArrayList<String>();
		listaUno = unaCarta.getNombreAtributos();
		listaDos = this.getNombreAtributos();
		for(int i =0; i < listaUno.size(); i++) {
			if(!listaDos.contains(listaUno.get(i)))
				return false;									
		}
		return true;	
	}
	
	public double getValorAtributoPorNombre(String atributo) {
		for(int i = 0; i < atributos.size(); i++) {
			Atributo atributoAux = atributos.get(i);
			if(atributoAux.getNombre().equals(atributo))
				return atributoAux.getValor();
		}
		return -1;
	}

	public ArrayList<String> getNombreAtributos(){
		ArrayList<String> nombres = new ArrayList<String>();
		for(int i= 0; i < atributos.size(); i++) {
			nombres.add(atributos.get(i).getNombre());
		}
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
			if(unaCarta.getNombrePersonaje().equals(this.getNombrePersonaje()))
				return true;
			else
				return false;
		}
		catch(Exception exc){
			return false;
		}
	}

	public ArrayList<Atributo> getAtributos() {
		return new ArrayList<Atributo> (this.atributos);
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
	
	public String getDatosPocima(String nombreAtributo) {
		Pocima p = this.getPocima();
		String nombrePocima = p.getNombre();
		double valorAtributoJpri = this.getValorAtributoPorNombre(nombreAtributo);
		double valorAPocima = this.aplicarPocima(valorAtributoJpri, nombreAtributo);
		return devolverDatosPocima(nombrePocima, valorAPocima);
	}
	
	public String devolverDatosPocima(String nombrePocima, double valorAPocima) {
		return "Se aplicó la pocima " + nombrePocima.toUpperCase() + ". Valor resultante: " + valorAPocima;
	}
	
	//GETS AND SETS
	public String getNombrePersonaje() {
		return nombrePersonaje;
	}

	public Pocima getPocima() {
		return pocima;
	}

	public void setPocima(Pocima pocima) {
		this.pocima = pocima;
	}
	
	public boolean tienePocima() {
		return this.pocima != null;
	}
	
	/*public void borrarPocima() {
		this.pocima = null;
	}*/
}