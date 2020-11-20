public class Obstinado implements Estrategia{

	@Override
	public Atributo elegirAtributo(Carta carta) {
		return carta.getAtributoObstinado();
	}

}