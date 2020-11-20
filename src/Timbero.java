public class Timbero implements Estrategia{

	@Override
	public Atributo elegirAtributo(Carta carta) {
		return carta.getAtributoRandom();
	}

}