import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import java.io.PrintStream;

public class TestRunner {
    public static void main(String[] args) {
        System.out.println("Ejecutando los tests para la clase Carta: ");
        run("Carta",      TestCarta.class);
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();
        System.out.println("Ejecutando los tests para la clase Mesa: ");
        run("Mesa",    TestMesa.class);
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();
        System.out.println("Ejecutando los tests para la clase Jugador: ");
        run("Jugador", TestJugador.class);
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();
        System.out.println("Ejecutando los tests para la clase Jugador Automático: ");
        run("JugadorAutomático", TestJugadorAutomatico.class);
        System.out.println("=========================================================");
        System.out.println("=========================================================");
        System.out.println();
        System.out.println("Ejecutando los tests para la clase Juego: ");
        run("Juego", TestJuego.class);
    }

    private static void run(String nombre, Class<?> testClass) {
        // Algunos tests (TestJuego) redirigen System.out durante cada test:
        // se guarda la consola real para que los mensajes del runner siempre se vean
        final PrintStream consola = System.out;

        JUnitCore core = new JUnitCore();
        core.addListener(new RunListener() {
            private boolean fallado;

            @Override
            public void testStarted(Description d) {
                fallado = false;
            }

            @Override
            public void testFailure(Failure f) {
                fallado = true;
                String motivo = f.getMessage() != null ? f.getMessage()
                        : f.getException().getClass().getSimpleName();
                consola.println("  FALLA  " + f.getDescription().getMethodName() + " -> " + motivo);
            }

            @Override
            public void testFinished(Description d) {
                if (!fallado) {
                    consola.println("  OK     " + d.getMethodName());
                }
            }
        });

        Result r = core.run(testClass);
        consola.println(r.wasSuccessful()
                ? nombre + ": OK (" + r.getRunCount() + " tests)"
                : nombre + ": " + r.getFailureCount() + " fallos de " + r.getRunCount());
    }
}
