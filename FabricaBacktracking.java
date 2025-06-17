import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FabricaBacktracking {
    private String nombre;
    private ArrayList<Maquina> maquinas; // m1(7) m2(3) m3(4) m4(1)
    private List<Maquina> mejorConfiguracion;// se guarda la mejor solucion
    private List<Maquina> recorridoActual;
    private int piezasAProducir;
    private int cantEstados;

    public FabricaBacktracking(String nombre, int piezasAProducir) {
        this.nombre = nombre;
        this.maquinas = new ArrayList<>();
        this.mejorConfiguracion = new ArrayList<>();
        this.recorridoActual = new ArrayList<>();
        this.piezasAProducir = piezasAProducir;
        this.cantEstados = 0;
    }

    public List<Maquina> backMaquinas() {
        mejorConfiguracion.clear();
        recorridoActual.clear();
        backMaquinasRec(0);
        return mejorConfiguracion;
    }
     /* Estrategia de Backtracking:
        El árbol de exploración se genera combinando las máquinas posibles.
        Cada nodo son las máquinas usadas y las piezas que producen.
        Un estado solución es cuando a traves de las maquinas producimos exactactamente la cantidad de piezas requeridas.
        Se aplica una poda si:
        1) Ya se encontró una solución mejor o igual.
        2) La suma actual de piezas es mayor a las piezas  a producir.
     */
    private void backMaquinasRec(int piezasProducidas) {
        cantEstados++;

        if (piezasProducidas == piezasAProducir) {
            mejorConfiguracion = new ArrayList<>(recorridoActual);
            return;
        }

        if (!mejorConfiguracion.isEmpty() && recorridoActual.size() >= mejorConfiguracion.size()) {
            return; // poda (ya se encontró una solución mejor o igual)
        }

        for (Maquina m : maquinas) {
            int piezas = m.getCantPiezasProduce();
            // poda (si la suma actual de piezas es mayor a las piezas  a producir no entra).
            if (piezasProducidas + piezas <= piezasAProducir) {
                recorridoActual.add(m);
                backMaquinasRec(piezasProducidas + piezas);
                recorridoActual.remove(recorridoActual.size() - 1);
            }
        }
    }

    public void addMaquina(Maquina maquina) {
        maquinas.add(maquina);
    }

    public int getCantEstados() {
        //estados generados
        return cantEstados;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("datos.txt"));
        System.out.println("Maquinas disponibles:");

        int piezasRequeridas = Integer.parseInt(br.readLine().trim());
        FabricaBacktracking fabrica = new FabricaBacktracking("Fabrica", piezasRequeridas);

        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            String nombre = partes[0].trim();
            int piezas = Integer.parseInt(partes[1].trim());
            fabrica.addMaquina(new Maquina(nombre, piezas));
            System.out.println(nombre + " -> " + piezas);
        }
        br.close();

        // Ejecuta el back
        List<Maquina> solucion = fabrica.backMaquinas();

        // Muestra resultado
        System.out.println("\nCantidad de maquinas en funcionamiento: " + solucion.size());
        System.out.println("Mejor secuencia de máquinas para " + piezasRequeridas + " piezas:");
        if (solucion.isEmpty()) {
            System.out.println("No se encontró ninguna combinación.");
        } else {
            for (Maquina maq : solucion) {
                System.out.println(maq.getNombre() + " (" + maq.getCantPiezasProduce() + ")");
            }
        }

        System.out.println("Total de llamados recursivos: " + fabrica.getCantEstados());
    }
}