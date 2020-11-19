public class Timbero implements Estrategia{

	@Override
	public Atributo elegirAtributo(Carta carta) {
		Atributo loQueDevuelvo = carta.getAtributoRandom();
		return loQueDevuelvo;
	}

}