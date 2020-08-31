package com.proyectosistemas.cajaregistradora;

/**
 *
 * @author Wendell Monge
 */
public class Cajero extends Thread{
    
    private String nombre;
    private Cliente cliente;
    private long TiempoInicial;

    public Cajero(){
        
    }
    
    public Cajero(String nombre, Cliente cliente, long TiempoInicial) {
        this.nombre = nombre;
        this.cliente = cliente;
        this.TiempoInicial = TiempoInicial;
    }

    public String getNombre() {
        return nombre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public long getInitialTime() {
        return TiempoInicial;
    }

    public static int getMIN_PRIORITY() {
        return MIN_PRIORITY;
    }

    public static int getNORM_PRIORITY() {
        return NORM_PRIORITY;
    }

    public static int getMAX_PRIORITY() {
        return MAX_PRIORITY;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setInitialTime(long TiempoInicial) {
        this.TiempoInicial = TiempoInicial;
    }
    
    @Override
    public void run() {
        System.out.println("La cajera: " + this.nombre + " está comenzando a procesar la compra de: "+ this.cliente.getNombre() 
                + " en un tiempo de: "+ (System.currentTimeMillis() - this.TiempoInicial) / 1000 + "segundos");

        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]); 
            System.out.println("Procesado el producto #" + (i + 1)+ " del cliente: " + this.cliente.getNombre() 
                    + "->Tiempo transcurrente: "+(System.currentTimeMillis() - this.TiempoInicial) / 1000 + "segundos");
		}

        System.out.println("La cajera: " + this.nombre + " ya terminó de procesar al cliente: "+ this.cliente.getNombre() 
                + " en un tiempo de : " + (System.currentTimeMillis() - this.TiempoInicial) / 1000 + "segundos");
	}

	private void esperarXsegundos(int segundos) {
            try {
                Thread.sleep(segundos * 1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

}

