package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

public class Indiani extends HnedaKarta{
    @Override
    public void akcia(Hrac hrac) {

        System.out.println("Hrate kartou Indiane proti vsetkym");
        for(Hrac nepriatel: hrac.getNepriatelia()){
            boolean stav = vyhodit_bang(nepriatel);
            if(stav){
                String meno = nepriatel.getMeno();
                String zivoty = String.valueOf(nepriatel.getZivoty());
                String karty_v_ruke = String.valueOf(nepriatel.getKarty_v_ruke().size());
                if(nepriatel.getZivoty()>0) {
                    System.out.println(" " + meno + "[" + zivoty + ", " + karty_v_ruke + "]" + " vyhodil kartu Bang");
                }
            }
            else{
                String meno = nepriatel.getMeno();
                String zivoty = String.valueOf(nepriatel.getZivoty());
                String karty_v_ruke = String.valueOf(nepriatel.getKarty_v_ruke().size());
                if(nepriatel.getZivoty()>0) {
                    System.out.println(" " + meno + "[" + zivoty + ", " + karty_v_ruke + "]" + " stratil jeden zivot");
                }
            }
        }
    }

    @Override
    public String nazov_karty() {
        return "Indiane";
    }

    private boolean vyhodit_bang(Hrac nepriatel){
        for(Karta karta: nepriatel.getKarty_v_ruke()){
            if(karta instanceof Bang && nepriatel.getZivoty()>0){
                nepriatel.getKarty_v_ruke().remove(karta);
                return true;
            }
        }
        if(nepriatel.getZivoty()>0) {
            nepriatel.setZivoty(nepriatel.getZivoty() - 1);
        }
        return false;
    }
}
