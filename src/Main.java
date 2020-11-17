public class Main {
    public static void main(String[] args) {
        String mazoPath = "./src/superheroes.json";
        Mazo mazo = new Mazo();
        mazo.cargarMazo(mazoPath);
        Jugador gian = new Jugador("gian");
        Jugador octi = new Jugador("octi");
        Juego juego = new Juego(100, octi, gian, mazo, true);
        Timbero timbero = new Timbero(); 
        Ambicioso ambicioso = new Ambicioso(); 
        //Obstinado obstinado = new Obstinado();
        PocimaIncrementadora fortalecedora = new PocimaIncrementadora("fortalecedora", 20);
        PocimaIncrementadora fortalecedoraDos = new PocimaIncrementadora("fortalecedora", 30);
        PocimaIncrementadora fortalecedoraPlus = new PocimaIncrementadora("fortalecedoraPlus", 50);
        PocimaIncrementadora fortalecedoraPlusDos = new PocimaIncrementadora("fortalecedoraPlus", 60);
        PocimaDecrementadora kriptonita = new PocimaDecrementadora("kriptonita", 25);
        PocimaDecrementadora kriptonitaDos = new PocimaDecrementadora("kriptonita", 35);
        PocimaDecrementadora reductorPlomo = new PocimaDecrementadora("reductorPlomo", 55);
        PocimaDecrementadora reductorPlomoDos = new PocimaDecrementadora("reductorPlomo", 65);
        PocimaValorFijo valeCuatro = new PocimaValorFijo("valeCuatro", 4);
        PocimaValorFijo valeCuatroDos = new PocimaValorFijo("valeCuatro", 6);
        PocimaValorFijo numeroMagico = new PocimaValorFijo("numeroMagico", 23);
        PocimaValorFijo numeroMagicoDos = new PocimaValorFijo("numeroMagico", 24);
        PocimaSelectiva PSF = new PocimaSelectiva("Selectiva Fuerza", "fuerza", 35);
        PocimaSelectiva PSFdos = new PocimaSelectiva("Selectiva Fuerza", "fuerza", 36);
        PocimaSelectiva PSP = new PocimaSelectiva("Selectiva Peso", "peso", 43);
        PocimaSelectiva PSPdos = new PocimaSelectiva("Selectiva Peso", "peso", 44);
        Cocktail cocktail = new Cocktail("cocktail", fortalecedora, fortalecedoraPlus);
        Cocktail cocktailDos = new Cocktail("cocktail", kriptonita, reductorPlomo);   
        juego.addPocima(fortalecedora);
        juego.addPocima(fortalecedoraDos);
        juego.addPocima(fortalecedoraPlus);
        juego.addPocima(fortalecedoraPlusDos);
        juego.addPocima(kriptonita);
        juego.addPocima(kriptonitaDos);
        juego.addPocima(reductorPlomo);
        juego.addPocima(reductorPlomoDos);
        juego.addPocima(valeCuatro);
        juego.addPocima(valeCuatroDos);
        juego.addPocima(numeroMagico);
        juego.addPocima(numeroMagicoDos);
        juego.addPocima(PSF);
        juego.addPocima(PSFdos);
        juego.addPocima(PSP);
        juego.addPocima(PSPdos);
        juego.addPocima(cocktail);
        juego.addPocima(cocktailDos);
        gian.setEstrategia(timbero);
        octi.setEstrategia(ambicioso);
        juego.repartirCartas();
        juego.comparar();
    }

}