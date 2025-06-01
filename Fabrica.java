import java.util.ArrayList;
import java.util.List;

public class Fabrica {
    private String nombre;
    private List<Maquina> maquinas; // m1(7) m2(3) m3(4) m4(1)
    private List<List<Maquina>> soluciones; // (m3, m3, m3) == 12
    private final int piezasAProducir = 12;

    public Fabrica( String nombre) {
        this.maquinas = new List<>(maquinas);

        this.nombre = nombre;
    }

    public List<List<Maquina>> backMaquinas(){
        List<Maquina> auxiliar = new List<>();
        backMaquinasRec(auxiliar, 0, 0);
        return soluciones;
    }
    private void backMaquinasRec(List<Maquina> actual, int indice, int piezasProducidas){
        if(piezasProducidas == piezasAProducir){
            soluciones.addAll(new List<>(actual));
            return; //corto recursion
        }else{
            int produceMaquina = actual.get(indice).getCantPiezasProduce(); // guardamos lo que produce la maquina
            actual.add(actual.get(indice));
            piezasProducidas+=produceMaquina;

            if(piezasProducidas<piezasAProducir) { // poda
                backMaquinasRec(actual, indice+1, piezasProducidas);
            }
            actual.remove(actual.size()-1);
            piezasProducidas-= produceMaquina;
            backMaquinasRec(actual, indice+1, piezasProducidas);

        }
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


}
