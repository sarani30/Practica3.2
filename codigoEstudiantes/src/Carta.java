/**
 * Carta inmutable de la baraja española de 40 cartas.
 * Números válidos: 1 a 7, 10 (sota), 11 (caballo) y 12 (rey).
 */
public final class Carta implements Comparable<Carta> {

    private final ...;
    private final  ...;

    /**
     * @throws IllegalArgumentException si el palo es null o el número no está en {1..7, 10, 11, 12}
     */
    public Carta(Palo palo, int numero) {

    }

    public Palo getPalo() {
        return null;
    }

    public int getNumero() {

        return 0;
    }


    public int getOrden() {
        return 0;
    }


    public boolean esSiguienteDe(Carta otra) {
        return false;
    }


    @Override
    public int compareTo(Carta otra) {

    }

    @Override
    public String toString() {

    }

    /** Dos cartas son iguales si tienen el mismo palo y el mismo número. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Carta)) {
            return false;
        }
        Carta otra = (Carta) o;
        return palo == otra.palo && numero == otra.numero;
    }

    /** Coherente con equals. */
    @Override
    public int hashCode() {
        return palo.ordinal() * 13 + numero;
    }


}