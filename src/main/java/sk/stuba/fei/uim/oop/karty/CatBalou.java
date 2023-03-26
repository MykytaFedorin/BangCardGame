package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class CatBalou extends HnedaKarta{
    @Override
    public void akcia(Hrac nepriatel) {
        int vyber, index_karty;
        System.out.println("Hram kartou Cat Balou proti "+nepriatel.getMeno());
        String vyhlaska = "Chces odhodit kartu z ruky(0) alebo zo stola(1)? Zadaj cislo:";
        while(true){
            vyber = ZKlavesnice.readInt(vyhlaska);
            if(vyber==0){
                vyhodit_karty_z("ruky", nepriatel);
                break;
            }
            else if(vyber==1){
                vyhodit_karty_z("stola", nepriatel);
                break;
            }
            else{
                System.out.println("Zadali ste nespravne cislo. Skuste este raz:");
            }
        }
    }
    private void vyhodit_karty_z(String param, Hrac nepriatel){
        ArrayList<Karta> karty_v_ruke = nepriatel.getKarty_v_ruke();
        ArrayList<Karta> karty_pred = nepriatel.getKarty_pred_hracom();
        ArrayList<Karta> odh_balicek = nepriatel.getOdhadzovaci_balicek();
        Random randomajzer = nepriatel.getRandomajzer();
        int pocet_kart_hraca = karty_pred.size()+karty_v_ruke.size();
        if(pocet_kart_hraca == 0){
            System.out.println("Hrac nema karty ani na stole ani v ruke");
        }
        boolean karta_je_vyhodena = false;
        while(!karta_je_vyhodena && pocet_kart_hraca>0){
            if(param.equals("ruky")){
                if(karty_v_ruke.size()>0){
                    System.out.println("Hrac "+nepriatel.getMeno()+" vyhodil kartu z ruky");
                    vyhodit_kartu(karty_v_ruke, odh_balicek, randomajzer);
                    karta_je_vyhodena = true;
                }
                else if(karty_pred.size()>0){
                    System.out.println("Hrac "+nepriatel.getMeno()+" nema karty v ruke");
                    param = "stola";
                }
            }
            else if(param.equals("stola")){
                if(karty_pred.size()>0) {
                    System.out.println("Hrac " + nepriatel.getMeno() + " vyhodil kartu zo stola");
                    vyhodit_kartu(karty_pred, odh_balicek, randomajzer);
                    karta_je_vyhodena=true;
                }
                else if(karty_v_ruke.size()>0){
                    System.out.println("Hrac "+nepriatel.getMeno()+" nema karty na stole");
                    param = "ruky";
                }
            }
        }
    }
    private void vyhodit_kartu(ArrayList<Karta> balicek, ArrayList<Karta> odh_baliicek, Random randomajzer){
        boolean ma_karty; int nahodny_index;
        ma_karty = kontrola_poctu_kart(balicek.size());
        if(ma_karty){
            nahodny_index = randomajzer.nextInt(balicek.size());
            Karta karta = balicek.get(nahodny_index);
            odh_baliicek.add(karta);
            balicek.remove(karta);
        }
    }
    @Override
    public String nazov_karty() {
        return "CatBalou";
    }

    private boolean kontrola_poctu_kart(int pocet){
        if(pocet<=0){
            System.out.println("Tomuto hracovi nemozete momentalne odhodit kartu");
            return false;
        }
        return true;
    }
}
