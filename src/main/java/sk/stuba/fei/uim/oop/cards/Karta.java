package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.ArrayList;

public abstract class Karta {
    public abstract void akcia(Hrac hrac);
    public abstract String nazov_karty();
}
