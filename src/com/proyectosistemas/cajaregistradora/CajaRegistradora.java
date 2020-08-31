package com.proyectosistemas.cajaregistradora;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * * @author Wendell Monge
 */
public class CajaRegistradora {
    public JFrame frame;
    public static JTextArea text = new JTextArea("");
    public static JTextArea text2 = new JTextArea("");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    
    public CajaRegistradora(){
        iniciar();
    }
    
    private void iniciar(){
        frame = new JFrame();
        frame.setBounds(100, 100, 598, 430);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        text.setBounds(30,80,500,40);
        text.setVisible(true);
        text.setFont(new Font("Tahoma",Font.BOLD,11));
        frame.getContentPane().add(text);
        
        text2.setBounds(30,150,500,40);
        text2.setVisible(true);
        text2.setFont(new Font("Tahoma",Font.BOLD,11));
        frame.getContentPane().add(text2);
    }
    
    public static class Cajero extends Thread{
    
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

    public long getTiempoInicial() {
        return TiempoInicial;
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
        text.setText("La cajera: " + this.nombre + " está comenzando a procesar la compra de: "+ this.cliente.getNombre() 
                + " en: "+ (System.currentTimeMillis() - this.TiempoInicial) / 1000 + " segundos");
        System.out.println(text.getText());
        for (int i = 0; i < this.cliente.getCarroCompra().length; i++) {
            this.esperarXsegundos(cliente.getCarroCompra()[i]); 
            text2.setText("Cajera: "+ this.nombre +", procesado el producto #" + (i + 1)+ " del cliente: " + 
            this.cliente.getNombre() + "->Tiempo: "+(System.currentTimeMillis() - this.TiempoInicial) / 1000 + "segundos");
            System.out.println(text2.getText());
		}
        text.setText("La cajera: " + this.nombre + " ya terminó de procesar al cliente: "+ this.cliente.getNombre() 
                + " en un tiempo de : " + (System.currentTimeMillis() - this.TiempoInicial) / 1000 + " seg");
        System.out.println(text.getText());
    }
	private void esperarXsegundos(int segundos) {
            try {
                Thread.sleep(segundos * 1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
