package sk.stuba.fei.uim.oop.karty;

import sk.stuba.fei.uim.oop.hrac.Hrac;

import java.util.Random;

public class Dynamit extends ModraKarta{
    @Override
    public void akcia(Hrac hrac){
        Random randomajzer = hrac.getRandomajzer();
        int sanca = randomajzer.nextInt(8);
        System.out.print("Kontrolujem efekt Dyniamitu");
        if(sanca==1){
            if((hrac.getZivoty()-3)>=0){
                hrac.setZivoty(hrac.getZivoty()-3);
            }
            else if((hrac.getZivoty()-3)<0){
                hrac.setZivoty(0);
            }
            System.out.println("-vybuchol mi dynamit");
        }
        else{
            System.out.println("-posuvam dynamit ku predoslemu hracu");
            hrac.getPredosly_hrac().getKarty_pred_hracom().add(this);
            hrac.getKarty_pred_hracom().remove(this);
        }
    }

    @Override
    public String nazov_karty() {
        return "Dynamit";
    }

}
