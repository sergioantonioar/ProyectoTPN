package Modelo;

public class Producto {

    private String nombre;
    private double precio;
    private int cantidadVendida;

    // Constructor
    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }
    
    
    
    public int getCantidadVendida(){
        return cantidadVendida;
    }
    
    public void setCantidadVendida(int cantidadVendida){
        this.cantidadVendida = cantidadVendida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

}