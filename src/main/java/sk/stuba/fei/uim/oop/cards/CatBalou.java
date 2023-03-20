package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.ArrayList;
import java.util.Random;

public class CatBalou extends HnedaKarta{
    @Override
    public void akcia(Hrac nepriatel) {
        System.out.println("    Hram kartou Cat Balou proti "+nepriatel.getMeno());
        Random randomajzer = nepriatel.getRandomajzer();
        int vyber = randomajzer.nextInt(2);
        int index_karty;
        System.out.println("    Chces odhodit kartu z ruky alebo zo stola?");
        if(vyber == 1){
            System.out.println("    - Zo stola");
            for(Karta karta: nepriatel.getKarty_pred_hracom()){
                boolean flag = kontrola_poctu_kart(nepriatel.getKarty_pred_hracom().size());
                if(flag){
                    nepriatel.getKarty_pred_hracom().remove(karta);
                    break;
                }
            }
        }
        else{
            System.out.println("    - Z ruky");
            boolean flag = kontrola_poctu_kart(nepriatel.getKarty_v_ruke().size());
            if(flag){
                index_karty = randomajzer.nextInt(nepriatel.getKarty_v_ruke().size());
                nepriatel.getKarty_v_ruke().remove(index_karty);
            }
        }

    }

    @Override
    public String nazov_karty() {
        return "CatBalou";
    }

    private boolean kontrola_poctu_kart(int pocet){
        if(pocet<=0){
            System.out.println("    tomuto hracovi nemozete momentalne odhodit kartu");
            return false;
        }
        return true;
    }
}
