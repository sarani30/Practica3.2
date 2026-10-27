import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Nombre del jugador? ");
        String nombre = in.nextLine();
        System.out.println("Contra cuántos jugadores automáticos quieres jugar? (1-3) ");
        int n = in.nextInt();

        List<Jugador> jugadores = new ArrayList<>();
        Jugador jugador1 = new Jugador(nombre);
        jugadores.add(jugador1);
        for (int i=0; i<n; i++) {
            nombre = "Jugador automático "+String.valueOf(i+1);
            jugadores.add(new JugadorAutomatico(nombre));
        }

        Juego partida = new Juego(jugadores);
        partida.jugar();
    }
}