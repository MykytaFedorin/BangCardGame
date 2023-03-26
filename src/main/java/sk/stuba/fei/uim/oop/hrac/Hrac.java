package sk.stuba.fei.uim.oop.hrac;

import sk.stuba.fei.uim.oop.karty.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import sk.stuba.fei.uim.oop.vynimky.VynimkaVynechavaniaTahu;


import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Random;

public class Hrac implements Comparable<Hrac>{
    private ArrayList<Karta> karty_v_ruke;
    private ArrayList<Karta> karty_pred_hracom;
    private ArrayList<Hrac> nepriatelia;
    private Random randomajzer;
    private int zivoty;
    private Hrac predosly_hrac;
    private ArrayList<Karta> balicek;
    private ArrayList<Karta> odhadzovaci_balicek;
    private boolean je_vo_vazani;
    private String meno;
    public Hrac(){
        this.randomajzer = new Random();
        this.karty_v_ruke = new ArrayList<>();
        this.zivoty = 4;
        this.karty_pred_hracom = new ArrayList<>();
        this.nepriatelia = new ArrayList<>();
        this.je_vo_vazani = false;
    }
    public Hrac(ArrayList<Karta> balicek, String meno){
        this.randomajzer = new Random();
        this.karty_v_ruke = new ArrayList<>();
        this.zivoty = 4;
        this.karty_pred_hracom = new ArrayList<>();
        this.nepriatelia = new ArrayList<>();
        this.balicek = balicek;
        this.odhadzovaci_balicek = new ArrayList<>();
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
        this.predosly_hrac = hrac.predosly_hrac;
        this.meno = hrac.meno;
        this.balicek = hrac.balicek;
        this.odhadzovaci_balicek = new ArrayList<>();
    }
    public String getMeno(){
        return this.meno;
    }
    public ArrayList<Karta> getOdhadzovaci_balicek(){return this.odhadzovaci_balicek;}
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

    public int getZivoty() {
        return zivoty;
    }

    public ArrayList<Karta> getKarty_pred_hracom() {
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

    public void setZivoty(int zivoty) {
        this.zivoty = zivoty;
    }
    private void vypis_obsah_balika(ArrayList<Karta> balik_kart){
        if(balik_kart.size()==0){
            System.out.print("  nie su karty");
        }
        int i = 1;
        System.out.print("  ");
        for(Karta karta: balik_kart){
            System.out.print(" "+i+"."+karta.nazov_karty());
            i+=1;
        }
        System.out.println();
    }
    public void print_info(){
        System.out.println("Meno: "+this.meno);
        System.out.println("Zivoty: "+this.zivoty);
    }
    public void tahanie(){
        System.out.println("1. Tahanie");
        kontrola_efektov();
        if(!this.isJe_vo_vazani()){
            potiahnut_karty();
        }
    }
    public void print_info_small(){
        System.out.println("Vase karty v ruke: ");
        vypis_obsah_balika(this.karty_v_ruke);
        System.out.println("Karty pred vami: ");
        vypis_obsah_balika(this.karty_pred_hracom);
    }
    public void zahranie(){
        System.out.println("2. Zahranie");
        while(true){
            if(this.karty_v_ruke.size()==0){
                System.out.println("Hrac na tahu nema ziadne dalsie karty v ruke");
                break;
            }
            else {
                Karta karta = null;
                print_info();
                print_info_small();
                try {
                    karta = vyber_karty();
                } catch (VynimkaVynechavaniaTahu e) {
                    break;
                }
                zahrat_kartu(karta);
                this.odhadzovaci_balicek.add(karta);
                this.karty_v_ruke.remove(karta);
            }
        }
    }
    public void odhadzovanie(){
        System.out.println("3. Odhadzovanie");
        int odhodit = this.getKarty_v_ruke().size()-this.zivoty;
        if(odhodit>0){
            System.out.println("Odhodil som "+odhodit+" karty\n");
            for(int i=0;i<odhodit;i++){
                Karta karta = this.karty_v_ruke.get(0);
                this.odhadzovaci_balicek.add(karta);
                this.karty_v_ruke.remove(karta);
            }
        }
        else {
            System.out.println("Hrac nepotrebuje odhodit ziadne karty\n");
        }
    }
    private void zahraj_modru(Karta karta){
        if (karta instanceof Barrel || karta instanceof Dynamit) {
            System.out.println("Hram Kartou " + karta.nazov_karty());
            this.karty_pred_hracom.add(karta);
        } else if (karta instanceof Vazenie) {
            vypis_nepriatelov();
            while(true) {
                Hrac nepriatel = vybrat_nepriatela();
                if (!nepriatel.isJe_vo_vazani()) {
                    System.out.println("Uvaznim kartou Vazanie hraca " + nepriatel.getMeno());
                    nepriatel.setKarty_pred_hracom(karta);
                    break;
                } else {
                    System.out.println("Hrac uz je vo vazani. Vyberte si ineho hraca.");
                }
            }
        }
    }
    private void vypis_nepriatelov(){
        System.out.println("Nepriatelia: ");
        int i = 0;
        for(Hrac hrac: this.nepriatelia){
            if(hrac.getZivoty()>0){
                i+=1;
                System.out.println("Hrac "+hrac.meno+" "+(i));
            }
        }
    }
    private void zahraj_hnedu(Karta karta){
        if (karta instanceof Bang || karta instanceof CatBalou) {
            vypis_nepriatelov();
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
        String vyhlaska = "Zadajte cislo nepriatela";
        while (true){
            int cislo_nepriatela = ZKlavesnice.readInt(vyhlaska);
            if(cislo_nepriatela>=1 && cislo_nepriatela<=this.nepriatelia.size()){
                if(this.nepriatelia.get(cislo_nepriatela-1).getZivoty()>0){
                    return this.nepriatelia.get(cislo_nepriatela-1);
                }
                else{
                    System.out.println("Tento hrac je mrtvy, nemozete nanho zahrat.");
                }
            }
            else{
                System.out.println("Zadali ste nespravne cislo. Skuste este raz");
            }
        }
    }
    private Karta vyber_karty() throws VynimkaVynechavaniaTahu {
        while(true){
            String vyhlaska = "Zadajte cislo karty ktoru chcete zahrat alebo 0 ked nechcete zahrat ziadnu";
            int cislo_karty = ZKlavesnice.readInt(vyhlaska);
            if (cislo_karty == 0) {
                throw new VynimkaVynechavaniaTahu("vynechavam tah");
            }
            else if(cislo_karty>=1 && cislo_karty<=this.karty_v_ruke.size()){
                Karta karta = this.karty_v_ruke.get(cislo_karty-1);
                if(karta instanceof Dynamit || karta instanceof Barrel){
                    if(!nachadza_sa_pred(karta)){
                        return karta;
                    };
                }
                else if (!(karta instanceof Vedla)) {
                    return karta;
                }
                System.out.println("Nemozete zahrat tuto kartu! Vyberte si inu.");
            }
            else{
                System.out.println("Zadali ste neplatne cislo");
            }
        }
    }
    private boolean nachadza_sa_pred(Karta karta){
        for(Karta k: this.karty_pred_hracom){
            if(k.getClass()==karta.getClass()){
                return true;
            }
        }
        return false;
    }
    public void potiahnut_karty(){
        if(this.balicek.size()>=2) {
            System.out.println("Taham 2 karty");
            Karta karta0 = this.balicek.get(0);
            this.balicek.remove(0);
            Karta karta1 = this.balicek.get(0);
            this.balicek.remove(0);
            this.karty_v_ruke.add(karta0);
            this.karty_v_ruke.add(karta1);
        }
        else if(this.balicek.size()==1){
            System.out.println("Taham 1 kartu");
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
        System.out.print("Kontrolujem karty Dynamit a Vazanie");
        Karta dynamit = null, vazanie = null;
        for(Karta karta: this.karty_pred_hracom){
            if(karta instanceof Dynamit){
                dynamit = karta;
            }
            else if(karta instanceof Vazenie){
                vazanie = karta;
            }
        }
        zoradit_efekty(dynamit, vazanie);
        if(dynamit != null){
            this.odhadzovaci_balicek.add(dynamit);
            this.karty_pred_hracom.remove(dynamit);
        }
        if(vazanie != null){
            this.odhadzovaci_balicek.add(vazanie);
            this.karty_pred_hracom.remove(vazanie);
        }
    }

    @Override
    public int compareTo(Hrac o) {
        return Integer.compare(this.zivoty, o.getZivoty());
    }
}
