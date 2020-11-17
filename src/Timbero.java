public class Timbero implements Estrategia{

	@Override
	public Atributo elegirAtributo(Carta carta) {
		//delego el obtener atributo random a carta
		Atributo loQueDevuelvo = carta.getAtributoRandom();
		return loQueDevuelvo;
	}

}