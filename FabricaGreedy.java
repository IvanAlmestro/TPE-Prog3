import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FabricaGreedy {
    private int piezasAProducir;

    public FabricaGreedy(int piezasAProducir){
        this.piezasAProducir = piezasAProducir;
    }
    public static List<Maquina> resolverGreedy(List<Maquina> maquinas, int piezasAProducir) {
        List<Maquina> solucion = new LinkedList<>();
        int sumaActual = 0;

        // Ordenar las máquinas de mayor a menor producción
        maquinas.sort(Comparator.comparingInt(Maquina::getCantPiezasProduce).reversed());

        while (sumaActual<piezasAProducir && !maquinas.isEmpty()) {

            Maquina candidata = seleccionar(maquinas);
            maquinas.remove(candidata); // sacar del conjunto de candidatos

            if (esFactible(sumaActual, candidata, piezasAProducir)) {
                solucion.add(candidata);
                sumaActual += candidata.getCantPiezasProduce();
            }


        }
        if(esSolucion(maquinas,sumaActual,piezasAProducir))
            return solucion;
        else{
            return null;
        }
    }

    // Chequea si la suma actual alcanza el objetivo
    private static boolean esSolucion(List<Maquina>maquinas,int sumaActual, int piezasAProducir) {

        return sumaActual == piezasAProducir;
    }

    // Chequea si agregar la máquina no se pasa del objetivo
    private static boolean esFactible(int sumaActual, Maquina candidata, int piezasAProducir) {
        return sumaActual + candidata.getCantPiezasProduce() <= piezasAProducir;
    }

    // Selecciona la máquina que produce más piezas
    private static Maquina seleccionar(List<Maquina> maquinas) {
        return maquinas.get(0); // ya está ordenada de mayor a menor
    }

    public static void main(String[] args) throws IOException {
        List<Maquina> maquinas = new LinkedList<>();

        BufferedReader br = new BufferedReader(new FileReader("datos.txt"));

        // Leer cantidad de piezas requeridas
        int piezasRequeridas = Integer.parseInt(br.readLine().trim());

        // Leer máquinas desde el archivo
        System.out.println("Maquinas disponibles:");
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] partes = linea.split(",");
            String nombre = partes[0].trim();
            int piezas = Integer.parseInt(partes[1].trim());
            maquinas.add(new Maquina(nombre, piezas));
            System.out.println(nombre + " -> " + piezas);
        }
        br.close();

        // Llamada al algoritmo Greedy modularizado
        List<Maquina> solucion = resolverGreedy(maquinas, piezasRequeridas);
        int totalEstados = solucion.size(); // cada elección se considera un "estado"

        // Mostrar resultado
        System.out.println("\nCantidad de maquinas en funcionamiento: " + solucion.size());
        System.out.println("Mejor secuencia de máquinas para " + piezasRequeridas + " piezas:");
        if (solucion.isEmpty()) {
            System.out.println("No se encontró ninguna combinación.");
        } else {
            for (Maquina maq : solucion) {
                System.out.println(maq.getNombre() + " (" + maq.getCantPiezasProduce() + ")");
            }
        }
        System.out.println("Estados generados: " + totalEstados);
    }
}