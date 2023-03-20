package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.ArrayList;

public class Indiani extends HnedaKarta{
    @Override
    public void akcia(Hrac hrac) {

        System.out.println("    hram kartou Indiane proti vsetkym");
        for(Hrac nepriatel: hrac.getNepriatelia()){
            boolean stav = vyhodit_bang(nepriatel);
            if(stav){
                String meno = nepriatel.getMeno();
                String zivoty = String.valueOf(nepriatel.getZivoty());
                String karty_v_ruke = String.valueOf(nepriatel.getKarty_v_ruke().size());
                System.out.println(" "+meno+"["+zivoty+", "+karty_v_ruke+"]"+" vyhodil kartu Bang");
            }
            else{
                String meno = nepriatel.getMeno();
                String zivoty = String.valueOf(nepriatel.getZivoty());
                String karty_v_ruke = String.valueOf(nepriatel.getKarty_v_ruke().size());
                System.out.print(" "+meno+"["+zivoty+", "+karty_v_ruke+"]"+" stratil jeden zivot");
            }
        }
    }

    @Override
    public String nazov_karty() {
        return "Indiane";
    }

    private boolean vyhodit_bang(Hrac nepriatel){
        for(Karta karta: nepriatel.getKarty_v_ruke()){
            if(karta instanceof Bang){
                nepriatel.getKarty_v_ruke().remove(karta);
                return true;
            }
        }
        nepriatel.setZivoty(nepriatel.getZivoty()-1);
        return false;
    }
}
