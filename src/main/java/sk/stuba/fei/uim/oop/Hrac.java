package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Dynamit;
import sk.stuba.fei.uim.oop.cards.Karta;
import sk.stuba.fei.uim.oop.cards.Vazenie;

import javax.management.ValueExp;
import java.util.ArrayList;

public class Hrac {
    private ArrayList<Karta> karty_v_ruke;
    private ArrayList<Karta> karty_pred_hracom;
    private int zivoty;
    public Hrac(){
        this.karty_v_ruke = new ArrayList<Karta>();
        this.zivoty = 4;
    }

    public ArrayList<Karta> getKarty_v_ruke() {
        return karty_v_ruke;
    }

    public void setKarty_v_ruke(ArrayList<Karta> karty_v_ruke) {
        this.karty_v_ruke = karty_v_ruke;
    }

    public int getZivoty() {
        return zivoty;
    }

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }
    public void tahanie(ArrayList<Karta> balicek){
        potiahnut_karty(balicek);
        kontrola_efektov();
    }
    private void potiahnut_karty(ArrayList<Karta> balicek){
        Karta karta0 = balicek.get(0);
        balicek.remove(0);
        Karta karta1 = balicek.get(0);
        balicek.remove(0);
        this.karty_v_ruke.add(karta0);
        this.karty_v_ruke.add(karta1);
    }
    private void kontrola_efektov(){
        ArrayList<Karta> vybrane_karty = new ArrayList<Karta>();
        for(Karta karta: this.karty_pred_hracom){
            if(karta instanceof Dynamit || karta instanceof Vazenie){
                vybrane_karty.add(karta);
            }
        }
        if(vybrane_karty.size()==1){
            vybrane_karty.get(0).akcia();
        } else if (vybrane_karty.size()==2) {
            for(Karta karta: vybrane_karty){
                if(karta instanceof Dynamit){
                    karta.akcia();
                    vybrane_karty.remove(karta);
                }
            }
            vybrane_karty.get(0).akcia();
        }
    }
}
