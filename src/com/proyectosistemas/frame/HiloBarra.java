/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectosistemas.frame;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alejandro
 */
public class HiloBarra extends Thread {

    @Override
    public void run() {
        if (Login.loginBar.getValue() < 100) {
            for (int i = 0; i <= 100; i++) {
                Login.loginBar.setValue(i);
                Login.loginBar.update(Login.loginBar.getGraphics());

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
