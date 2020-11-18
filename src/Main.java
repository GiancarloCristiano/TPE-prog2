public class Main {
    public static void main(String[] args) {
        String mazoPath = "./src/superheroes.json";
        Mazo mazo = new Mazo();
        mazo.cargarMazo(mazoPath);
        Jugador octi = new Jugador("Octi");
        Jugador gian = new Jugador("Gian");
        Juego juego = new Juego(100, octi, gian, mazo, true);
        Timbero timbero = new Timbero(); 
        Ambicioso ambicioso = new Ambicioso(); 
        //Obstinado obstinado = new Obstinado();
        PocimaIncrementadora fortalecedora = new PocimaIncrementadora("Fortalecedora", 20);
        PocimaIncrementadora fortalecedoraDos = new PocimaIncrementadora("Fortalecedora", 30);
        PocimaIncrementadora fortalecedoraPlus = new PocimaIncrementadora("Fortalecedora Plus", 50);
        PocimaIncrementadora fortalecedoraPlusDos = new PocimaIncrementadora("Fortalecedora Plus", 60);
        PocimaDecrementadora kriptonita = new PocimaDecrementadora("Kriptonita", 25);
        PocimaDecrementadora kriptonitaDos = new PocimaDecrementadora("Kriptonita", 35);
        PocimaDecrementadora reductorPlomo = new PocimaDecrementadora("Reductor Plomo", 55);
        PocimaDecrementadora reductorPlomoDos = new PocimaDecrementadora("Reductor Plomo", 65);
        PocimaValorFijo valeCuatro = new PocimaValorFijo("Vale Cuatro", 4);
        PocimaValorFijo valeCuatroDos = new PocimaValorFijo("Vale Cuatro", 6);
        PocimaValorFijo numeroMagico = new PocimaValorFijo("Número Mágico", 23);
        PocimaValorFijo numeroMagicoDos = new PocimaValorFijo("Número Mágico", 24);
        PocimaSelectiva PSF = new PocimaSelectiva("Selectiva Fuerza", "fuerza", 35);
        PocimaSelectiva PSFdos = new PocimaSelectiva("Selectiva Fuerza", "fuerza", 36);
        PocimaSelectiva PSP = new PocimaSelectiva("Selectiva Peso", "peso", 43);
        PocimaSelectiva PSPdos = new PocimaSelectiva("Selectiva Peso", "peso", 44);
        Cocktail cocktail = new Cocktail("Cocktail", fortalecedora, fortalecedoraPlus);
        Cocktail cocktailDos = new Cocktail("Cocktail", kriptonita, reductorPlomo);
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
        octi.setEstrategia(ambicioso);
        gian.setEstrategia(timbero);
        juego.repartirCartas();
        juego.jugar();
    }

}