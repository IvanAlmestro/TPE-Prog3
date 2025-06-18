import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FabricaGreedy {
    private int piezasAProducir;
    private int cantEstados ;

    public FabricaGreedy(int piezasAProducir){
        this.piezasAProducir = piezasAProducir;
        cantEstados=0;
    }

    /*
     * Estrategia Greedy:
      - Los candidatos son las máquinas disponibles, ordenadas por cantidad de piezas que producen (mayor a menor).
      - En cada paso, se selecciona la máquina que produce más piezas y que no exceda la producción requerida.
      - Se repite mientras haya candidatos y no se haya alcanzado el total requerido.
      - La estrategia greedy en este caso evalua que con las maquinas disponibles se pueda llegar a la produccion de piezas determinada.
      - Estados: cantidad de candidatos considerados.
     */
    public List<Maquina> resolverGreedy(List<Maquina> maquinas, int piezasAProducir) {
        List<Maquina> solucion = new LinkedList<>();
        int sumaActual = 0;

        // Ordenar las máquinas de mayor a menor producción
        maquinas.sort(Comparator.comparingInt(Maquina::getCantPiezasProduce).reversed());

        while (sumaActual<piezasAProducir && !maquinas.isEmpty()) {

            Maquina candidata = seleccionar(maquinas);
            maquinas.remove(candidata); // sacar del conjunto de candidatos
            this.cantEstados++;
            if (esFactible(sumaActual, candidata, piezasAProducir)) {
                solucion.add(candidata);
                sumaActual += candidata.getCantPiezasProduce();
            }


        }
        if(esSolucion(sumaActual,piezasAProducir))
            return solucion;
        else{
            return null;
        }
    }

    // Chequea si la suma actual alcanza el objetivo
    private boolean esSolucion(int sumaActual, int piezasAProducir) {

        return sumaActual == piezasAProducir;
    }

    // Chequea si agregar la máquina no se pasa del objetivo
    private boolean esFactible(int sumaActual, Maquina candidata, int piezasAProducir) {
        return sumaActual + candidata.getCantPiezasProduce() <= piezasAProducir;
    }

    // Selecciona la máquina que produce más piezas
    private Maquina seleccionar(List<Maquina> maquinas) {
        return maquinas.get(0); // ya está ordenada de mayor a menor
    }

    public int getCantEstados() {
        return cantEstados;
    }

    public int getPiezasAProducir() {
        return piezasAProducir;
    }

    public void setPiezasAProducir(int piezasAProducir) {
        this.piezasAProducir = piezasAProducir;
    }


}