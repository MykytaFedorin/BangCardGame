package sk.stuba.fei.uim.oop.cards;


import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.ArrayList;

public class Dostavnik extends HnedaKarta{

    public void akcia(Hrac hrac){
        System.out.println("    hram kartou Dostavnik");
        hrac.potiahnut_karty();
    }

    @Override
    public String nazov_karty() {
        return "Dostavnik";
    }

    ;
}
