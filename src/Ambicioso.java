public class Ambicioso implements Estrategia{

	@Override
	public Atributo elegirAtributo(Carta carta) {
		return carta.getAtributoMaxValor();
	}
}