public abstract class Pocima {
	private String nombre;
	
	public Pocima(String nombre) {
		this.nombre = nombre;
	}
	
	public abstract double modificarValor(Atributo atributo);

	public String getNombre() {
		return nombre;
	}

}