import java.util.List;

/**
 * Jugador controlado por el ordenador. Se proporciona completo.
 *
 * <p>Estrategia: de las cartas jugables, elige la del palo del que tiene más cartas en la mano,
 * para poder seguir jugando por esa fila en los turnos siguientes. A igualdad, la primera
 * jugable en el orden de la mano.</p>
 */

public class JugadorAutomatico extends Jugador{
    public JugadorAutomatico(String nombre) {
        super(nombre);
    }

    @Override
    public boolean esAutomatico() {
        return true;
    }

    /** @return la carta elegida, o null si no tiene ninguna jugable */
    public Carta elegirCarta(Mesa mesa) {
        List<Carta> jugables = cartasJugables(mesa);
        List<Carta> mano = getMano();
        Carta mejor = null;
        int mejorCuenta = -1;
        for (Carta c : jugables) {
            int cuenta = 0;
            for (Carta otra : mano) {
                if (otra.getPalo() == c.getPalo()) {
                    cuenta++;
                }
            }
            if (cuenta > mejorCuenta) {
                mejor = c;
                mejorCuenta = cuenta;
            }
        }
        return mejor;
    }

}
