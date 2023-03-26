package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.Random;

public class Vazenie extends ModraKarta{
    @Override
    public void akcia(Hrac nepriatel){
        System.out.print("Kontrolujem efekt karty Vazanie ");
        Random randomajzer = nepriatel.getRandomajzer();
        int sanca = randomajzer.nextInt(8);
        if(sanca == 1){
            nepriatel.setJe_vo_vazani(true);
            System.out.println("-> nepodarilo sa mne ujst z vazania");
            System.out.println("Vynechavam kolo\n");
        }
        else{
            System.out.println("-> podarilo sa mne ujst z vazania");
        }
    }

    @Override
    public String nazov_karty() {
        return "Vazanie";
    }
}
