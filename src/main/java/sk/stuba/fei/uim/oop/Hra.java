package sk.stuba.fei.uim.oop;
import java.util.ArrayList;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.Collections;

public class Hra {
    public void hrat(){
        ArrayList<Hrac> hraci = hraci_init();
        ArrayList<Karta> karty = karty_init();
        Collections.shuffle(karty);
    }
    private void share_cards(Hrac[] players, Karta[] cards){

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
