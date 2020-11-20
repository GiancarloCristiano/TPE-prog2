public class Main {
    public static void main(String[] args) {
        //String mazoPath = "./src/superheroes.json";
        String mazoPath = "./src/autos.json";
        Mazo mazo = new Mazo();
        mazo.cargarMazo(mazoPath);
        Jugador octi = new Jugador("Octi");
        Jugador gian = new Jugador("Gian");
        Juego juego = new Juego(100, octi, gian, mazo, true);
        Timbero timbero = new Timbero(); 
        Ambicioso ambicioso = new Ambicioso(); 
        //Obstinado obstinado = new Obstinado();
        Pocima fortalecedora = new PocimaPorcentual("Fortalecedora", 20);
        Pocima fortalecedoraDos = new PocimaPorcentual("Fortalecedora", 30);
        Pocima fortalecedoraPlus = new PocimaPorcentual("Fortalecedora Plus", 50);
        Pocima fortalecedoraPlusDos = new PocimaPorcentual("Fortalecedora Plus", 60);
        Pocima kriptonita = new PocimaPorcentual("Kriptonita", -25);
        Pocima kriptonitaDos = new PocimaPorcentual("Kriptonita", -30);
        Pocima reductorPlomo = new PocimaPorcentual("Reductor Plomo", -55);
        Pocima reductorPlomoDos = new PocimaPorcentual("Reductor Plomo", -60);
        Pocima valeCuatro = new PocimaValorFijo("Vale Cuatro", 4);
        Pocima valeCuatroDos = new PocimaValorFijo("Vale Cuatro", 4.2);
        Pocima numeroMagico = new PocimaValorFijo("Número Mágico", 23);
        Pocima numeroMagicoDos = new PocimaValorFijo("Número Mágico", 25);
        Pocima selectivaFuerza = new PocimaSelectiva("Selectiva Fuerza", "fuerza", 35);
        Pocima selectivaFuerzaDos = new PocimaSelectiva("Selectiva Fuerza", "fuerza", 40);
        Pocima selectivaPeso = new PocimaSelectiva("Selectiva Peso", "peso", 43);
        Pocima selectivaPesoDos = new PocimaSelectiva("Selectiva Peso", "peso", 45);
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
        juego.addPocima(selectivaFuerza);
        juego.addPocima(selectivaFuerzaDos);
        juego.addPocima(selectivaPeso);
        juego.addPocima(selectivaPesoDos);
        juego.addPocima(cocktail);
        juego.addPocima(cocktailDos);
        octi.setEstrategia(ambicioso);
        gian.setEstrategia(timbero);
        juego.repartirCartas();
        juego.jugar();
    }

}