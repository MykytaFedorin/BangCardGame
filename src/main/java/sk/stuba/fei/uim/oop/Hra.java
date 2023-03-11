package sk.stuba.fei.uim.oop;
import java.util.ArrayList;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.Collections;

public class Hra {
    public void hrat(){
        String label = "Provide a number of players: ";
        int pocet_hracov = ZKlavesnice.readInt(label);
        ArrayList<Hrac> hraci= new ArrayList<Hrac>();
        for(int i=0;i<pocet_hracov;i++){
            hraci.add(new Hrac());
        }
        ArrayList<Karta> karty = new ArrayList<Karta>();
        karty_init(karty);
        Collections.shuffle(karty);
        for(Karta karta: karty){
            System.out.println(karta.getClass());
        }
    }
    private void share_cards(Hrac[] players, Karta[] cards){

    }
    private void karty_init(ArrayList<Karta> karty){
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
    }
}
