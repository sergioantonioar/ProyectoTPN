package Modelo;

public class Inventario {

    private Producto[] productos;

    // Constructor
    public Inventario() {
        // Inicializando el arreglo de productos con los precios
        productos = new Producto[]{
            new Producto("Tonificadores Abdominales", 125),
            new Producto("Tobilleras", 10),
            new Producto("Fajas Reductoras", 55),
            new Producto("Mat de Yoga", 10),
            new Producto("Proteinas", 72),
            new Producto("Masajeador Facial", 125),
            new Producto("Crema Reductora", 25),
            new Producto("Pesas", 22),
            new Producto("Rueda Abdominal", 42),
            new Producto("Pelota Fitness", 63)
        };
    }

    // Metodo getter
    public Producto[] getProductos() {
        return productos;
    }

    // Metodo para mostrar inventario
    public void mostrarInventario() {
        System.out.println("Productos disponibles:");

        for (int i = 0; i < productos.length; i++) {
            Producto producto = productos[i];
            System.out.println((i+1)+". "+producto.getNombre()+" - Precio: $" + producto.getPrecio());
        }
    }
}