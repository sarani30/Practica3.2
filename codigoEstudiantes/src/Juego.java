import java.util.*;

public class Juego {
    public static final int MIN_JUGADORES = 2;
    public static final int MAX_JUGADORES = 4;
    public static final int CARTAS_POR_JUGADOR = 8;
    public static final Carta CARTA_INICIAL = new Carta(Palo.OROS, 5);

    private Stack<Carta> mazo; //pila de cartas
    private List<Jugador> jugadores; //lista de jugadores
    private int turno;
    private Mesa mesa; //mesa
    private Scanner in;     // único Scanner sobre System.in; se crea al leer por primera vez


    public Juego(List<Jugador> jugadores) {
        validarJugadores(jugadores);
        List<Carta> baraja = crearBarajaSinCartaInicial();
        Collections.shuffle(baraja);
        iniciar(jugadores, baraja);
    }

    /**
     * Crea un juego con la baraja en el orden indicado (útil para pruebas).
     *
     */
    public Juego(List<Jugador> jugadores, List<Carta> baraja) {
        validarJugadores(jugadores);
        validarBaraja(baraja);
        iniciar(jugadores, new LinkedList<>(baraja));
    }

    private void validarJugadores(List<Jugador> jugadores) {
        //la lista no puede ser null
        if (jugadores == null) {
            throw new IllegalArgumentException("Lista vacía");

        } else if (jugadores.size() < MIN_JUGADORES || jugadores.size() > MAX_JUGADORES) {
            //no puede haber menos de minimo ni max de jug
            throw new IllegalArgumentException("Cnatidad de jugadores no válida");

        } else {

            //comprobar si algún jugador es null
            boolean jugadorNull = false;

            //comprobar que no se repiten los nombres
            Set<String> nombresJugadores = new HashSet<>();

            for (Jugador j : jugadores) {

                //ningun jugador puede ser null
                if (j.getNombre() == null) {
                    throw new IllegalArgumentException("Nombre de jugador - null - no permitido");

                }

                //no puede haber jugadores con el nombre repetido
                if (!nombresJugadores.contains(j.getNombre())) {
                    nombresJugadores.add(j.getNombre());
                } else {
                    throw new IllegalArgumentException("Jugadores con el mismo nombre");
                }
            }
        }
    }

    private List<Carta> crearBarajaSinCartaInicial() {
        List<Carta> baraja = new ArrayList<>();

        //Generar la baraja por palo y por número
        for (Palo p : Palo.values()) { //por palo

            //por número
            for (int i = 1; i <= 12; i++) {

                if (i != 8 && i != 9) { // Ignorar 8 y 9
                    baraja.add(new Carta(p, i));
                }
            }
        }

        // Eliminar el 5 de Oros
        baraja.remove(new Carta(Palo.OROS, 5));

        return baraja;
    }
    private void validarBaraja(List<Carta> baraja) {
        //no puede ser null
        if (baraja == null){
            throw new IllegalArgumentException("La lista es null");

        } else if (baraja.size() != 39) {
            //debe de tener 39 cartas
            throw new IllegalArgumentException("No tiene 39 cartas");
        } else {
            //no pueden estar repetidas
            Set<Carta> vistos = new HashSet<>();

            for (Carta c : baraja) {
                if (!vistos.contains(c)) {
                    vistos.add(c);
                } else {
                    throw new IllegalArgumentException("No pueden haber cartas repetidas");
                }
            }
        }
    }

    private void iniciar(List<Jugador> jugadores, List<Carta> baraja) {
        this.jugadores = new ArrayList<>(jugadores);
        repartirCartas(baraja);
        mazo = new Stack<>();
        for (Carta c : baraja) {
            mazo.push(c);
        }
        mesa = new Mesa();
        mesa.colocar(CARTA_INICIAL);
        turno = 0;
    }



    private void repartirCartas(List<Carta> baraja) {
        for (Jugador j : jugadores) {


        }
    }

    private void jugarCarta(Jugador jugador, Carta c) {

    }


    private boolean robarHastaColocar(Jugador jugador, boolean mostrarCartas) {
        return false;
    }

    private void pasarTurno(Jugador jugador) {

    }


    private void comprobarJugador (Jugador jugador) {

    }

    // ------------------------------------------------------------------
    // Getters
    // ------------------------------------------------------------------

    public Mesa getMesa() {
        return mesa;
    }

    /** @return número de cartas que quedan en el mazo */
    public int getNumCartasMazo() {
        return mazo.size();
    }

    /** @return índice del jugador al que le toca jugar */
    public int getTurno() {
        return turno;
    }

    /** @return copia de la lista de jugadores, en el orden de juego */
    public List<Jugador> getJugadores() {
        return new ArrayList<>(jugadores);
    }

    /** @return el jugador que se ha quedado sin cartas, o null si la partida no ha terminado */
    public Jugador getGanador() {
        for (Jugador j : jugadores) {
            if (j.numCartas() == 0) {
                return j;
            }
        }
        return null;
    }

    //===================================================================
    // clasificacion y juego por turnos
    //=================================================================
    public List<Jugador> getClasificacion() {
        List<Jugador> clasificacion = new ArrayList<>();
        clasificacion.add(jugadores.get(0));
        for (int i=1; i<jugadores.size(); i++) {
            int ncartas = jugadores.get(i).getMano().size();
            int j=0;
            while (j<clasificacion.size() && ncartas>=clasificacion.get(j).getMano().size()) {
                j++;
            }
            clasificacion.add(j,jugadores.get(i));
        }
       return clasificacion;
    }


    private Scanner entrada() {
        if (in == null) {
            in = new Scanner(System.in);
        }
        return in;
    }



    public boolean jugarTurnoManual(Jugador jugador) {
        //Jugador jugador = jugadorEn(indice);
        comprobarJugador(jugador);
        if (jugador.esAutomatico())
            throw new IllegalArgumentException("Es un jugdor automático en turno manual");
        boolean colocada = false;
        if (!jugador.puedeJugar(mesa)) {
            System.out.println("No tienes cartas jugables en tu mano. Vas a robar del mazo.");
            colocada= robarHastaColocar(jugador, true);
        }
        else {
            List<Carta> cartasJug = jugador.cartasJugables(mesa);
            System.out.print("Puedes jugar las siguientes cartas: ");
            for (int i = 0; i < cartasJug.size(); i++) {
                System.out.print(i + ": " + cartasJug.get(i) + " ");
            }
            System.out.println();
            System.out.println("Elige una carta: ");
            int index = leerIndice(cartasJug.size());
            Carta c = cartasJug.get(index);
            jugarCarta(jugador, c);
            colocada = true;
            System.out.println("Juega: " + c);
        }
        pasarTurno(jugador);
        return colocada;
    }


    private int leerIndice(int n) {
		int index = -1;
        while (index<0 || index>=n) {
            String linea = entrada().nextLine().trim();
            try {
                index = Integer.parseInt(linea);
                if (index < 0 || index >= n) {
                   System.out.println("Índice no válido. Pulsa un valor válido");
                }
            } catch (NumberFormatException e) {
                // no es un número: se vuelve a pedir
				System.out.println("Índice no válido. Pulsa un valor válido");
            }        
        }
		return index;
    }


    public boolean jugarTurnoAutomatico(Jugador jugador) {
        //Jugador jugador = jugadorEn(indice);
        comprobarJugador(jugador);
        if (!(jugador instanceof JugadorAutomatico)) {
            throw new IllegalArgumentException(jugador + " no es un jugador automático");
        }
        boolean colocada = false;
        Carta c = ((JugadorAutomatico) jugador).elegirCarta(mesa);
        if (c == null) {
            System.out.println("   No tiene cartas jugables en la mano. Va a robar del mazo.");
            colocada= robarHastaColocar(jugador, false);
        }
        else {
            jugarCarta(jugador, c);
            colocada = true;
            System.out.println("   Juega: " + c);
        }
        pasarTurno(jugador);
        return colocada;
    }

    // ------------------------------------------------------------------
    // Partida
    // ------------------------------------------------------------------

    public void jugar() {
        System.out.println("Carta inicial colocada en la mesa: " + CARTA_INICIAL);

		boolean hayGanador = false;
        while (!hayGanador) {
            Jugador jugador = jugadores.get(turno);
            System.out.println();
            System.out.println("MESA:");
            System.out.println(mesa);
            System.out.println("Es el turno del jugador " + jugador.getNombre());
            if (!jugador.esAutomatico()) {
                System.out.println("  Mano: " + jugador.getMano());
            }
            System.out.println("  Tiene " + jugador.numCartas() + " cartas.");

            if (jugador.esAutomatico()) {
                jugarTurnoAutomatico(jugador);
            } else {
                jugarTurnoManual(jugador);
            }

            hayGanador = jugador.numCartas() == 0;
            System.out.println("Fin turno. Pulsa ENTER");
            entrada().nextLine();
        }

        List<Jugador> clasificacion = getClasificacion();
        System.out.println();
        System.out.println("Fin del juego. Clasificación: ");
        System.out.println("Ganador: " + clasificacion.get(0).getNombre());
        System.out.println("Resto de clasificados:");
        for (int i = 1; i < clasificacion.size(); i++) {
            Jugador j = clasificacion.get(i);
            System.out.println("       " + j.getNombre() + " con " + j.numCartas() + " cartas.");
        }
    }
}
