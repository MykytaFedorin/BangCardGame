package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Pivo extends HnedaKarta{

    @Override
    public void akcia(Hrac hrac) {
        String vnutro = String.valueOf(hrac.getZivoty())+", "+String.valueOf(hrac.getKarty_v_ruke().size());
        String stav = hrac.getMeno()+"["+vnutro+"]";
        System.out.println("Hram kartou Pivo -> +1 zivot");
        hrac.setZivoty(hrac.getZivoty()+1);
    }

    @Override
    public String nazov_karty() {
        return "Pivo";
    }
}
