import java.util.*;

public class Mesa {
    //parte privada
    private final Map<Palo, Deque<Carta>> filas;
    //clave -> palo ; valor -> fila de cartas

    public Mesa() {
        this.filas = new HashMap<>();
    }

    public boolean puedeColocar(Carta c) {

        if (c == null)
            return false;

        boolean ok = false;
        int numeroCarta = c.getNumero();
        Palo paloCarta = c.getPalo();

        //5 de un palo no abierto todavía
        if ((numeroCarta == 5) && (!this.filas.containsKey(paloCarta))) {
            ok = true;
        } else if (this.filas.containsKey(paloCarta)) {

            //existe el palo, comprobar si se puede poner por alguno de sus extremos
            Deque<Carta> filaPalo = this.filas.get(paloCarta);

            //extremo izquierdo y extremo derecho
            Carta izq = filaPalo.getFirst();
            Carta der = filaPalo.getLast();

            //ver si es siguiente de izq o siguiente de der
            if (izq.esSiguienteDe(c)) {
                //añadir al principio
                ok = true;

            } else if (c.esSiguienteDe(der)) {
                //añadir al final
                ok = true;
            } else { //no es siguiente de ninguna, no es válida
                ok = false;
            }
        }
        return ok;
    }


    public void colocar(Carta c) throws IllegalArgumentException {

        if (!puedeColocar(c)) { //false
            throw new IllegalArgumentException("No se puede colocar");

        } else { //true
            int numeroCarta = c.getNumero();
            Palo paloCarta = c.getPalo();

            //5 de un palo no abierto todavía
            if ((numeroCarta == 5) && (!this.filas.containsKey(paloCarta))) {

                Deque<Carta> filaNueva = new LinkedList<>();
                filaNueva.addFirst(c);

                this.filas.put(paloCarta, filaNueva);

            } else if (this.filas.containsKey(paloCarta)) {

                //existe el palo, poner por alguno de sus extremos
                Deque<Carta> filaPalo = this.filas.get(paloCarta);

                //extremo izquierdo y extremo derecho
                Carta izq = filaPalo.getFirst();
                Carta der = filaPalo.getLast();

                //ver si es siguiente de izq o siguiente de der
                if (izq.esSiguienteDe(c)) {
                    //añadir al principio
                    filaPalo.addFirst(c);

                } else if (c.esSiguienteDe(der)) {
                    //añadir al final
                    filaPalo.addLast(c);
                }
            }
        }
    }

    public boolean paloAbierto(Palo p) {
        return this.filas.containsKey(p);
    }

    public Carta getExtremoIzq(Palo p) {

        if (!this.filas.containsKey(p)) {
            return null;

        } else {
            //existe el palo
            return this.filas.get(p).getFirst();
        }
    }

    public Carta getExtremoDer(Palo p) {

        if (!this.filas.containsKey(p)) {
            return null;

        } else {
            //existe el palo
            return this.filas.get(p).getLast();
        }

    }


    public List<Carta> getFila(Palo p) {

        if (p == null) {
            return new ArrayList<>();
        }

        Deque<Carta> filaPalo = this.filas.get(p);

        if (filaPalo == null || filaPalo.isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(filaPalo);

    }

    public int numCartas() {

        int total = 0;

        for (Deque<Carta> fila : this.filas.values()) {
            if (fila != null) {
                total += fila.size();
            }
        }

        return total;
    }

    /** Una línea por palo, por ejemplo "copas:   [4, 5, 6]". */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Palo p : Palo.values()) {
            List<Integer> numeros = new ArrayList<>();
            Deque<Carta> fila = this.filas.get(p);

            if (fila != null) {
                for (Carta c : fila) {
                    numeros.add(c.getNumero());
                }
            }

            sb.append(p.name().toLowerCase())
                    .append(": ")
                    .append(numeros)
                    .append("\n");
        }

        return sb.toString();
    }
}
