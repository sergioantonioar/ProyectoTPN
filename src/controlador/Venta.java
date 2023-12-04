package controlador;

import modelo.Cliente;
import modelo.Producto;

import java.time.LocalDateTime; // Para representar fechas y horas locales
import java.time.format.DateTimeFormatter; // Para formatear la fecha y hora
import java.util.Scanner;

public class Venta {

    private String numero;
    private double totalVenta;
    private Cliente cliente;
    private Producto[] productos;
    private LocalDateTime fechaHora;

    //Sobrecarga de constructores
    public Venta(Producto[] productos) {
        this.productos = productos;
        this.fechaHora = LocalDateTime.now();
    }

    public Venta(String numero, Cliente cliente, Producto[] productos) {
        this.numero = numero;
        this.cliente = cliente;
        this.productos = productos;
    }

    public void realizarVenta() {
        Scanner sc = new Scanner(System.in);

        //Creando nuevo cliente y pedir nombre
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.ingresarNombre();

        //Asignando el nuevo cliente
        cliente = nuevoCliente;
        
        double totalVentas = 0;
        
        boolean continuarVenta;
        do {

            System.out.println("Ingrese la numeracion del producto a comprar: ");
            int indiceProducto = sc.nextInt();

            System.out.println("Ingrese la cantidad del producto: ");
            int cantidad = sc.nextInt();
            sc.nextLine();

            validarProductoEfectuarVenta(indiceProducto, cantidad);
            
            //Calculando y mostrando el valor total de la venta actual
            totalVentas += this.totalVenta; // totalVenta es una variable miembro

            // Preguntar si desea continuar o terminar la compra
            System.out.println("¿Desea continuar con la compra? (si/no)");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("no")) {
                System.out.println("Compra terminada. Total de ventas: $" + totalVentas);
                break; // Salir del bucle si el usuario desea terminar la compra
            }
            
            //Se utiliza la funcion equalsIgnoreCase para que no sea sensible a mayusculas o minusculas
            continuarVenta = respuesta.equalsIgnoreCase("si");

            // Calcular y mostrar el total de la venta actual
            System.out.println("Total hasta el momento: $" + totalVentas);
        } while (continuarVenta);
        
        sc.close(); //cerrando Scanner
    }

    //Metodo para obtener la fecha y hora con formato
    private String obtenerFecha() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return fechaHora.format(formatter);
    }

    //Metodo para calcular total venta
    private double calcularTotalVenta(double precioUnitario, int cantidad) {
        return precioUnitario * cantidad;
    }

    //Metodo para validar el producto y efectuar la venta
    private void validarProductoEfectuarVenta(int indiceProducto, int cantidad) {
        if (indiceProducto >= 1 && indiceProducto < productos.length) {
            Producto productoVendido = productos[indiceProducto - 1];

            String nombreProducto = productoVendido.getNombre();
            double precioUnitario = productoVendido.getPrecio();

            this.totalVenta = calcularTotalVenta(precioUnitario, cantidad);

            System.out.println("Venta realizada:");
            System.out.println("Producto: " + nombreProducto);
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Precio unitario: $" + precioUnitario);
            System.out.println("Total venta: $" + this.totalVenta);
            System.out.println("Fecha: " + obtenerFecha());
        } else {
            System.out.println("Producto no válido");
        }
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

}
