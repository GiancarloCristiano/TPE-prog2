public class PocimaValorFijo extends Pocima {
	private double valor;
	
	public PocimaValorFijo(String nombre, double valor) {
		super(nombre);
		this.valor = valor;
	}
	
	@Override
	public double modificarValor(Atributo atributo) {
		double valorAtributo = this.getValor();
		return valorAtributo;
	}
	
	public double getValor() {
		return valor;
	}

}