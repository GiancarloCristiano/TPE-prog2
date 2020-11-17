
public class Atributo {
	private String nombre;
	private double valor;
	
	public Atributo(String nombre, double valor) {
		this.nombre = nombre;
		this.valor = valor;
	}
	
	public boolean equals(Object o) { //EQUALS ATRIBUTO
		try {
			Atributo unAtributo = (Atributo) o;
			if(unAtributo.getNombre() == this.nombre)
				return true;
			else
				return false;
		}
		catch(Exception exc) {
			return false;
		}
	}

	public String toString() {
		return this.getNombre() + ": " + this.getValor()+" ";
	}
	
	//SETS AND GETS
	public String getNombre() {
		return nombre;
	}

	public double getValor() {
		return valor;
	}
	
}
