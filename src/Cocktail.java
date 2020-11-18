public class Cocktail extends Pocima {
	Pocima p1;
	Pocima p2;
	
	public Cocktail(String nombre, Pocima p1, Pocima p2) {
		super(nombre);
		this.p1 = p1;
		this.p2 = p2;
	}
	
	@Override
	public double modificarValor(Atributo atributo) {
		Atributo a = new Atributo (atributo.getNombre(), p1.modificarValor(atributo));
		Atributo b = new Atributo (atributo.getNombre(), p2.modificarValor(a));
		return b.getValor();
	}

}