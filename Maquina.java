public class Maquina {
    private String nombre;
    private int cantPiezasProduce;
    private boolean enFuncionamiento;

    public Maquina(String nombre,int cantPiezasProduce, boolean enFuncionamiento) {
        this.nombre = nombre;
        this.cantPiezasProduce = cantPiezasProduce;
        this.enFuncionamiento = enFuncionamiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantPiezasProduce() {
        return cantPiezasProduce;
    }


    public boolean isEnFuncionamiento() {
        return enFuncionamiento;
    }

    public void setEnFuncionamiento(boolean enFuncionamiento) {
        this.enFuncionamiento = enFuncionamiento;
    }
}
