/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectosistemas.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JEditorPane;

/**
 *
 * @author Alejandro
 */
public class HiloCliente implements Runnable{
//Declaramos las variables necesarias para la conexion y comunicacion
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    //El puerto debe ser el mismo en el que escucha el servidor
    private int puerto = 2027;
    //Si estamos en nuestra misma maquina usamos localhost si no la ip de la maquina servidor
    private String host = "localhost";
    private String mensajes = "";
    JEditorPane panel;
    
    //Constructor recibe como parametro el panel donde se mostraran los mensajes
    public HiloCliente(JEditorPane panel){
        this.panel = panel;
        try {
            cliente = new Socket(host,puerto);
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void run() {
        try{
            //Ciclo infinito que escucha por mensajes del servidor y los muestra en el panel
            while(true){
                mensajes += in.readUTF();
                panel.setText(mensajes);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Funcion sirve para enviar mensajes al servidor
    public void enviarMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
    

