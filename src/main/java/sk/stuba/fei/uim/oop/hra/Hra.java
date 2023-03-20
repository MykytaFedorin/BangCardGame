package sk.stuba.fei.uim.oop.hra;
import java.util.ArrayList;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.Collections;

public class Hra {
    private int mrtvi_hraci;
    public Hra(){
        this.mrtvi_hraci = 0;
    }
    public void hrat(){
        ArrayList<Karta> karty = karty_init();
        ArrayList<Hrac> hraci = hraci_init(karty);
        nepriatelia_init(hraci);
        System.out.println("Miesam karty...");
        Collections.shuffle(karty);
        rozdat_karty(hraci, karty);
        hlavny_cyklus(hraci, karty);
    }
    private void zapamatat_predoslych(ArrayList<Hrac> hraci){
        for(int index=0;index<hraci.size();index++){
            Hrac hrac = hraci.get(index);
            int index_predosleho = (index+(hraci.size()-1))%hraci.size();
            Hrac predosly_hrac = hraci.get(index_predosleho);
            hrac.setPredosly_hrac(predosly_hrac);
        }
    }
    private void hlavny_cyklus(ArrayList<Hrac> hraci, ArrayList<Karta> balicek_kart){
        boolean game_over; boolean prve_kolo = true;
        do{
            for(int i=0;i<hraci.size(); i++) {
                Hrac hrac = hraci.get(i);
                if (prve_kolo) {
                    zapamatat_predoslych(hraci);
                    prve_kolo = false;
                }
                if (hrac.getZivoty()>0) {
                    hrac.print_info();
                    hrac.tahanie();
                    if (!hrac.isJe_vo_vazani()) {
                        hrac.zahranie();
                        hrac.odhadzovanie();
                        hrac.setJe_vo_vazani(false);
                    }
                }
                else{
                    this.mrtvi_hraci+=1;
                }
            }
            game_over = game_over_check(hraci);
        }while(!game_over);
        for(Hrac hrac: hraci){
            if(hrac.getZivoty()>0){
                System.out.println("Vyhral "+hrac.getMeno());
            }
        }
    }
    private boolean game_over_check(ArrayList<Hrac> hraci) {
        return (hraci.size() - this.mrtvi_hraci) < 2;
    }
    private void rozdat_karty(ArrayList<Hrac> hraci, ArrayList<Karta> karty){
        System.out.println("Rozdavam karty...\n");
        for(int i=0;i<hraci.size()*4;i++){
            Karta karta_z_balika = karty.get(i);
            Hrac hrac_na_rade = hraci.get(i%hraci.size());
            ArrayList<Karta> karty_hraca = hrac_na_rade.getKarty_v_ruke();
            karty_hraca.add(karta_z_balika);
        }
    }
    private ArrayList<Hrac> hraci_init(ArrayList<Karta> balicek){
        String[] mena = {"Joe", "Daniel", "Walter", "Jessie"};
        System.out.println("Inicializuju sa hraci...");
        String label = "Zadajte pocet hracov: ";
        int pocet_hracov = 0;
        while(true){
            pocet_hracov = ZKlavesnice.readInt(label);
            if(pocet_hracov>=2 && pocet_hracov<=4){
                break;
            }
            else{
                System.out.println("Zadany pocet hracov nie je platny. Zadajte cislo od 2 po 4: ");
            }
        };
        ArrayList<Hrac> hraci= new ArrayList<Hrac>();
        for(int i=0;i<pocet_hracov;i++){
            hraci.add(new Hrac(balicek, mena[i]));
        }
        return hraci;
    }
    private void nepriatelia_init(ArrayList<Hrac> hraci){
        System.out.println("Nastavuju sa nepriatelia ...");
        ArrayList<Hrac> nepriatelia = new ArrayList<Hrac>();
        ArrayList<Hrac> hraci_kopia = new ArrayList<Hrac>(hraci);
        for(Hrac hrac: hraci){
            for (Hrac hrac_kopia:hraci_kopia){
                if(!hrac.equals(hrac_kopia)){
                    nepriatelia.add(hrac_kopia);
                }
            }
            hrac.setNepriatelia(new ArrayList<Hrac>(nepriatelia));
            nepriatelia.clear();
        }
    }
    private ArrayList<Karta> karty_init(){
        System.out.println("Inicializuje sa balicek kariet...");
        ArrayList<Karta> karty = new ArrayList<Karta>();
        for(int i=0; i<30;i++){
            karty.add(new Bang());
        }
        for(int i=30; i<45;i++){
            karty.add(new Vedla());
        }
        for(int i=45; i<53;i++){
            karty.add(new Pivo());
        }
        for(int i=53; i<59;i++){
            karty.add(new CatBalou());
        }
        for(int i=59; i<63;i++){
            karty.add(new Dostavnik());
        }
        for(int i=63; i<65;i++){
            karty.add(new Indiani());
        }
        for(int i=65; i<67;i++){
            karty.add(new Barrel());
        }
        for(int i=67; i<68;i++){
            karty.add(new Dynamit());
        }
        for(int i=68; i<71;i++){
            karty.add(new Vazenie());
        }
        return karty;
    }
}
