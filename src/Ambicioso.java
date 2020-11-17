
public class Ambicioso implements Estrategia{

	@Override
	public Atributo elegirAtributo(Carta carta) {
		Atributo loQueDevuelvo = carta.getAtributoMaxValor();
		return loQueDevuelvo;
	}
}
