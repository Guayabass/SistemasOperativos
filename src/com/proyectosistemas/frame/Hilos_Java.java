package com.proyectosistemas.frame;

import javax.swing.*;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hilos_Java {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        JFrame marco = new MarcoRebote();

        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        marco.setVisible(true);

    }

}

//Movimiento de la pelota
class Pelota {

    // Mueve la pelota invirtiendo posición si choca con límites
    public void mueve_pelota(Rectangle2D limites) {

        x += dx;

        y += dy;

        if (x < limites.getMinX()) {

            x = limites.getMinX();

            dx = -dx;
        }

        if (x + TAMX >= limites.getMaxX()) {

            x = limites.getMaxX() - TAMX;

            dx = -dx;
        }

        if (y < limites.getMinY()) {

            y = limites.getMinY();

            dy = -dy;
        }

        if (y + TAMY >= limites.getMaxY()) {

            y = limites.getMaxY() - TAMY;

            dy = -dy;

        }

    }

    //Forma de la pelota en su posición inicial
    public Ellipse2D getShape() {

        return new Ellipse2D.Double(x, y, TAMX, TAMY);

    }

    private static final int TAMX = 15;

    private static final int TAMY = 15;

    private double x = 0;

    private double y = 0;

    private double dx = 1;

    private double dy = 1;

}

// Lámina que dibuja las pelotas----------------------------------------------------------------------
class LaminaPelota extends JPanel {

    //Añadimos pelota a la lámina
    public void add(Pelota b) {

        pelotas.add(b);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        g.setColor(Color.WHITE);

        Graphics2D g2 = (Graphics2D) g;

        for (Pelota b : pelotas) {

            g2.fill(b.getShape());
        }

    }

    private ArrayList<Pelota> pelotas = new ArrayList<Pelota>();
}

class PelotaHilos implements Runnable {

    private Pelota pelota;
    private Component componente;

    public PelotaHilos(Pelota unaPelota, Component unComponente) {

        pelota = unaPelota;
        componente = unComponente;

    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000000; i++) {

            try {
                pelota.mueve_pelota(componente.getBounds());

                componente.paint(componente.getGraphics());

                Thread.sleep(4);
            } catch (InterruptedException ex) {
                System.out.println("");
            }

        }
    }
}

//Marco con lámina y botones------------------------------------------------------------------------------
class MarcoRebote extends JFrame {

    private LaminaPelota lamina;

    public MarcoRebote() {

        setBounds(600, 300, 400, 350);

        setTitle("Ejemplo con rebotes");

        lamina = new LaminaPelota();
        lamina.setBackground(Color.BLUE);
        add(lamina, BorderLayout.CENTER);

        JPanel laminaBotones = new JPanel();

        ponerBoton(laminaBotones, "Iniciar", new ActionListener() {

            public void actionPerformed(ActionEvent evento) {

                try {
                    comienza_el_juego();
                } catch (InterruptedException ex) {
                    System.out.println("");
                }
            }

        });

        ponerBoton(laminaBotones, "Salir", new ActionListener() {

            public void actionPerformed(ActionEvent evento) {

                dispose();

            }

        });

        add(laminaBotones, BorderLayout.SOUTH);
    }

    //Ponemos botones
    public void ponerBoton(Container c, String titulo, ActionListener oyente) {

        JButton boton = new JButton(titulo);

        c.add(boton);

        boton.addActionListener(oyente);

    }

    //Añade pelota y la bota 1000 veces
    public void comienza_el_juego() throws InterruptedException {

        Pelota pelota = new Pelota();

        lamina.add(pelota);

        Runnable r = new PelotaHilos(pelota, lamina);

        Thread t = new Thread(r);

        t.start();

    }

}
