package vista;

import controlador.Venta;
import modelo.Cliente;
import modelo.Inventario;

public class Ejecucion {

    public static void main(String[] args) {
        // Creando instancias de la clase Inventario
        Inventario productos = new Inventario();

        // Creando un cliente 
        Cliente cliente1 = new Cliente("Juan Hurtado");

        // Creando una instancia de la clase Venta
        Venta venta1 = new Venta("NumeroVenta", cliente1, productos.getProductos());
        
        //Mostrar inventario
        productos.mostrarInventario();

        
    }

}
