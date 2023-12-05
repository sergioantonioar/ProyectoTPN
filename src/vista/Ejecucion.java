package vista;

import controlador.Venta;
//import modelo.Cliente;
import modelo.Inventario;

public class Ejecucion {

    public static void main(String[] args) {
        // Creando instancias de la clase Inventario
        Inventario productos = new Inventario();

        // Creando una instancia de la clase Venta
        Venta ventaNueva = new Venta(productos.getProductos());
        
        //Mostrar inventario
        productos.mostrarInventario();
        
        ventaNueva.realizarVenta();
        
        ventaNueva.mostrarDetallesVenta();
    }

}
