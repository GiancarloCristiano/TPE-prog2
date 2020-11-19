public class Obstinado implements Estrategia{

	@Override
	public Atributo elegirAtributo(Carta carta) {
		Atributo loQueDevuelvo = carta.getAtributoObstinado();
		return loQueDevuelvo;
	}

}