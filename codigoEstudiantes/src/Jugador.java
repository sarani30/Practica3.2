import java.util.*;

public class Jugador {
    //parte privada completar
    private String nombre;

    public Jugador(String nombre) {

    }


    public void recibir(Carta c) {

    }


    public boolean eliminar(Carta c) {
        return true;
    }


    public int numCartas() {
        return 0;
    }

    public List<Carta> getMano() {
        return null;
    }

    public boolean puedeJugar (Mesa mesa) {

        return false;
    }

    public List<Carta> cartasJugables (Mesa mesa) {

        return null;
    }


    public String getNombre() {
        return nombre;
    }

    /** @return false; JugadorAutomatico lo redefine */
    public boolean esAutomatico() {
        return false;
    }

    /** Dos jugadores son iguales si tienen el mismo nombre. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
                    return false;
        }
        return nombre.equals(((Jugador) o).nombre);
    }

    @Override
    public int hashCode() {
        return nombre.hashCode();
    }

    @Override
    public String toString() {
        return nombre;
    }

}
