
public class PocimaDecrementadora extends Pocima {
	private double valor;
	
	public PocimaDecrementadora(String nombre, double valor) {
		super(nombre);
		this.valor = valor;
	}

	@Override
	public double modificarValor(Atributo atributo) {
		double valorAtributo = atributo.getValor();
		return valorAtributo -= valorAtributo * this.getValor() / 100;
	}

	public double getValor() {
		return valor;
	}

}
