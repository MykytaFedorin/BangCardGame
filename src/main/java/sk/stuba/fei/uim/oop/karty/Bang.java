package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.sql.SQLOutput;
import java.util.Random;

public class Bang extends HnedaKarta {

    @Override
    public void akcia(Hrac nepriatel){
        String zivoty = String.valueOf(nepriatel.getZivoty());
        String karty_v_ruke = String.valueOf(nepriatel.getKarty_v_ruke().size());
        System.out.println("Hram kartou Bang proti "+nepriatel.getMeno()+"["+zivoty+", "+karty_v_ruke+"]");
        for(Karta karta: nepriatel.getKarty_pred_hracom()){
            if(karta instanceof Barrel){
                System.out.println("Hrac "+nepriatel.getMeno()+" ma pred sebou Barrel");
                System.out.print("Kontrolujem efekt barrelu - ");
                Random randomajzer = nepriatel.getRandomajzer();
                int sanca = randomajzer.nextInt(4);
                if(sanca==1) {
                    System.out.println(" barrel zachranil hraca "+nepriatel.getMeno());
                    return;
                }
                else{
                    break;
                }
            }
        }
        for(Karta karta: nepriatel.getKarty_v_ruke()){
            if(karta instanceof Vedla){
                zivoty = String.valueOf(nepriatel.getZivoty());
                karty_v_ruke = String.valueOf(nepriatel.getKarty_v_ruke().size());
                nepriatel.getOdhadzovaci_balicek().add(karta);
                nepriatel.getKarty_v_ruke().remove(karta);
                System.out.println("Hrac "+nepriatel.getMeno()+"["+zivoty+", "+karty_v_ruke+"]"+" zahral kartu Vedla");
                return;
            }
        }
        nepriatel.setZivoty(nepriatel.getZivoty()-1);
        zivoty = String.valueOf(nepriatel.getZivoty());
        karty_v_ruke = String.valueOf(nepriatel.getKarty_v_ruke().size());
        System.out.println("Hrac "+nepriatel.getMeno()+"["+zivoty+", "+karty_v_ruke+"]"+" stratil jeden zivot");
    }

    @Override
    public String nazov_karty() {
        return "Bang";
    }

}
