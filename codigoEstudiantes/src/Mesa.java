import java.util.*;

public class Mesa {
    //parte privada

    public Mesa() {

    }

    public boolean puedeColocar(Carta c) {

        return true;
    }


    public void colocar(Carta c) throws IllegalArgumentException {

    }

    public boolean paloAbierto(Palo p) {
        return true;
    }

    public Carta getExtremoIzq(Palo p) {
        return null;
    }

    public Carta getExtremoDer(Palo p) {
        return null;
    }


    public List<Carta> getFila(Palo p) {
        return null;
    }

    public int numCartas() {

        return 0;
    }

    /** Una línea por palo, por ejemplo "copas:   [4, 5, 6]". */
    @Override
    public String toString() {
        return null;
    }
}
