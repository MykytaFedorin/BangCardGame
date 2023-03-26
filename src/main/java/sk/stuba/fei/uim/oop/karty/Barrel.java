package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Barrel extends ModraKarta{

    public void akcia(Hrac hrac) {
        System.out.println("    vylozim pred seba Barrel");
    }

    @Override
    public String nazov_karty() {
        return "Barrel";
    }
}
