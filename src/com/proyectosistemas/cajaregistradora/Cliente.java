package com.proyectosistemas.cajaregistradora;

/**
 *
 * @author Wendell Monge
 */
public class Cliente {
    private String nombre;
    private int[] carroCompra;

    public Cliente(){
    
    }
    public Cliente(String nombre, int[] carroCompra) {
        this.nombre = nombre;
        this.carroCompra = carroCompra;
    }

    public String getNombre() {
        return nombre;
    }

    public int[] getCarroCompra() {
        return carroCompra;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCarroCompra(int[] carroCompra) {
        this.carroCompra = carroCompra;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", carroCompra=" + carroCompra + '}';
    }
}
