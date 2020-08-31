package com.proyectosistemas.carreracaballos;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

/**
 *
 * @author Wendell Monge
 */
public class CarreraCaballos {
    public JFrame frame;
    public JProgressBar h1 = new JProgressBar(0,100);
    public JProgressBar h2 = new JProgressBar(0,100);
    public JProgressBar h3 = new JProgressBar(0,100);
    public JProgressBar h4 = new JProgressBar(0,100);
    public JLabel msg = new JLabel("");
    static boolean runRaceButtonIsPressed = false;
    static boolean resetRaceButtonIsPressed = false;
    static int caballoGanador = 0;
    static boolean ganador = false;
    
    public synchronized void finish(int i)
    {
        msg.setVisible(true);
        msg.setText("Caballo #"+caballoGanador+" ha ganado la carrera! "
                + "Presione Reset para hacer otra carrera");
        if(i==100){
            ganador=true;
            System.out.println("Caballo #"+caballoGanador+" ha ganado la carrera! "
                + "Presione Reset para volver a hacer otra carrera");
        }
        frame.getContentPane().add(msg);
    }
        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable(){
                @Override
                public void run(){
                    try{
                        CarreraCaballos ventana = new CarreraCaballos();
                        ventana.frame.setVisible(true);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
                });
    }
    public CarreraCaballos(){
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 598, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        h1.setStringPainted(true);
        h1.setForeground(Color.red);
        h1.setBounds(150, 129, 259, 14);
        frame.getContentPane().add(h1);
        
        h2.setStringPainted(true);
        h2.setForeground(Color.green);
        h2.setBounds(150, 159, 259, 14);
        frame.getContentPane().add(h2);
        
        h3.setStringPainted(true);
        h3.setForeground(Color.blue);
        h3.setBounds(150, 189, 259, 14);
        frame.getContentPane().add(h3);
        
        h4.setStringPainted(true);
        h4.setForeground(Color.orange);
        h4.setBounds(150, 219, 259, 14);
        frame.getContentPane().add(h4);
        
        msg.setBounds(85,100,410,14); //Se muestra el caballo que gane
        msg.setVisible(false);
        msg.setFont(new Font("Tahoma",Font.BOLD,11));
        frame.getContentPane().add(msg);
        
        JButton btnStart = new JButton("Empezar Carrera");
        btnStart.setFont(new Font("Tahoma",Font.BOLD,14));
        btnStart.addActionListener(new Carrera());
        btnStart.setBounds(50, 287, 155, 40);
        frame.getContentPane().add(btnStart);
        
        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Tahoma",Font.BOLD,18));
        btnReset.addActionListener(new Reset());
        btnReset.setBounds(205, 287, 155, 40);
        frame.getContentPane().add(btnReset);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setFont(new Font("Tahoma",Font.PLAIN,18));
        btnSalir.addActionListener(new Salir());
        btnSalir.setBounds(360, 287, 155, 40);
        frame.getContentPane().add(btnSalir);
    }
    
    class Carrera implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (!runRaceButtonIsPressed){
                msg.setVisible(false);
                resetRaceButtonIsPressed = false;
                runRaceButtonIsPressed = true;
                H1 h1 = new H1();
                h1.start();
                H2 h2 = new H2();
                h2.start();
                H3 h3 = new H3();
                h3.start();
                H4 h4 = new H4();
                h4.start();
            }
        }
    }
    class Reset implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (!resetRaceButtonIsPressed){
                msg.setVisible(false);
                resetRaceButtonIsPressed = true;
                runRaceButtonIsPressed = false;
                ganador=false;
                H1 h1 = new H1();
                h1.reset();
                H2 h2 = new H2();
                h2.reset();
                H3 h3 = new H3();
                h3.reset();
                H4 h4 = new H4();
                h4.reset();
            }
        }
    }
    class Salir implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }
    
    class H1 extends Thread{
        public void reset(){
            h1.setValue(0);
            h1.repaint();
        }
        @Override
        public void run(){
            for(int i=0;i<101;i++){
                if(ganador){
                    break;
                }
                h1.setValue(i);
                h1.repaint();
                if(i==100){
                    caballoGanador=1;
                    finish(i);
                }
                try{//poner sleep en numero random de milisegundos
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()%60));
                }catch(InterruptedException err){
                    err.printStackTrace();
                }
            }
        }
    }
    
    class H2 extends Thread{
        public void reset(){
            h2.setValue(0);
            h2.repaint();
        }
        @Override
        public void run(){
            for(int i=0;i<101;i++){
                if(ganador){
                    break;
                }
                h2.setValue(i);
                h2.repaint();
                if(i==100){
                    caballoGanador=2;
                    finish(i);
                }
                try{//poner sleep en numero random de milisegundos
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()%60));
                }catch(InterruptedException err){
                    err.printStackTrace();
                }
            }
        }
    }
    
    class H3 extends Thread{
        public void reset(){
            h3.setValue(0);
            h3.repaint();
        }
        @Override
        public void run(){
            for(int i=0;i<101;i++){
                if(ganador){
                    break;
                }
                h3.setValue(i);
                h3.repaint();
                if(i==100){
                    caballoGanador=3;
                    finish(i);
                }
                try{//poner sleep en numero random de milisegundos
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()%60));
                }catch(InterruptedException err){
                    err.printStackTrace();
                }
            }
        }
    }
    
    class H4 extends Thread{
        public void reset(){
            h4.setValue(0);
            h4.repaint();
        }
        @Override
        public void run(){
            for(int i=0;i<101;i++){
                if(ganador){
                    break;
                }
                h4.setValue(i);
                h4.repaint();
                if(i==100){
                    caballoGanador=4;
                    finish(i);
                }
                try{//poner sleep en numero random de milisegundos
                    Thread.sleep(Math.abs(UUID.randomUUID().getMostSignificantBits()%60));
                }catch(InterruptedException err){
                    err.printStackTrace();
                }
            }
        }
    }
}
