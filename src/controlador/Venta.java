package controlador;

import modelo.Cliente;
import modelo.Producto;

public class Venta {

    private String numero;
    private Cliente cliente;
    private Producto[] productos;

    public Venta(String numero, Cliente cliente, Producto[] productos) {
        this.numero = numero;
        this.cliente = cliente;
        this.productos = productos;
    }

    public void realizarVenta(int indiceProducto, int cantidad) {
        if (indiceProducto >= 0 && indiceProducto < productos.length) {
            Producto productoVendido = productos[indiceProducto];

            String nombreProducto = productoVendido.getNombre();
            double precioUnitario = productoVendido.getPrecio();

            double totalVenta = precioUnitario * cantidad;

            System.out.println("Venta realizada:");
            System.out.println("Producto: " + nombreProducto);
            System.out.println("Cantidad: " + cantidad);
            System.out.println("Precio unitario: $" + precioUnitario);
            System.out.println("Total venta: $" + totalVenta);
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
