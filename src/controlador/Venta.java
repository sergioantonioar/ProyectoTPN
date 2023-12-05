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
        StringBuilder resultadoVenta = new StringBuilder(); // Para la construccion de la cadena

        //Creando nuevo cliente y pedir nombre
        Cliente nuevoCliente = new Cliente();
        nuevoCliente.ingresarNombre();

        //Asignando el nuevo cliente
        cliente = nuevoCliente;

        double totalVentas = 0;

        boolean continuarVenta = true;
        do {

            System.out.println("Ingrese la numeración del producto a comprar: ");
            int indiceProducto;

            //Si se ingresa algo que no puede ser convertido a numero entero
            try {
                indiceProducto = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                continue; //Para volver al inicio del bucle y permitir ingresar un valor nuevamente 
            }

            System.out.println("Ingrese la cantidad del producto: ");
            int cantidad;
            try {
                cantidad = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
                continue;
            }

            validarProductoEfectuarVenta(indiceProducto, cantidad);

            //Acumulando el total de la venta actual a total de ventas
            totalVentas += calcularTotalVenta(productos[indiceProducto - 1].getPrecio(), cantidad);

            // Se utiliza la función equalsIgnoreCase para que no sea sensible a mayúsculas o minúsculas
            resultadoVenta.append("Venta realizada. Total de la venta actual: $").append(this.totalVenta).append("\n");
            resultadoVenta.append("Total hasta el momento: $").append(totalVentas).append("\n");
            resultadoVenta.append("¿Desea continuar con la compra? (si/no)");

            System.out.println(resultadoVenta.toString());
            resultadoVenta.setLength(0); //Establece la longitud en 0, para asegurar que este vacio antes de comenzar la construccion

            String respuesta = sc.nextLine();
            continuarVenta = respuesta.equalsIgnoreCase("si");

        } while (continuarVenta);

        resultadoVenta.append("Compra terminada. Total de ventas: $").append(totalVentas).append("\n");

        // Imprimir usando StringBuilder
        System.out.println(resultadoVenta.toString());

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
        if ((indiceProducto >= 1) && (indiceProducto <= productos.length)) {
            Producto productoVendido = productos[indiceProducto - 1];

            // Actualizar la cantidad vendida en el producto
            productoVendido.setCantidadVendida(productoVendido.getCantidadVendida() + cantidad);

            String nombreProducto = productoVendido.getNombre();
            double precioUnitario = productoVendido.getPrecio();

            this.totalVenta = calcularTotalVenta(precioUnitario, cantidad);

            System.out.println("Producto agregado:");
            System.out.println("Producto: " + nombreProducto);
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Precio unitario: $" + precioUnitario);
            System.out.println("Sub total: $" + this.totalVenta);
            System.out.println("Fecha: " + obtenerFecha());
        } else {
//            System.out.println("Producto no válido");
            System.out.println("Producto no válido. Índice ingresado: " + indiceProducto + ". Tamaño del array de productos: " + productos.length);
        }
    }

    // Método para mostrar detalles de la venta al finalizar
    public void mostrarDetallesVenta() {
        System.out.println("====================================================================");
        System.out.println("Detalles de la venta:");
        System.out.println("====================================================================");
        System.out.println("Nombre del cliente: " + cliente.getNombre());
        System.out.println("Fecha y hora de la venta: " + obtenerFecha());

        System.out.println("Productos comprados:");
        System.out.printf("%-20s %-10s %-15s %-15s\n", "Producto", "Cantidad", "Precio Unitario", "Sub total");

        double totalVentaDeProductos = 0;

        for (int i = 0; i < productos.length; i++) {
            Producto producto = productos[i];

            // Verificar si el producto fue comprado (cantidad vendida mayor que cero)
            if (producto.getCantidadVendida() > 0) {
                double totalVentaProducto = calcularTotalVenta(producto.getPrecio(), producto.getCantidadVendida());
                totalVentaDeProductos += totalVentaProducto;

                System.out.printf("%-20s %-10d %-15.2f %-15.2f\n",
                        producto.getNombre(),
                        producto.getCantidadVendida(),
                        producto.getPrecio(),
                        calcularTotalVenta(producto.getPrecio(), producto.getCantidadVendida()));
            }
        }
        System.out.println("====================================================================");
        System.out.println("Total de la venta: $" + totalVentaDeProductos);
        System.out.println("====================================================================");

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
