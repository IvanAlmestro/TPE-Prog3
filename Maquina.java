public class Maquina {
    private String nombre;
    private int cantPiezasProduce;


    public Maquina(String nombre,int cantPiezasProduce) {
        this.nombre = nombre;
        this.cantPiezasProduce = cantPiezasProduce;

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

}
