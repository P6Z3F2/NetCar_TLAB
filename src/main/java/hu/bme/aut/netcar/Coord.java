package hu.bme.aut.netcar;



import java.io.Serializable;
import java.util.Locale;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;


public class Coord implements Serializable {

    private static final long serialVersionUID = 3583151228933783558L;

    private  double x;
    private  double y;


    @PersistenceConstructor
    public Coord(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coord(){}





    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }




    @Override
    public String toString() {
        return String.format(Locale.ENGLISH, "Coord [x=%f, y=%f]", x, y);
    }
}

