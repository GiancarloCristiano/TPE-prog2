public class PocimaSelectiva extends Pocima {
	private String nombreAtributo;
	private double valor;
	
	public PocimaSelectiva(String nombre, String nombreAtributo, double valor) {
		super(nombre);
		this.nombreAtributo = nombreAtributo;
		this.valor = valor;
	}

	@Override
	public double modificarValor(Atributo atributo) {
		if(atributo.getNombre().equals(this.getNombreAtributo())) {
			double valorAtributo = atributo.getValor();
			return valorAtributo += valorAtributo * this.getValor() / 100;
		}
		else
			return atributo.getValor();
	}

	public double getValor() {
		return valor;
	}

	public String getNombreAtributo() {
		return nombreAtributo;
	}

}