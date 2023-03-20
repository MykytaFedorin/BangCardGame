package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.ArrayList;

public class Barrel extends ModraKarta{

    public void akcia(Hrac hrac) {
        System.out.println("    vylozim pred seba Barrel");
    }

    @Override
    public String nazov_karty() {
        return "Barrel";
    }
}
