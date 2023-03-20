package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.ArrayList;
import java.util.Random;

public class Vazenie extends ModraKarta{
    @Override
    public void akcia(Hrac nepriatel){
        System.out.print("    uvaznim kartou Vazanie hraca "+nepriatel.getMeno());
        Random randomajzer = nepriatel.getRandomajzer();
        int sanca = randomajzer.nextInt(8);
        if(sanca == 1){
            nepriatel.setJe_vo_vazani(true);
        }
        else{
            System.out.println("-> podarilo sa mu ujst z vazania");
        }
    }

    @Override
    public String nazov_karty() {
        return "Vazanie";
    }
}
