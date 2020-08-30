/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectosistemas.frame;

import static java.lang.Thread.sleep;

/**
 *
 * @author Anthony Flores Boza
 */
public class CronometroDigital implements Runnable {

    private Cronometro cronometro;

    private int horas, segundos, minutos, milisegundos;

    private boolean corriendo = false;

    public CronometroDigital(int horas, int minutos, int segundos, int milisegundos, Cronometro cronometro) {
        this.horas = horas;
        this.segundos = segundos;
        this.minutos = minutos;
        this.milisegundos = milisegundos;
        this.cronometro = cronometro;
    }

    @Override
    public void run() {

        try {

            while (corriendo) {

                if (String.valueOf(milisegundos).length() == 1) {
                    cronometro.mili.setText(String.valueOf("000" + milisegundos));
                } else if (String.valueOf(milisegundos).length() == 2) {
                    cronometro.mili.setText(String.valueOf("00" + milisegundos));
                } else if (String.valueOf(milisegundos).length() == 3) {
                    cronometro.mili.setText(String.valueOf("0" + milisegundos));
                } else if (String.valueOf(milisegundos).length() == 4) {
                    cronometro.mili.setText(String.valueOf(milisegundos));
                }

                if (String.valueOf(segundos).length() > 1) {
                    cronometro.segundo.setText(String.valueOf(segundos));
                } else {
                    cronometro.segundo.setText(String.valueOf("0" + segundos));
                }

                if (String.valueOf(minutos).length() > 1) {
                    cronometro.minuto.setText(String.valueOf(minutos));
                } else {
                    cronometro.minuto.setText(String.valueOf("0" + minutos));
                }

                if (String.valueOf(horas).length() > 1) {
                    cronometro.hora.setText(String.valueOf(horas));
                } else {
                    cronometro.hora.setText(String.valueOf("0" + horas));
                }

                //System.out.println("Horas: " + horas + " minutos: " + minutos + " Segundos: " + segundos + " milisegundos: " + milisegundos);
                sleep(1);

                milisegundos += 1;

                if (milisegundos >= 1000) {
                    milisegundos = 0;
                    segundos += 1;

                    if (segundos >= 60) {

                        segundos = 0;
                        minutos += 1;

                        if (minutos >= 60) {
                            minutos = 0;
                            horas += 1;
                        }

                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Excepción del hilo por [" + e.getMessage() + "]");

        }

    }

    public static void main(String[] args) {

        Cronometro c = new Cronometro();
        c.setVisible(true);

        CronometroDigital cd = new CronometroDigital(0, 0, 0, 1, c);

        Thread t = new Thread(cd);

        t.start();

    }

    public boolean isCorriendo() {
        return corriendo;
    }

    public void setCorriendo(boolean corriendo) {
        this.corriendo = corriendo;
    }

}
