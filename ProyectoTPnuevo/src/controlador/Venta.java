package Controlador;

import modelo.Cliente;
import Modelo.Producto;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Venta {

    private double totalVenta;
    private Cliente cliente;
    private Producto[] productos;
    private LocalDateTime fechaHora;

    // Constructor
    public Venta(Producto[] productos) {
    this.productos = productos;
    this.fechaHora = LocalDateTime.now();
    this.cliente = new Cliente(); 
    }

    // Método para realizar la venta
    public void realizarVenta(int indiceProducto, int cantidad) {
        validarProductoEfectuarVenta(indiceProducto, cantidad);

        // Mostrar mensaje de venta realizada
        JOptionPane.showMessageDialog(null, "Venta realizada. Total de la venta actual: $" + this.totalVenta +
                "\nTotal hasta el momento: $" + calcularTotalVentas(), "Venta Realizada", JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para obtener la fecha y hora con formato
    private String obtenerFecha() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaHora.format(formatter);
    }

    // Método para calcular total venta
    private double calcularTotalVenta(double precioUnitario, int cantidad) {
        return precioUnitario * cantidad;
    }

    // Método para validar el producto y efectuar la venta
    private void validarProductoEfectuarVenta(int indiceProducto, int cantidad) {
        if ((indiceProducto >= 0) && (indiceProducto < productos.length)) {
            Producto productoVendido = productos[indiceProducto];

            // Actualizar la cantidad vendida en el producto
            productoVendido.setCantidadVendida(productoVendido.getCantidadVendida() + cantidad);

            String nombreProducto = productoVendido.getNombre();
            double precioUnitario = productoVendido.getPrecio();

            this.totalVenta = calcularTotalVenta(precioUnitario, cantidad);

            // Mostrar detalles de la venta
            JOptionPane.showMessageDialog(null,
                    "Producto agregado:\n" +
                            "Producto: " + nombreProducto + "\n" +
                            "Cantidad: " + cantidad + "\n" +
                            "Precio unitario: $" + precioUnitario + "\n" +
                            "Sub total: $" + this.totalVenta + "\n" +
                            "Fecha: " + obtenerFecha(),
                    "Detalles de la Venta", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Mostrar mensaje de producto no válido
            JOptionPane.showMessageDialog(null, "Producto no válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para mostrar detalles de la venta al finalizar y persistir en un archivo
    public void mostrarDetallesVentaYPersistir() {
        StringBuilder detallesVenta = new StringBuilder("Detalles de la venta:\n");
        detallesVenta.append("Nombre del cliente: ").append(cliente.getNombre()).append("\n");
        detallesVenta.append("Fecha y hora de la venta: ").append(obtenerFecha()).append("\n");

        detallesVenta.append("Productos comprados:\n");
        detallesVenta.append(String.format("%-20s %-10s %-15s %-15s\n", "Producto", "Cantidad", "Precio Unitario", "Sub total"));

        double totalVentaDeProductos = 0;

        for (int i = 0; i < productos.length; i++) {
            Producto producto = productos[i];

            // Verifica si el producto se compró (cantidad vendida mayor que cero)
            if (producto.getCantidadVendida() > 0) {
                double totalVentaProducto = calcularTotalVenta(producto.getPrecio(), producto.getCantidadVendida());
                totalVentaDeProductos += totalVentaProducto;

                detallesVenta.append(String.format("%-20s %-10d %-15.2f %-15.2f\n",
                        producto.getNombre(),
                        producto.getCantidadVendida(),
                        producto.getPrecio(),
                        totalVentaProducto));
            }
        }

        detallesVenta.append("Total de la venta: $").append(totalVentaDeProductos).append("\n");

        // Mostrar detalles de la venta usando JOptionPane
        JOptionPane.showMessageDialog(null, detallesVenta.toString(), "Detalles de la Venta", JOptionPane.INFORMATION_MESSAGE);

        // Persistir los detalles de la venta en un archivo txt
        persistirEnArchivo(detallesVenta.toString());
    }

    // Método para persistir los detalles de la venta en un archivo txt
    private void persistirEnArchivo(String detallesVenta) {
        String nombreArchivo = "detalles_venta.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(nombreArchivo, true))) {
            writer.println(detallesVenta);
            writer.println("=========================================");
            System.out.println("Los detalles de la venta se han guardado en el archivo " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al intentar guardar los detalles de la venta en el archivo.");
            e.printStackTrace();
        }
    }

    // Método para calcular el total de todas las ventas
    private double calcularTotalVentas() {
        double total = 0;

        for (Producto producto : productos) {
            total += calcularTotalVenta(producto.getPrecio(), producto.getCantidadVendida());
        }

        return total;
    }

    // Setter para establecer el nombre del cliente
    public void setNombreCliente(String nombreCliente) {
        if (cliente == null) {
            cliente = new Cliente();
        }
        cliente.setNombre(nombreCliente);
    }

    public String getNombreCliente() {
        if (cliente != null) {
            return cliente.getNombre();
        } else {
            return null; // O algún valor predeterminado si el cliente no está inicializado
        }
    }
}
