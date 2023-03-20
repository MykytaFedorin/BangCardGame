package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.cards.*;


import java.nio.channels.ScatteringByteChannel;
import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;

public class Hrac {
    private ArrayList<Karta> karty_v_ruke;
    private HashSet<Karta> karty_pred_hracom;
    private ArrayList<Hrac> nepriatelia;
    private Random randomajzer;
    private int zivoty;
    private Hrac predosly_hrac;
    private ArrayList<Karta> balicek;
    private boolean je_vo_vazani;
    private String meno;
    public Hrac(){
        this.randomajzer = new Random();
        this.karty_v_ruke = new ArrayList<Karta>();
        this.zivoty = 4;
        this.karty_pred_hracom = new HashSet<Karta>();
        this.nepriatelia = new ArrayList<Hrac>();
        this.je_vo_vazani = false;
    }
    public Hrac(ArrayList<Karta> balicek, String meno){
        this.randomajzer = new Random();
        this.karty_v_ruke = new ArrayList<Karta>();
        this.zivoty = 4;
        this.karty_pred_hracom = new HashSet<Karta>();
        this.nepriatelia = new ArrayList<Hrac>();
        this.balicek = balicek;
        this.je_vo_vazani = false;
        this.meno = meno;
    }
    public Hrac(Hrac hrac){
        this.randomajzer = hrac.randomajzer;
        this.karty_v_ruke = hrac.karty_v_ruke;
        this.karty_pred_hracom = hrac.karty_pred_hracom;
        this.nepriatelia = hrac.nepriatelia;
        this.zivoty = hrac.zivoty;
        this.je_vo_vazani=false;
    }
    private void vypis_obsah_balika(AbstractCollection<Karta> balik_kart){
        if(balik_kart.size()==0){
            System.out.print("nie su karty");
        }
        for(Karta karta: balik_kart){
            System.out.print(" "+karta.nazov_karty());
        }
        System.out.println();
    }
    public void print_info(){
        System.out.println("Meno: "+this.meno);
        System.out.print("Karty v ruke:");
        vypis_obsah_balika(this.karty_v_ruke);
        System.out.print("Karty pred hracom:");
        vypis_obsah_balika(this.karty_pred_hracom);
        System.out.println("Zivoty: "+String.valueOf(this.zivoty));
    }
    public String getMeno(){
        return this.meno;
    }
    public boolean isJe_vo_vazani() {
        return je_vo_vazani;
    }

    public void setJe_vo_vazani(boolean je_vo_vazani) {
        this.je_vo_vazani = je_vo_vazani;
    }

    public void setPredosly_hrac(Hrac predosly_hrac) {
        this.predosly_hrac = predosly_hrac;
    }

    public Hrac getPredosly_hrac() {
        return predosly_hrac;
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

    public HashSet<Karta> getKarty_pred_hracom() {
        return karty_pred_hracom;
    }

    public void setKarty_pred_hracom(Karta karta) {
        this.karty_pred_hracom.add(karta);
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
    public void tahanie(){
        System.out.println("1. Tahanie");
        kontrola_efektov();
        potiahnut_karty();
    }
    public void zahranie(){
        System.out.println("2. Zahranie");
        try {
            int nahodny_pocet = this.randomajzer.nextInt(this.karty_v_ruke.size());
            if(nahodny_pocet==0){
                System.out.println("    nezahram ziadnu kartu");
            }
            for (int i = 0; i < nahodny_pocet; i++) {
                Karta karta = vyber_karty();
                if (!(karta instanceof Vedla)) {
                    zahrat_kartu(karta);
                }
                this.karty_v_ruke.remove(karta);
            }
        }
        catch(IllegalArgumentException e){
            System.out.println("    nemam ziadnu kartu");
        }
    }
    public void odhadzovanie(){
        System.out.println("3. Odhadzovanie");
        int odhodit = this.getKarty_v_ruke().size()-this.zivoty;
        if(odhodit>0 && this.getKarty_v_ruke().size()>=odhodit){
            for(int i=0;i<odhodit;i++){
                this.karty_v_ruke.remove(0);
            }
        }
        else if(this.getKarty_v_ruke().size()<odhodit){
            this.karty_v_ruke.clear();
        }
    }
    private void zahraj_modru(Karta karta){
        if (karta instanceof Barrel || karta instanceof Dynamit) {
            System.out.println("    hram Kartou Dynamit");
            this.karty_pred_hracom.add(karta);
        } else if (karta instanceof Vazenie) {
            Hrac nepriatel = vybrat_nepriatela();
            nepriatel.setKarty_pred_hracom(karta);
        }
    }
    private void zahraj_hnedu(Karta karta){
        if (karta instanceof Bang || karta instanceof CatBalou) {
            Hrac nepriatel = vybrat_nepriatela();
            karta.akcia(nepriatel);
        }
        else {
            karta.akcia(this);
        }
    }
    private void zahrat_kartu(Karta karta) {
        if(karta instanceof ModraKarta){
            zahraj_modru(karta);
        } else if (karta instanceof HnedaKarta){
            zahraj_hnedu(karta);
        }
    }
    private Hrac vybrat_nepriatela(){
        return this.nepriatelia.get(this.randomajzer.nextInt(this.nepriatelia.size()));
    }
    private Karta vyber_karty(){
        int nahodne_cislo = this.randomajzer.nextInt(this.karty_v_ruke.size());
        return this.karty_v_ruke.get(nahodne_cislo);
    }
    public void potiahnut_karty(){
        if(this.balicek.size()>=2) {
            System.out.println("    taham 2 karty");
            Karta karta0 = this.balicek.get(0);
            this.balicek.remove(0);
            Karta karta1 = this.balicek.get(0);
            this.balicek.remove(0);
            this.karty_v_ruke.add(karta0);
            this.karty_v_ruke.add(karta1);
        }
        else if(this.balicek.size()==1){
            System.out.println("    taham 1 kartu");
            Karta karta0 = this.balicek.get(0);
            this.balicek.remove(0);
            this.karty_v_ruke.add(karta0);
        }
    }
    private void zoradit_efekty(Karta dynamit, Karta vazanie){
        if(dynamit!=null && vazanie!=null){
            dynamit.akcia(this);
            vazanie.akcia(this);
        }
        else if(dynamit!=null){
            System.out.println("-mam Dynamit");
            dynamit.akcia(this);
        }
        else if(vazanie!=null){
            System.out.println("-mam Vazanie");
            vazanie.akcia(this);
        }
        else{
            System.out.println("-nemam ani jednu");
        }
    }
    private void kontrola_efektov(){
        System.out.print("    kontrolujem karty Dynamit a Vazanie");
        int index_barrel; Karta dynamit = null, vazanie = null;
        for(Karta karta: this.karty_pred_hracom){
            if(karta instanceof Dynamit){
                dynamit = karta;
            }
            else if(karta instanceof Vazenie){
                vazanie = karta;
            }
        }
        zoradit_efekty(dynamit, vazanie);
        this.karty_pred_hracom.remove(dynamit);
        this.karty_pred_hracom.remove(vazanie);
    }
}
