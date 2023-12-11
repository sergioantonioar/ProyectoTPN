package Vista;

import Controlador.Venta;
import Modelo.Inventario;

import javax.swing.JOptionPane;

public class Ejecucion {

    public static void main(String[] args) {
        // Mostrar mensaje de bienvenida
        mostrarMensajeBienvenida();

        // Creando instancias de la clase Inventario
        Inventario productos = new Inventario();

        // Creando una instancia de la clase Venta
        Venta ventaNueva = new Venta(productos.getProductos());

        // Mostrar inventario
        productos.mostrarInventario();

        do {
            // Pedir nombre del cliente solo si es la primera vez
            if (ventaNueva.getNombreCliente() == null) {
                String nombreCliente = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
                ventaNueva.setNombreCliente(nombreCliente);
            }

            // Mostrar lista de productos para que el usuario elija
            int indiceProducto = mostrarListaProductos(productos.getProductos());

            // Pedir cantidad de compra del producto
            int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad del producto:"));

            // Realizar la venta con los datos ingresados
            ventaNueva.realizarVenta(indiceProducto, cantidad);

            // Preguntar si desea realizar otra compra
            int opcion = JOptionPane.showOptionDialog(null, "¿Desea realizar otra compra?", "Opción",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Sí", "No"}, "Sí");

            // Si la opción es 'No', salir del bucle
            if (opcion == JOptionPane.NO_OPTION) {
                break;
            }

        } while (true);

        // Mostrar detalles de la venta al finalizar
        ventaNueva.mostrarDetallesVentaYPersistir();
    }

    // Método para mostrar lista de productos y obtener la selección del usuario
    private static int mostrarListaProductos(Modelo.Producto[] productos) {
        StringBuilder listaProductos = new StringBuilder("Seleccione un producto:\n");

        for (int i = 0; i < productos.length; i++) {
            Modelo.Producto producto = productos[i];
            listaProductos.append((i + 1)).append(". ").append(producto.getNombre()).append(" - Precio: $").append(producto.getPrecio()).append("\n");
        }

        // Pedir al usuario que seleccione un producto
        int seleccion = Integer.parseInt(JOptionPane.showInputDialog(listaProductos.toString()));

        // Ajustar la selección para que coincida con el índice del arreglo
        return seleccion - 1;
    }

    // Método para mostrar mensaje de bienvenida
    private static void mostrarMensajeBienvenida() {
        JOptionPane.showMessageDialog(null,
                "Bienvenido a Vega Store.\nDonde tus victorias comienzan con el equipo adecuado.\n¡Prepárate para brillar en cada juego!",
                "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
    }
}