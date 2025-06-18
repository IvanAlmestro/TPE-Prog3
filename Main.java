import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Maquina> maquinas = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader("datos.txt"));
        int piezasRequeridas = Integer.parseInt(br.readLine().trim());

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

        System.out.println("\n================== BACKTRACKING ==================\n");
        FabricaBacktracking fabricaBack = new FabricaBacktracking("Fabrica", piezasRequeridas);
        for (Maquina m : maquinas) {
            fabricaBack.addMaquina(m);
        }
        List<Maquina> solucionBack = fabricaBack.backMaquinas();
        imprimirSolucion(solucionBack, piezasRequeridas, fabricaBack.getCantEstados(), "Backtracking");

        System.out.println("\n==================== GREEDY =====================\n");
        FabricaGreedy fabricaGreedy = new FabricaGreedy(piezasRequeridas);

        // Importante: duplicar lista para que no se altere al remover
        List<Maquina> copiaMaquinas = new ArrayList<>();
        for (Maquina m : maquinas) {
            copiaMaquinas.add(new Maquina(m.getNombre(), m.getCantPiezasProduce()));
        }

        List<Maquina> solucionGreedy = fabricaGreedy.resolverGreedy(copiaMaquinas, piezasRequeridas);
        imprimirSolucion(solucionGreedy, piezasRequeridas, fabricaGreedy.getCantEstados(), "Greedy");
    }

    public static void imprimirSolucion(List<Maquina> solucion, int piezasRequeridas, int estados, String metodo) {
        System.out.println("Solución " + metodo + ":");
        if (solucion == null || solucion.isEmpty()) {
            System.out.println("No se encontró ninguna combinación.");
        } else {
            int total = solucion.stream().mapToInt(Maquina::getCantPiezasProduce).sum();
            System.out.println("Cantidad de máquinas en funcionamiento: " + solucion.size());
            System.out.println("Cantidad total de piezas producidas: " + total);
            System.out.println("Secuencia:");
            for (Maquina m : solucion) {
                System.out.println("- " + m.getNombre() + " (" + m.getCantPiezasProduce() + ")");
            }
        }
        System.out.println("Estados generados: " + estados);
    }
}