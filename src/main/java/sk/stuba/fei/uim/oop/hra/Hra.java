package sk.stuba.fei.uim.oop.hra;
import java.util.ArrayList;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.Collections;

public class Hra {
    public void hrat(){

        ArrayList<Hrac> hraci = hraci_init();
        ArrayList<Karta> karty = karty_init();
        nepriatelia_init(hraci);
        Collections.shuffle(karty);
        rozdat_karty(hraci, karty);
        hlavny_cyklus(hraci, karty);
    }
    private void hlavny_cyklus(ArrayList<Hrac> hraci, ArrayList<Karta> balicek_kart){
        boolean game_over;
        do{
            for(Hrac hrac: hraci){
                hrac.tahanie(balicek_kart);
                hrac.zahranie();
                //odhadzovanie();
            }
            game_over = game_over_check(hraci);
        }while(!game_over);
    }
    private boolean game_over_check(ArrayList<Hrac> hraci) {
        int pocet_zivych_hracov = 0;
        for (Hrac hrac : hraci) {
            if (hrac.getZivoty() > 0) {
                pocet_zivych_hracov += 1;
            }
        }
        return pocet_zivych_hracov == 1;
    }
    private void rozdat_karty(ArrayList<Hrac> hraci, ArrayList<Karta> karty){
        for(int i=0;i<hraci.size()*4;i++){
            Karta karta_z_balika = karty.get(i);
            Hrac hrac_na_rade = hraci.get(i%hraci.size());
            ArrayList<Karta> karty_hraca = hrac_na_rade.getKarty_v_ruke();
            karty_hraca.add(karta_z_balika);
        }
    }
    private ArrayList<Hrac> hraci_init(){
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
            hraci.add(new Hrac());
        }
        return hraci;
    }
    private void nepriatelia_init(ArrayList<Hrac> hraci){
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
