package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.cards.Dynamit;
import sk.stuba.fei.uim.oop.cards.Karta;
import sk.stuba.fei.uim.oop.cards.Vazenie;

import javax.management.ValueExp;
import java.util.ArrayList;
import java.util.Random;

public class Hrac {
    private ArrayList<Karta> karty_v_ruke;
    private ArrayList<Karta> karty_pred_hracom;
    private ArrayList<Hrac> nepriatelia;
    private Random randomajzer;
    private int zivoty;
    public Hrac(){
        this.randomajzer = new Random();
        this.karty_v_ruke = new ArrayList<Karta>();
        this.zivoty = 4;
        this.karty_pred_hracom = new ArrayList<Karta>();
        this.nepriatelia = new ArrayList<Hrac>();
    }
    public Hrac(Hrac hrac){
        this.randomajzer = hrac.randomajzer;
        this.karty_v_ruke = hrac.karty_v_ruke;
        this.karty_pred_hracom = hrac.karty_pred_hracom;
        this.nepriatelia = hrac.nepriatelia;
        this.zivoty = hrac.zivoty;
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

    public ArrayList<Karta> getKarty_pred_hracom() {
        return karty_pred_hracom;
    }

    public void setKarty_pred_hracom(ArrayList<Karta> karty_pred_hracom) {
        this.karty_pred_hracom = karty_pred_hracom;
    }

    public ArrayList<Hrac> getNepriatelia() {
        return nepriatelia;
    }

    public void setNepriatelia(ArrayList<Hrac> nepriatelia) {
        this.nepriatelia = nepriatelia;
    }

    public Random getRandomajzer() {
        return randomajzer;
    }

    public void setRandomajzer(Random randomajzer) {
        this.randomajzer = randomajzer;
    }

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }
    public void tahanie(ArrayList<Karta> balicek){
        potiahnut_karty(balicek);
        kontrola_efektov();
    }
    public void zahranie(){
        int nahodny_pocet = this.randomajzer.nextInt(this.karty_v_ruke.size());
        for(int i = 0; i < nahodny_pocet;i++){
            Karta karta = vyber_karty();
            this.karty_v_ruke.remove(karta);

        }

    }
    private Karta vyber_karty(){
        int nahodne_cislo = this.randomajzer.nextInt(this.karty_v_ruke.size());
        return this.karty_v_ruke.get(nahodne_cislo);
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
