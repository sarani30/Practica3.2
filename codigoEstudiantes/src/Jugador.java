import java.util.*;

public class Jugador {
    //parte privada completar
    private final String nombre;
    private final Set<Carta> mano;

    public Jugador(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("Nombre no válido");
        } else {
            this.nombre = nombre;
            this.mano = new TreeSet<>();
        }

    }


    public void recibir(Carta c) {

        if (c == null) {
            throw new IllegalArgumentException("La carta es null");
        } else {
            //añadir al set
            this.mano.add(c);
        }
    }


    public boolean eliminar(Carta c) {

        if (c == null) {
            throw new IllegalArgumentException("La carta es null");
        } else if (!this.mano.contains(c)) {
            return false; //comprobar si la carta está en la mano del jugador
        } else {
            //la carta está, la borramos
            this.mano.remove(c);
            return true;
        }

    }


    public int numCartas() {
        return this.mano.size();
    }

    public List<Carta> getMano() {

        List<Carta> listaMano = new ArrayList<>();
        listaMano.addAll(mano);
        return listaMano;
    }

    public boolean puedeJugar (Mesa mesa) {

        for (Carta c : this.mano) {
            if (mesa.puedeColocar(c)) {
                return true; //mientras haya una que puede colocar, puede seguir jugando
            }
        }
        return false;
    }

    public List<Carta> cartasJugables (Mesa mesa) {

        List<Carta> puedeJugar = new ArrayList<>();

        for (Carta c : this.mano) {
            if (mesa.puedeColocar(c)) {
                puedeJugar.add(c);
            }
        }
        return puedeJugar;
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
