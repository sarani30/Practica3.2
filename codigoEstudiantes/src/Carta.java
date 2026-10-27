import java.util.HashSet;
import java.util.Set;

/**
 * Carta inmutable de la baraja española de 40 cartas.
 * Números válidos: 1 a 7, 10 (sota), 11 (caballo) y 12 (rey).
 */
public final class Carta implements Comparable<Carta> {

    private final Palo palo;
    private final  int numero;

    /**
     * @throws IllegalArgumentException si el palo es null o el número no está en {1..7, 10, 11, 12}
     */
    public Carta(Palo palo, int numero) {

        boolean numeroValido = (numero >= 1 && numero <= 7) || (numero >= 10 && numero <= 12);

        //la carta tiene un palo y es un número válido
        if (palo != null && numeroValido) {
            this.palo = palo;
            this.numero = numero;
        } else {
            //lanza excepción
            throw new IllegalArgumentException("Argumentos inválidos");
        }
    }

    public Palo getPalo() {
        return this.palo;
    }

    public int getNumero() {
        return this.numero;
    }


    public int getOrden() {
        //no se usan ni el 8 ni el 9
        if (this.numero >= 1 && this.numero <= 7) {
            return this.numero;
        } else if (this.numero == 10) {
            //sota
            return 8;
        } else if (this.numero == 11) {
            //caballo
            return 9;
        } else {
            //rey
            return 10;
        }
    }


    public boolean esSiguienteDe(Carta otra) {

        //comprobar el palo y orden
        if ((this.palo == otra.palo) && (this.getOrden() == (otra.getOrden() + 1))) {
            return true;

        } else {
            return false;
        }
    }


    @Override
    public int compareTo(Carta otra) {

        //posible excepción
        if (otra == null) {
            throw new NullPointerException("La carta a comparar no puede ser null");
        }

        // Comparar por palo
        int compPalo = this.palo.compareTo(otra.palo);
        if (compPalo != 0) {
            return compPalo;
        }

        // Si los palos son iguales, comparar por número
        return this.numero - otra.numero;

    }

    @Override
    public String toString() {

        //analizar el número
        if (this.numero == 1) {
            //as
            return "as de " + this.palo;
        } else if (this.numero == 10) {
            //sota
            return "sota de " + this.palo;
        } else if (this.numero == 11) {
            //caballo
            return "caballo de " + this.palo;
        } else if (this.numero == 12) {
            //rey
            return "rey de " + this.palo;
        } else {
            //cualquiera de los otros número
            return this.numero + " de " + this.palo;
        }
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