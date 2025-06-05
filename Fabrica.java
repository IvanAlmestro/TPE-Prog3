import java.util.ArrayList;
import java.util.List;

public class Fabrica {
    private String nombre;
    private ArrayList<Maquina> maquinas; // m1(7) m2(3) m3(4) m4(1)
    private List<List<Maquina>> soluciones; // (m3, m3, m3) == 12
    private final int piezasAProducir = 12;
    private int cantEstados;
    private int tamMejorSolucion =999;

    public Fabrica( String nombre) {
        this.maquinas = new ArrayList<>();
        this.soluciones = new ArrayList<>();
        this.nombre = nombre;
    }

    public List<List<Maquina>> backMaquinas(){
        ArrayList<Maquina> auxiliar = new ArrayList<>();
        cantEstados = 0;
        backMaquinasRec(auxiliar, 0,0);
         // reinicio el contador antes de empezar

        return soluciones;
    }
    private void backMaquinasRec(ArrayList<Maquina> actual,int indice ,int piezasProducidas) {
        cantEstados++;
        if (piezasProducidas == piezasAProducir) {
            soluciones.add(new ArrayList<>(actual));
            tamMejorSolucion=actual.size();
            return;
        }
        for (int i = indice; i < maquinas.size(); i++) {
            //agarramos maquina
            Maquina m = maquinas.get(i);
            //lo que produce cada maquina
            int produce = m.getCantPiezasProduce();
            //sumo las piezas producidas mas las que produce la actual para ver si es menor a las que hay que producir
            if (piezasProducidas + produce <= piezasAProducir) { //poda
                if (actual.size() >= tamMejorSolucion) {
                    continue; //saltear la rama cuando es mayor a la mejor solucion hasta ahora
                }
                actual.add(m);
                backMaquinasRec(actual, i, piezasProducidas + produce);
                actual.remove(actual.size() - 1);
            }
        }
    }
    @Override
    public String toString() {
        return "Fabrica{" +
                "soluciones=" + soluciones +
                ", maquinas=" + maquinas +
                '}';
    }

    public int getCantAProducir() {
        return piezasAProducir;
    }

    public List<Maquina> getMaquinas() {
        return new ArrayList<>(maquinas);
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void addMaquina(Maquina maquina){
        maquinas.add(maquina);
    }
    public void removeMaquina(Maquina maquina){
        maquinas.remove(maquina);
    }
    public int getTamanio(){
        return maquinas.size();
    }

    public int getCantEstados() {
        return cantEstados;
    }

    public static void main(String[] args) {
        // Crear la fábrica
        Fabrica fab = new Fabrica("Pepito");

        // Crear máquinas (nombre, piezas que produce, en funcionamiento)
        Maquina m1 = new Maquina("M1", 7, true);
        Maquina m2 = new Maquina("M2", 3, true);
        Maquina m3 = new Maquina("M3", 4, true);
        Maquina m4 = new Maquina("M4", 1, true);

        // Agregar máquinas a la fábrica
        fab.addMaquina(m1);
        fab.addMaquina(m2);
        fab.addMaquina(m3);
        fab.addMaquina(m4);

        // Mostrar lista de máquinas
        for (Maquina m : fab.getMaquinas()) {
            System.out.println("Maquina [nombre=" + m.getNombre() + ", cantidadPiezas=" + m.getCantPiezasProduce() + "]");
        }

        // Ejecutar el algoritmo de backtracking
        System.out.println("Backtracking");
        List<List<Maquina>> soluciones = fab.backMaquinas();

        // Suponemos que se quiere mostrar la primera solución óptima
        if (!soluciones.isEmpty()) {
            // Buscar la mejor solución (la más corta)
            List<Maquina> mejorSolucion = soluciones.get(0);

            for (List<Maquina> sol : soluciones) {
                if (sol.size() < mejorSolucion.size()) {
                    mejorSolucion = sol;
                }
            }

            // Mostrar la mejor solución
            System.out.print("Solución obtenida (secuencia de máquinas): ");
            int totalPiezas = 0;
            ArrayList<String> maquinasUsadas = new ArrayList<>();

            for (Maquina m : mejorSolucion) {
                System.out.print(m.getNombre() + " ");
                totalPiezas += m.getCantPiezasProduce();

                if (!maquinasUsadas.contains(m.getNombre())) {
                    maquinasUsadas.add(m.getNombre());
                }
            }
            System.out.println();

            System.out.println("Cantidad de piezas producidas: " + totalPiezas);
            System.out.println("Cantidad de puestas en funcionamiento: " + maquinasUsadas.size());
            System.out.println("Estados generados (nodos explorados): " + fab.getCantEstados());
        } else {
            System.out.println("No se encontró una solución.");
        }
    }
}
