package sk.stuba.fei.uim.oop.hrac;

import java.util.Comparator;

public class PorovnavacHracov implements Comparator<Hrac> {
    @Override
    public int compare(Hrac o1, Hrac o2) {
        return -Integer.compare(o1.getZivoty(), o2.getZivoty());
    }
}
